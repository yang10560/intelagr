package com.oracle.intelagr.service.impl;

import com.oracle.intelagr.common.TreeModel;
import com.oracle.intelagr.entity.Function;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.mapper.FunctionMapper;
import com.oracle.intelagr.mapper.RoleFunctionMapper;
import com.oracle.intelagr.mapper.RoleMapper;
import com.oracle.intelagr.mapper.UserMapper;
import com.oracle.intelagr.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FunctionMapper functionMapper;

    @Autowired
    RoleMapper roleMapper;




    @Override
    public List<TreeModel> getMenuTree(String id) {

        List<TreeModel> treeList = new ArrayList<>();

        List<Function> allFunctions = new ArrayList<>();

        //用户所有菜单
   /*      User u = userMapper.getUserRoleFunction("admin");
       List<Role> roles = u.getRoles();
        for (Role role : roles) {
            List<Function> rfs = role.getFunctions();
            for (Function f : rfs) {
                if(f.getModuleCode() != null &&!"null".equals(f.getModuleCode()))
                allFunctions.add(f);
            }
        }*/

        //用色下的权限
        Role r = roleMapper.getRoleById(id);
        List<Function> rfs = functionMapper.getRoleFuctions(r.getRoleCode());
        for (Function f : rfs) {
            if(f.getModuleCode() != null &&!"null".equals(f.getModuleCode()))
                allFunctions.add(f);
        }

        /*for (Function ff : allFunctions) {
            System.out.println(ff.getModuleCode() + "-" + ff.getFunctionCode() + "-" + ff.getFunctionName());
        }*/

        List<String> fc = functionMapper.getAllFunctionModuleCode();

        for (String s : fc) {
            List<Function> fs = functionMapper.getFunctionByModuleCode(s);
            TreeModel tr1 = new TreeModel();

            tr1.setPid("");
            tr1.setIconCls("");
            tr1.setState("");
            tr1.setChecked("");

            tr1.setId(s);
            for (Function f : fs) {
                tr1.setText(f.getModuleName());
                TreeModel tr2 = new TreeModel();
                tr2.setId(f.getFunctionCode());
                tr2.setText(f.getFunctionName());

                tr2.setPid("");
                tr2.setIconCls("");
                tr2.setState("");

                String checked = "";
                //用户是否存在
                for (Function uf : allFunctions) {
                    if(uf.getFunctionCode().equals(f.getFunctionCode())){
                        checked = "true" ;
                    }
                }
                tr2.setChecked(checked);
                tr1.getChildren().add(tr2);
                //System.out.println(f);
            }
            treeList.add(tr1);

            //System.out.println("===============");
        }

        return treeList;
    }
}
