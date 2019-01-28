package com.eussi.shiro.test;

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
public class _05_AuthenticatorTest {

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //所有Realm验证成功才算成功，且返回所有Realm身份验证成功的
        //认证信息，如果有一个失败就失败了
        PrincipalCollection principalCollection = subject.getPrincipals();

        //验证
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
        System.out.println(principalCollection.asList().get(1));
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:_05_shiro-authenticator-all-fail.ini");
    }

    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();

        //验证
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println(principalCollection.asList().get(0));
        System.out.println(principalCollection.asList().get(1));
    }

    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:_05_shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了第一个Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }



    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    private void login(String configFile) {
        //1、获取SecurityManager工厂，使用ini配置文件初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、将SecurityManager实例并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject并创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wangxm", "12345");

        //4、登录，即身份验证
        subject.login(token);
    }

    @After
    public void tearDown() throws Exception {
        //退出时解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

}
