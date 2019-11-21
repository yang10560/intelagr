package com.oracle.intelagr.service.impl;

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
}
