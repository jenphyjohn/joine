package com.github.joine.business.service;

import com.github.joine.business.domain.User;

import java.util.List;

/**
 * 终端用户 服务层
 *
 * @author JenphyJohn
 * @date 2019-04-17
 */
public interface IUserService {

    /**
     * 查询终端用户信息
     *
     * @param userId 终端用户ID
     * @return 终端用户信息
     */
    User selectUserById(Long userId);

    /**
     * 查询终端用户列表
     *
     * @param user 终端用户信息
     * @return 终端用户集合
     */
    List<User> selectUserList(User user);

    /**
     * 新增终端用户
     *
     * @param user 终端用户信息
     * @return 结果
     */
    int insertUser(User user);

    /**
     * 修改终端用户
     *
     * @param user 终端用户信息
     * @return 结果
     */
    int updateUser(User user);

    /**
     * 删除终端用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserByIds(String ids);

    User selectUserByLoginName(User user);

}
