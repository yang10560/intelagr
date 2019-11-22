package com.oracle.intelagr.controller;


import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.TreeModel;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.service.FunctionService;
import com.oracle.intelagr.service.RoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    FunctionService functionService;

    /**
     * 查询
     * @param roleCode
     * @param roleName
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "roleCode", defaultValue = "") String roleCode,
                             @RequestParam(value = "roleName", defaultValue = "") String roleName,
                             @RequestParam(value = "page", defaultValue = "1") String page,
                             @RequestParam(value = "pageSize", defaultValue = "5") String pageSize) {

         Map<String, String> queryMap = new HashMap<>();
        queryMap.put("roleCode", roleCode);
        queryMap.put("roleName", roleName);


        PageInfo<Role> pageInfo = roleService.getRoles(Integer.parseInt(page), Integer.parseInt(pageSize), roleCode, roleName);

        ModelAndView mv = new ModelAndView("role/roleList");
        // System.out.println(users);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("queryRoleMap", queryMap);
        return mv;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public JsonResult addRole(HttpSession session,@RequestBody Map<String, Object> parms){

        JsonResult jsonResult = new JsonResult(roleService.saveRole(session, parms));

        return jsonResult;
    }



    @RequestMapping("/editRoleInit")
    public ModelAndView editRoleInit(String id){
        Role role = roleService.getRoleById(id);
        ModelAndView mv = new ModelAndView("role/editRole");
        mv.addObject("updateRole",role);
        return mv;
    }



    @RequestMapping("/updateRole")
    @ResponseBody
    public JsonResult updateRole(HttpSession session,@RequestBody Map<String, Object> parms){

        JsonResult jsonResult = new JsonResult(roleService.UpateRole(session, parms));

        return jsonResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult deleteRole(@RequestParam("ids[]")Integer[] ids){

        //sonResult jsonResult = new JsonResult(roleService.UpateRole(session, parms));

        //TODO
        System.out.println(Arrays.toString(ids));
        return new JsonResult(true);
    }


    /**
     * 跳转权限页
     * @param id
     * @return
     */
    @RequestMapping("/roleAuth")
    public ModelAndView roleAuth(@RequestParam("id")String id){

        Role role = roleService.getRoleById(id);

        ModelAndView mv = new ModelAndView("role/roleAuth");
        JSONArray jsonArray = new JSONArray();

        List<TreeModel> menuTree = functionService.getMenuTree(id);

        for (TreeModel tr1 : menuTree) {
            // JSONObject jsonObject = JSONObject.fromObject(tr1);
            jsonArray.add(tr1);
        }

        String jsonResult = jsonArray.toString();

        mv.addObject("role",role);
        mv.addObject("jsonResult",jsonResult);

        return mv;


    }




}
