package com.oracle.JunitTest;


import com.oracle.intelagr.common.MD5Util;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {


    @Test
    public void testMd5(){
        String md5Code = MD5Util.getMD5Code("123");
        String md5Code1 = MD5Util.getMD5Code("");
        System.out.println(md5Code);
    }


    @Test
    public void test1(){
        JSONObject jsonObject = new JSONObject();
        //{"desc":"${rs.roleName}","roleCode":"${rs.roleCode}","selected":false,"text":"${rs.roleName}"},

        List<String> jsonDate = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            jsonObject.put("desc","aa");
            jsonObject.put("roleCode","g01");
            jsonObject.put("selected",true);
            jsonObject.put("text","aa");
            jsonDate.add(jsonObject.toString());
        }

        System.out.println(jsonDate);

        for (String s : jsonDate) {
            System.out.println(s);
        }







    }
}
