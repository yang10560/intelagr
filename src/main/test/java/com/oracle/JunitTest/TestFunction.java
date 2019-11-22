package com.oracle.JunitTest;

import com.oracle.intelagr.common.TreeModel;
import com.oracle.intelagr.entity.Function;
import com.oracle.intelagr.mapper.FunctionMapper;
import com.oracle.intelagr.service.FunctionService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestFunction extends BaseTest {

    @Autowired
    FunctionMapper functionMapper;

    @Autowired
    FunctionService functionService;
    @Test
    public void testFunction1(){

        List<String> fc = functionMapper.getAllFunctionModuleCode();

        for (String s : fc) {
            List<Function> fs = functionMapper.getFunctionByModuleCode(s);
            for (Function f : fs) {
                System.out.println(f);
            }
            System.out.println("===============");
        }

    }


    @Test
    public void testFunction2(){
        List<TreeModel> menuTree = functionService.getMenuTree("21");

      //  System.out.println(menuTree);

      //  System.out.println("=====");

        //System.out.println(menuTree.get(0).getChildren());

        JSONArray jsonArray = new JSONArray();

        for (TreeModel tr1 : menuTree) {
           // JSONObject jsonObject = JSONObject.fromObject(tr1);
            jsonArray.add(tr1);
        }

        String s = jsonArray.toString();
        System.out.println(s);


    }
}
