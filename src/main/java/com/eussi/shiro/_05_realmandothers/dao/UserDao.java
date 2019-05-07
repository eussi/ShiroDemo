package com.eussi.shiro._05_realmandothers.dao;


import com.eussi.shiro._05_realmandothers.entity.User;

import java.util.Set;

/**
 * Created by wangxueming on 2019/3/20.
 */
public interface UserDao {

    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
