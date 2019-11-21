package com.oracle.intelagr.service;

import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RoleService {


    /**
     * 获取所有权限
     * @return
     */
    public List<Role> getAllRoles();


    /**
     * 根据用户id取权限
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(String userId);

    PageInfo<Role> getRoles(Integer page, Integer pageSize, String roleCode, String roleName);
}
