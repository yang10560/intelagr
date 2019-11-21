package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    List<Role> getRolesByRoleCodeOrRoleName(@Param("roleCode") String roleCode, @Param("roleName") String roleName);

    boolean saveRole(Map<String, Object> parms);

    /**
     * 根据id查用色
     * @param id
     * @return
     */
    Role getRoleById(String id);

    /**
     * 更新角色
     * @param parms
     * @return
     */
    boolean updateRole(Map<String, Object> parms);
}
