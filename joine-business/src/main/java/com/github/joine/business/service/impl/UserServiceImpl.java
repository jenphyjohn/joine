package com.github.joine.business.service.impl;

import com.github.joine.business.domain.User;
import com.github.joine.business.mapper.UserMapper;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.core.text.Convert;
import com.github.joine.common.exception.user.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public User selectUserByLoginName(User user) {
        List<User> users = userMapper.selectUserList(user);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

}
