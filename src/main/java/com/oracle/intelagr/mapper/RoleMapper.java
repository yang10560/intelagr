package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.Role;

import java.util.List;

public interface RoleMapper {


    /**
     * 获取所有权限
     * @return
     */
   List<Role> getAllRoles();


    /**
     * 根据用户id取权限
     * @param userId
     * @return
     */
   List<Role> getRolesByUserId(String userId);
	
}
