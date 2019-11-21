package com.oracle.intelagr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.common.MD5Util;
import com.oracle.intelagr.entity.Function;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.entity.UserRole;
import com.oracle.intelagr.mapper.UserMapper;
import com.oracle.intelagr.mapper.UserRoleMapper;
import com.oracle.intelagr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public User login(String username, String password) {
        String pwd = MD5Util.getMD5Code(password);

        return userMapper.getUserByUserIdWithPassword(username, pwd);
    }

    @Override
    public List<Map<String, Object>> getRoleFunction(String userID) {
        User user = userMapper.getUserRoleFunction(userID);
        //通过 user 获取 当前 user的权限
        List<Role> roles = user.getRoles();

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> ModuleList = new HashMap<>();
        for (Role role : roles) {
            //根据 当前用户的权限 返回 指定菜单  一对多
            for (Function fun : role.getFunctions()) {
                //如果 当前 用户 没有 很多 模块儿编号  还显示码
                if (null != fun.getModuleCode()) {
                    //区分一级菜单 和 二级菜单?
                    //这个 map 集合 就是用来 控制 是一级菜单 还是二级菜单的
                    if (ModuleList.get(fun.getModuleCode()) == null) {
                        //一级菜单
                        Map<String, Object> map = new HashMap<>();

                        map.put("parent", fun.getModuleName());

                        //存储 二级菜单的 集合  最终加入到 map 集合中
                        List<Function> funList = new ArrayList<>();
                        //加入的是 实体
                        funList.add(fun);
                        //map 加入的是 二级菜单
                        map.put("child", funList);

                        list.add(map);
                        //这个 map集合 就是用来控制 是 一级菜单 还是二级菜单的
                        ModuleList.put(fun.getModuleCode(), map);

                    } else {
                        //二级菜单
                        Map map = (Map) ModuleList.get(fun.getModuleCode());

                        List<Function> funList = (List<Function>) map.get("child");

                        funList.add(fun);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public PageInfo<User> getUsers(Integer page, Integer pageSize,
                                   String userID, String userName, String type) {


        PageHelper.startPage(page, pageSize, true, true, false);
        try {
            PageInfo<User> studentPageInfo = new PageInfo<>(userMapper.getAllUserWithInfo(userID, userName, type));
//            studentPageInfo.setPageNum(pageNo);

            return studentPageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean setLoginStatus(String id, String loginStatus) {
        if (!"".equals(id) && !"".equals(loginStatus)) return userMapper.setLoginStatus(id, loginStatus);
        else return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        User u = userMapper.getUserById(id);
        boolean c = false;
        boolean b = false;
        if(u != null && u.getUserID() != null){
             c = userRoleMapper.deleteRolesByUserId(u.getUserID());
             b = userMapper.deleteUser(id);
        }

        return  b && c;
    }

    @Override
    public User getUserByid(Integer id) {
        return userMapper.getUserById(id);
    }


    @Override
    public List<Map<Integer, String>> getUserTypes() {
        return userMapper.getUserTypes();
    }

    @Override
    public boolean addUser(HttpSession session, Map<String, String> map) {
        String userID = map.get("userID");
        String password = map.get("password");
        String userName = map.get("userName");
        String userType = map.get("userType");
        String companyCode = map.get("companyCode");
        String companyName = map.get("companyName");
        String tel = map.get("tel");
        String email = map.get("email");
        String remark = map.get("remark");

        User user = new User();
        user.setUserID(userID);
        user.setPassword(MD5Util.getMD5Code(password));
        user.setUserName(userName);
        user.setUserType(userType);
        user.setCompanyCode(companyCode);
        user.setCompanyName(companyName);
        user.setTel(tel);
        user.setEmail(email);
        user.setRemark(remark);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        User sessionUser = (User) session.getAttribute("user");
        user.setUpdateUserId(sessionUser.getUserID());
        user.setCreateUserId(sessionUser.getUserID());
        boolean b = userMapper.addUser(user);
        UserRole userRole = new UserRole();

        String[] urs = map.get("role").split(",");

        for (String ur : urs) {
            userRole.setRoleCode(ur);
            userRole.setUserID(userID);
            userRole.setRemark(remark);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRole.setUpdateUserId(sessionUser.getUserID());
            userRole.setCreateUserId(sessionUser.getUserID());
            userRoleMapper.addUserRole(userRole);
        }

        return b;
    }


    @Override
    public boolean updateUserAndRoles(HttpSession session, Map<String, String> map) {
        map.put("password", MD5Util.getMD5Code(map.get("password")));
        boolean a = userMapper.updateUserPro(map);
        boolean b = userRoleMapper.deleteRolesByUserId(map.get("userID"));
        String[] urs = map.get("role").split(",");
        UserRole userRole = new UserRole();
        User sessionUser = (User) session.getAttribute("user");
        for (String ur : urs) {
            userRole.setRoleCode(ur);
            userRole.setUserID(map.get("userID"));
            userRole.setRemark(map.get("remark"));
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRole.setUpdateUserId(sessionUser.getUserID());
            userRole.setCreateUserId(sessionUser.getUserID());
            userRoleMapper.addUserRole(userRole);
        }


        return a && b;
    }
}
