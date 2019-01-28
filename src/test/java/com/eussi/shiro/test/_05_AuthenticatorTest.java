package com.eussi.shiro.test;

import com.eussi.shiro.base.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;


/**
 * Created by wangxueming on 2019/1/28.
 */
public class _05_AuthenticatorTest extends BaseTest{

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-all-success.ini", "wangxm", "12345");

        //所有Realm验证成功才算成功，且返回所有Realm身份验证成功的
        //认证信息，如果有一个失败就失败了
        PrincipalCollection principalCollection = subject().getPrincipals();

        //验证
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
        System.out.println(principalCollection.asList().get(1));
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:_05_shiro-authenticator-all-fail.ini", "wangxm", "12345");
    }

    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-atLeastOne-success.ini", "wangxm", "12345");

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject().getPrincipals();

        //验证
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
        System.out.println(principalCollection.asList().get(1));
    }

    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-atLeastTwo-success.ini", "wangxm", "12345");

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject().getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-first-success.ini", "wangxm", "12345");

        //得到一个身份集合，其包含了第一个Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject().getPrincipals();

        Assert.assertEquals(1, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
//        System.out.println(principalCollection.asList().get(1));
    }



    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-onlyone-success.ini", "wangxm", "12345");

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject().getPrincipals();

        Assert.assertEquals(1, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
    }
}
