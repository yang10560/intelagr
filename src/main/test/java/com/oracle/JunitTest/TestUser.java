package com.oracle.JunitTest;


import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.mapper.UserMapper;
import com.oracle.intelagr.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:sqlMapConfig.xml"})
public class TestUser {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testlogin(){
        User login = userService.login("admin", "admin");

        System.out.println("结果："+login);
    }

    @Test
    public void testMenu(){
        List<Map<String, Object>> admin = userService.getRoleFunction("admin");

        System.out.println("log.d"+admin);
    }

    @Test
    public void testUserTyps(){
        List<Map<Integer,String>> userTypes = userMapper.getUserTypes();

        for (Map<Integer, String> userType : userTypes) {
            System.out.println(userType);
        }
    }




}
