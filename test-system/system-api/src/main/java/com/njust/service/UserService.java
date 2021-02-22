package com.njust.service;

import com.njust.domain.User;
import com.njust.dto.UserDto;
import com.njust.entity.UserEntity;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/1/31 21:46
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户编号
     * @return
     */
    User getOne(Long userId);

    /**
     * 分页查询用户
     * @param userDto
     * @return
     */
    DataGridView listUserForPage(UserDto userDto);

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    int addUser(UserDto userDto);

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    int updateUser(UserDto userDto);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 重置用户密码
     * @param userIds
     */
    void resetPassWord(Long[] userIds);


    /**
     * 查询所有可用的用户
     * @return
     */
    List<User> getAllUsers();


    void updateUserTime(User user);

    void updatePwd(Long userId, String newPassword);

    List<UserEntity>  addUserExcel(List<UserEntity> list);
}
