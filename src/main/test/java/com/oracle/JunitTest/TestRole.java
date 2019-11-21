package com.oracle.JunitTest;


import com.oracle.intelagr.common.MD5Util;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.mapper.RoleMapper;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

public class TestRole extends BaseTest{


    @Autowired
    RoleMapper roleMapper;
    @Test
    public void testGetRoles(){
        List<Role> allRoles = roleMapper.getAllRoles();
        for (Role allRole : allRoles) {
            System.out.println(allRole);

        }
    }


    @Test
    public void testJoson(){
        List<Role> allRoles = roleMapper.getAllRoles();




    }


}
