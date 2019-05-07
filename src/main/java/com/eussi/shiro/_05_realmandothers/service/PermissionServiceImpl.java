package com.eussi.shiro._05_realmandothers.service;


import com.eussi.shiro._05_realmandothers.dao.PermissionDao;
import com.eussi.shiro._05_realmandothers.dao.PermissionDaoImpl;
import com.eussi.shiro._05_realmandothers.entity.Permission;

/**
 * Created by wangxueming on 2019/3/20.
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
