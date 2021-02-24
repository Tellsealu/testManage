package com.njust.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.constants.Constants;
import com.njust.domain.User;
import com.njust.dto.UserDto;
import com.njust.entity.UserEntity;
import com.njust.mapper.RoleMapper;
import com.njust.mapper.UserMapper;
import com.njust.service.UserService;
import com.njust.utils.AppMd5Utils;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User queryUserByPhone(String phone) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq(User.COL_PHONE,phone);
        qw.eq(User.COL_STATUS,0);
        User user = this.userMapper.selectOne(qw);
        return user;
    }

    @Override
    public User getOne(Long userId) {
        return this.userMapper.selectById(userId);
    }

    @Override
    public DataGridView listUserForPage(UserDto userDto) {
        Page<User> page=new Page<>(userDto.getPageNum(), userDto.getPageSize());
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(userDto.getUserName()), User.COL_USER_NAME, userDto.getUserName());
        qw.like(StringUtils.isNotBlank(userDto.getPhone()), User.COL_PHONE, userDto.getPhone());
        qw.eq(StringUtils.isNotBlank(userDto.getStatus()), User.COL_STATUS, userDto.getStatus());
        qw.ge(null!= userDto.getBeginTime(), User.COL_CREATE_TIME, userDto.getBeginTime());
        qw.le(null!= userDto.getEndTime(), User.COL_CREATE_TIME, userDto.getEndTime());
        qw.orderByAsc(User.COL_USER_ID);
        this.userMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int addUser(UserDto userDto) {

        String name=this.userMapper.selectExit(userDto.getPhone());
        if (name==null) {
            User user=new User();
            BeanUtil.copyProperties(userDto, user);
            user.setUserType(Constants.USER_NORMAL);//用户类型是系统用户
            String defaultPwd = user.getPhone().substring(5);
            user.setCreateBy(userDto.getSimpleUser().getUserName());
            user.setCreateTime(DateUtil.date());
            user.setSalt(AppMd5Utils.createSalt());
            user.setPassword(AppMd5Utils.md5(defaultPwd, user.getSalt(), 2));
            return this.userMapper.insert(user);
        }else {
            return 520;
        }
    }

    @Override
    public int updateUser(UserDto userDto) {
        User user=this.userMapper.selectById(userDto.getUserId());
        if(null==user){
            return 0;
        }
        BeanUtil.copyProperties(userDto,user);
        //设置修改人
        user.setUpdateBy(userDto.getSimpleUser().getUserName());
        return this.userMapper.updateById(user);
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        //根据用户ids删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUserIds(ids);
        return this.userMapper.deleteBatchIds(ids);
    }

    @Override
    public void resetPassWord(Long[] userIds) {
        for (Long userId : userIds) {
            User user=this.userMapper.selectById(userId);
            String defaultPwd="";
            //如果是超级 管理员
            if (user.getUserType().equals(Constants.USER_ADMIN)){
                defaultPwd="123456";
            }else{
                defaultPwd=user.getPhone().substring(5);
            }
            user.setSalt(AppMd5Utils.createSalt());
            user.setPassword(AppMd5Utils.md5(defaultPwd,user.getSalt(),2));
            this.userMapper.updateById(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq(User.COL_STATUS, Constants.STATUS_TRUE);
        qw.eq(User.COL_USER_TYPE,Constants.USER_NORMAL);
        qw.orderByAsc(User.COL_USER_ID);
        return this.userMapper.selectList(qw);
    }

    @Override
    public void updateUserTime(User user) {
        //上次登陆时间
         this.userMapper.updateById(user);
    }

    @Override
    public void updatePwd(Long userId, String newPassword) {
        User nowUser=this.userMapper.selectById(userId);
        nowUser.setSalt(AppMd5Utils.createSalt());
        String defaultPwd=newPassword;
        nowUser.setPassword(AppMd5Utils.md5(defaultPwd,nowUser.getSalt(),2));
        this.userMapper.updateById(nowUser);
    }

    @Override
    public List<UserEntity> addUserExcel(List<UserEntity> userEntityList) {
        //重复的数据集合
        List<UserEntity> userSameList=new ArrayList<>();
        for (UserEntity userEntity :userEntityList) {
            String selectExit = this.userMapper.selectExit(userEntity.getPhone());
            if (selectExit==null) {
                User user = new User();
                BeanUtil.copyProperties(userEntity, user);
                //用户类型是系统用户
                user.setUserType(Constants.USER_NORMAL);
                String defaultPwd = user.getPhone().substring(5);
                user.setCreateBy("excel导入");
                user.setCreateTime(DateUtil.date());
                user.setSalt(AppMd5Utils.createSalt());
                user.setPassword(AppMd5Utils.md5(defaultPwd, user.getSalt(), 2));
                this.userMapper.insert(user);
            }else {
                userSameList.add(userEntity);
            }
        }
        return userSameList;

    }


}
