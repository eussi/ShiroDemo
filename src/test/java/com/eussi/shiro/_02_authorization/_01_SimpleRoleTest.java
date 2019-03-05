package com.eussi.shiro._02_authorization;

import com.eussi.shiro.base.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangxueming on 2019/1/28.
 */
public class _01_SimpleRoleTest extends BaseTest {

    @Test
    public void testHasRole() {

        //基于角色的访问控制（即隐式角色）
        //粗粒度造成
        login("classpath:_02_authorization/shiro-role.ini", "wangxm", "12345");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    //checkRole/checkRoles 和hasRole/hasAllRoles 不同的地方是它在判断为假的情
    //况下会抛出UnauthorizedException异常

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        login("classpath:_02_authorization/shiro-role.ini", "wangxm2", "12345");
        //断言拥有角色：role1
        subject().checkRole("role1");
        //断言拥有角色：role1 and role3 失败抛出异常
        subject().checkRoles("role1", "role3");
    }

}
