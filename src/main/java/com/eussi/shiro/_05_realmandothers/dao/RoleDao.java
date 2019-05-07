package com.eussi.shiro._05_realmandothers.dao;


import com.eussi.shiro._05_realmandothers.entity.Role;

/**
 * Created by wangxueming on 2019/3/20.
 */
public interface RoleDao {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
