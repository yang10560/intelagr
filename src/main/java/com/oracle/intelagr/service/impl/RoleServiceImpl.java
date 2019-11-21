package com.oracle.intelagr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.mapper.RoleMapper;
import com.oracle.intelagr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles(){
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
            PageInfo<Role> rolePageInfo = new PageInfo<>(roleMapper.getRolesByRoleCodeOrRoleName(roleCode,roleName));

            return rolePageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
