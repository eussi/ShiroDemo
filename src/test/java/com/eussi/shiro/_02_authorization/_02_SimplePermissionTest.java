package com.eussi.shiro._02_authorization;

import com.eussi.shiro.base.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Test;

/**
 * Created by wangxueming on 2019/1/28.
 */
public class _02_SimplePermissionTest extends BaseTest {

    @Test
    public void testIsPermitted_123_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm", "12345");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission_123_2() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm", "12345");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }

    @Test
    public void testWildcardPermission_4_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm1", "12345");

        subject().checkPermissions("system:user:update", "system:user:delete");
        //验证会出错，不等价
//        subject().checkPermissions("system:user:update,delete");
    }
    @Test
    public void testWildcardPermission_4_2() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm2", "12345");

        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission_5_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm3", "12345");

        subject().checkPermissions("system:user:create,delete,update:view");
        //不等价
//        subject().checkPermissions("system:user:*");
//        subject().checkPermissions("system:user");
    }

    @Test
    public void testWildcardPermission_5_2() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm4", "12345");

        subject().checkPermissions("system:user:create,delete,update:view");
        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    @Test
    public void testWildcardPermission_6_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm5", "12345");

        subject().checkPermissions("user:view");
//        subject().checkPermissions("system:user:view");
    }

    @Test
    public void testWildcardPermission_6_2() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm6", "12345");

//        subject().checkPermissions("user:view");
        subject().checkPermissions("system:user:view");
    }

    @Test
    public void testWildcardPermission_7_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm7", "12345");
        subject().checkPermissions("user:view:1");
    }

    @Test
    public void testWildcardPermission_7_2() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm8", "12345");

        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
    }

    @Test
    public void testWildcardPermission_7_3() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm9", "12345");

        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
    }

    @Test
    public void testWildcardPermission_7_4() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm10", "12345");

        subject().checkPermissions("user:auth:1", "user:auth:2");

    }

    @Test
    public void testWildcardPermission_7_5() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm11", "12345");

        subject().checkPermissions("user:auth:1", "user:add:2");

    }

    @Test
    public void testWildcardPermission_8_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm12", "12345");

        //前缀匹配
        subject().checkPermissions("menu:view:1");
        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

    }


    @Test
    public void testWildcardPermission_9_1() {
        login("classpath:_02_authorization/shiro-permission.ini", "wangxm12", "12345");

        //等价
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));
    }
}
