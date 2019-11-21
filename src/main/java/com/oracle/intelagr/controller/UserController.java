package com.oracle.intelagr.controller;


import com.github.pagehelper.PageInfo;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.MD5Util;
import com.oracle.intelagr.entity.Role;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.service.CompanyService;
import com.oracle.intelagr.service.RoleService;
import com.oracle.intelagr.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    CompanyService companyService;


    @RequestMapping("/logindo")
    @ResponseBody
    public JsonResult login(@RequestParam("userID") String userid, @Param("password") String password, Model model) {
        User user = userService.login(userid, password);
        if (user != null) {
            if ("01".equals(user.getLoginStatus())) {
                model.addAttribute("user", user);
                return new JsonResult(true, "登录成功");
            } else {
                return new JsonResult(false, "用户不可用");
            }
        } else {
            return new JsonResult(false, "用户名或密码错误");
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        session.removeAttribute("currentUser");
        sessionStatus.setComplete();
        return "login";
    }

    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        //验证 用户的权限 ， 从而显示 不同的 菜单列表

        User user = (User) session.getAttribute("user");

        List<Map<String, Object>> menuList = userService.getRoleFunction(user.getUserID());

        model.addAttribute("menuList", menuList);
        return "main";
    }


    /**
     * 个人信息维护编辑页
     *
     * @return
     */
    @RequestMapping("/baseInfoEditInit")
    public String baseInfoEditInit() {

        return "/user/basicInfoEdit";
    }


    /**
     * 个人信息更新
     *
     * @return
     */
    @RequestMapping(value = "/baseInfoUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult baseInfoUpdate(@RequestBody User user) {

        // System.out.println(user);
        boolean b = userService.updateUser(user);

        if (b) {
            return new JsonResult(true, "修改成功");
        } else {
            return new JsonResult(false, "修改失败");
        }

    }

    /**
     * 个人信息维护编辑页
     *
     * @return
     */
    @RequestMapping("/updatePwdInit")
    public String updatePwdInit() {

        return "/user/updatePwd";
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    //updatePwd
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePwd(@RequestBody Map<String, String> map, HttpSession session) {


        //  System.out.println(map);

        String oldPwd = map.get("oldPwd");
        String id = map.get("id");
        String newPassword = map.get("newPassword");


        User user = (User) session.getAttribute("user");
        User u = new User();
        if (user.getPassword().equals(MD5Util.getMD5Code(oldPwd)) && Integer.parseInt(id) == user.getId()) {
            u.setId(Integer.parseInt(id));
            u.setPassword(MD5Util.getMD5Code(newPassword));
            boolean b = userService.updatePwd(u);
            if (b) {
                return new JsonResult(true, "修改成功");
            } else {
                return new JsonResult(false, "修改失败");
            }
        }
        return new JsonResult(false, "修改失败");
    }


    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "userID", defaultValue = "") String userID,
                             @RequestParam(value = "userName", defaultValue = "") String userName,
                             @RequestParam(value = "type", defaultValue = "") String type,
                             @RequestParam(value = "page", defaultValue = "1") String page,
                             @RequestParam(value = "pageSize", defaultValue = "5") String pageSize) {
      /*  System.out.println(map);
        String userID = map.get("userID");
        String userName = map.get("userName");
        String type = map.get("type");
        String page = map.get("page");
        String pageSize = map.get("pageSize");*/

        Map<String, String> queryUserMap = new HashMap<>();
        queryUserMap.put("userId", userID);
        queryUserMap.put("userName", userName);
        queryUserMap.put("type", type);


        PageInfo<User> users = userService.getUsers(Integer.parseInt(page), Integer.parseInt(pageSize), userID, userName, type);

        ModelAndView mv = new ModelAndView("/user/userList");
        // System.out.println(users);
        mv.addObject("users", users);
        mv.addObject("queryUserMap", queryUserMap);
        mv.addObject("userTypes", userService.getUserTypes());

        return mv;
    }

 /*   @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list1() {
        PageInfo<User> users = userService.getUsers(1, 3, null, null, null);
        ModelAndView mv = new ModelAndView("/user/userList");
        System.out.println(users);
        mv.addObject("users", users);
        return mv;
    }*/

    @RequestMapping(value = "/setLoginStatus", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult setLoginStatus(@RequestParam("id") String id, @RequestParam("loginStatus") String loginStatus) {
//        @RequestParam("id")String id,@RequestParam("loginStatus")String loginStatus
      /*  String id = map.get("id");
        String loginStatus = map.get("loginStatus");*/
//        ModelAndView mv = new ModelAndView("/user/userList");
//        mv.addObject("users",);
        // System.out.println(id);
        //  System.out.println(loginStatus);

        boolean b = userService.setLoginStatus(id, loginStatus);

        if (b) {
            return new JsonResult(true, "启用成功");
        } else {
            return new JsonResult(false, "禁用失败");
        }


    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult deleteUser(@RequestParam(value = "ids", defaultValue = "-1") String id) {

        boolean b = userService.deleteUser(Integer.parseInt(id));

        if (b) {
            return new JsonResult(true, "删除成功");
        } else {
            return new JsonResult(false, "删除失败");
        }
    }


    /**
     * 跳转编辑密码页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/editPass", method = RequestMethod.GET)
    public ModelAndView editPass(@RequestParam(value = "id", defaultValue = "-1") String id) {
        ModelAndView modelAndView = new ModelAndView("user/editPass");
        User resetUser = userService.getUserByid(Integer.parseInt(id));
        modelAndView.addObject("resetUser", resetUser);
        return modelAndView;
    }

    /**
     * 重置密码
     *
     * @param
     * @return
     */
    //updatePwd
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult resetPass(@RequestBody User user) {
        if (user != null && user.getPassword() != null && user.getId() != null) {
            user.setPassword(MD5Util.getMD5Code(user.getPassword()));

            if (userService.updatePwd(user)) {
                return new JsonResult(true, "修改成功");
            } else {
                return new JsonResult(false, "修改失败");
            }

        } else {
            return new JsonResult(false, "修改失败");
        }
    }

    /**
     * 跳转添加用户编辑页面
     */

    @RequestMapping("/toAddUserPage")
    public ModelAndView toAddUserPage() {


        ModelAndView mv = new ModelAndView("user/addUser");

        mv.addObject("userTypes", userService.getUserTypes());

        List<Role> allRoles = roleService.getAllRoles();

        mv.addObject("allRoles", allRoles);

        mv.addObject("compnays", companyService.getAllCompanys());


        return mv;
    }


    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonResult addUser(HttpSession session, @RequestBody Map<String, String> map) {

        // System.out.println(map);


        boolean b = userService.addUser(session, map);
        if (b) {
            return new JsonResult(true, "添加成功");
        }

        return new JsonResult(true, "添加失败");
    }


    /**
     * 跳转添加用户编辑页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/toUpdateUserPage")
    public ModelAndView toUpdateUserPage(@RequestParam(value = "id", defaultValue = "-1") String id) {

//        1.查询用户
        User updateUser = userService.getUserByid(Integer.parseInt(id));

        ModelAndView mv = new ModelAndView("user/updateUser");
        if (updateUser != null) {

            List<Role> roles = roleService.getRolesByUserId(updateUser.getUserID());

            updateUser.setRoles(roles);

            mv.addObject("userTypes", userService.getUserTypes());

            List<Role> allRoles = roleService.getAllRoles();

            mv.addObject("allRoles", allRoles);

            mv.addObject("compnays", companyService.getAllCompanys());

            mv.addObject("updateUser", updateUser);

            //Done
            JSONObject jsonObject = new JSONObject();
            List<String> jsonDate = new ArrayList<>();

            for (Role allRole : allRoles) {
                boolean selected = false;
                for (Role role : roles) {
                    if (allRole.getRoleCode().equals(role.getRoleCode())) {
                        selected = true;
                        break;
                    }
                }
                jsonObject.put("desc", allRole.getRoleName());
                jsonObject.put("roleCode", allRole.getRoleCode());
                jsonObject.put("selected", selected);
                jsonObject.put("text", allRole.getRoleName());
                jsonDate.add(jsonObject.toString());

            }

            //  System.out.println(jsonDate.toString());

            mv.addObject("jsonDate", jsonDate.toString());


        }

        return mv;

    }


    /**
     * 修改用户
     *
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public JsonResult updateUser(HttpSession session, @RequestBody Map<String, String> map) {

        //System.out.println(map);

        boolean b = userService.updateUserAndRoles(session, map);

        if(b) return new JsonResult(true, "修改成功");
        return new JsonResult(true, "添加失败");
    }


}
