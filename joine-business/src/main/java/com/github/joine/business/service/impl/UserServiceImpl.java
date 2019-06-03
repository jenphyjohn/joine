package com.github.joine.business.service.impl;

import com.github.joine.business.domain.User;
import com.github.joine.business.mapper.UserMapper;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 终端用户 服务层实现
 *
 * @author JenphyJohn
 * @date 2019-04-17
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询终端用户信息
     *
     * @param userId 终端用户ID
     * @return 终端用户信息
     */
    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 查询终端用户列表
     *
     * @param user 终端用户信息
     * @return 终端用户集合
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增终端用户
     *
     * @param user 终端用户信息
     * @return 结果
     */
    @Override
    public int insertUser(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insertUser(user);
    }

    /**
     * 修改终端用户
     *
     * @param user 终端用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user) {
        user.setUpdateTime(new Date());
        return userMapper.updateUser(user);
    }

    /**
     * 删除终端用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) {
        return userMapper.deleteUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据用户名查询用户
     *
     * @param loginName
     * @return
     */
    @Override
    public User selectUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName(loginName);
    }

    /**
     * 根据openid查询用户
     *
     * @param openid
     * @return
     */
    @Override
    public User selectUserByOpenid(String openid) {
        return userMapper.selectUserByOpenid(openid);
    }

}
