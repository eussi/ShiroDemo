package com.eussi.shiro._05_realmandothers.service;


import com.eussi.shiro._05_realmandothers.entity.Role;

/**
 * Created by wangxueming on 2019/3/20.
 */
public interface RoleService {


    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
