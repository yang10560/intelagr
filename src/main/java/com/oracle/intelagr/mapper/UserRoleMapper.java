package com.oracle.intelagr.mapper;


import com.oracle.intelagr.entity.UserRole;

import java.util.Map;

public interface UserRoleMapper {
    /**
     * 添加用户角色
     * @param role
     */
	public void addUserRole(UserRole role);


    boolean deleteRolesByUserId(String userID);
}
