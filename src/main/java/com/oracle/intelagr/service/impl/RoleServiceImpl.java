package com.oracle.intelagr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.mapper.RoleFunctionMapper;
import com.oracle.intelagr.mapper.RoleMapper;
import com.oracle.intelagr.mapper.UserRoleMapper;
import com.oracle.intelagr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleMapper.getAllRoles();

        return allRoles;
    }

    @Override
    public List<Role> getRolesByUserId(String userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public PageInfo<Role> getRoles(Integer page, Integer pageSize, String roleCode, String roleName) {
        PageHelper.startPage(page, pageSize, true, true, false);
        try {
            PageInfo<Role> rolePageInfo = new PageInfo<>(roleMapper.getRolesByRoleCodeOrRoleName(roleCode, roleName));

            return rolePageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean saveRole(HttpSession session, Map<String, Object> parms) {
        User user = (User) session.getAttribute("user");
        //CreateUserId,CreateDate,UpdateUserId,UpdateDate
        parms.put("createUserId", user.getUserID());
        parms.put("updateUserId", user.getUserID());
        parms.put("createDate", new Date());
        parms.put("updateDate", new Date());

        return roleMapper.saveRole(parms);
    }

    @Override
    public Role getRoleById(String id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public boolean UpateRole(HttpSession session, Map<String, Object> parms) {
        User user = (User) session.getAttribute("user");
        //CreateUserId,CreateDate,UpdateUserId,UpdateDate
        parms.put("createUserId", user.getUserID());

        boolean a = roleMapper.updateRole(parms);
        boolean b = userRoleMapper.updateRoleCodeByOldRoleCode(parms);//更新用户表关联的角色
        boolean c = roleFunctionMapper.updateRoleCodeByOldRoleCode(parms);//更新菜单表关联的角色

        return a && b && c;
    }

    @Override
    public boolean saveRoleAuth(HttpSession session, String roleCode, String[] funIds) {

        User user = (User) session.getAttribute("user");
        boolean a = roleFunctionMapper.delete(roleCode);

        Map<String, String> params = new HashMap<>();
        for (String funId : funIds) {
            params.put("createUserId", user.getUserID());
            params.put("updateUserId", user.getUserID());
            params.put("roleCode", roleCode);
            params.put("fid", funId);
            roleFunctionMapper.insert(params);
        }
        return a;
    }


    @Override
    public boolean deleteRole(Integer[] ids) {

        for (Integer id : ids) {
            Role r = roleMapper.getRoleById(id.toString());
            String roleCode = r.getRoleCode();
            roleFunctionMapper.delete(roleCode);//删除角色菜单映射
            userRoleMapper.deleteByRoleCode(roleCode);//删除用户角色映射
            roleMapper.delete(id);//删除role
        }

        return true;
    }
}
