package com.oracle.intelagr.service;

import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface RoleService {


    /**
     * 获取角色所有角色
     * @return
     */
    public List<Role> getAllRoles();


    /**
     * 根据用户id取角色
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(String userId);

    PageInfo<Role> getRoles(Integer page, Integer pageSize, String roleCode, String roleName);

    /**
     * 添加角色
     * @param parms
     * @return
     */
    boolean saveRole(HttpSession session,Map<String, Object> parms);

    Role getRoleById(String id);

    boolean UpateRole(HttpSession session, Map<String, Object> parms);
}
