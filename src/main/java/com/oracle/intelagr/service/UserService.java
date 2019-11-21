package com.oracle.intelagr.service;

import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password);


    /**
     * 查一级二级菜单
     * @param userID
     * @return
     */
    public List<Map<String,Object>> getRoleFunction(String userID);

    /**
     * 更新用户 3字段
     * @param user
     * @return
     */
    boolean updateUser(User user);

    boolean updatePwd(User user);


    PageInfo<User> getUsers(Integer page, Integer pageSize,
                            String userID, String userName, String type);

    boolean setLoginStatus(String id,String loginStatus);


    boolean deleteUser(Integer id);

    User getUserByid(Integer id);

    /**
     * 查询用户类型
     * @return
     */
    List<Map<Integer,String>> getUserTypes();

    /**
     * 添加用户
     * @param map
     * @return
     */
    boolean addUser(HttpSession session,Map<String, String> map);

    boolean updateUserAndRoles(HttpSession session,Map<String, String> map);
}
