package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 根据用户id密码查用户
     * @param userID
     * @param password
     * @return
     */
    public User getUserByUserIdWithPassword(@Param("userID")String userID,@Param("password")String password);

    /**
     * 根据id查用户权限
     * @param userID
     * @return
     */
    public User getUserRoleFunction(String userID);

    /**
     * 根据id更新信息
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 更新密码
     * @param user
     * @return
     */
    boolean updatePwd(User user);

    /**
     * 根据Id查用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 所有用户带信息
     * @return
     */
    List<User> getAllUserWithInfo(@Param("userId")String userId,
                                  @Param("userName")String userName,
                                  @Param("type")String userType);


    /**
     * 设置用户登录是否可用状态
     * @param id
     * @param loginStatus
     * @return
     */
    public boolean setLoginStatus(@Param("id")String id,@Param("loginStatus")String loginStatus);

    /**
     * 删除用户 软删除
     * @param id
     * @return
     */
    boolean deleteUser(Integer id);

    /**
     * 查询用户类型
     * @return
     */
    List<Map<Integer,String>> getUserTypes();

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);


    boolean updateUserPro(Map<String, String> map);
}
