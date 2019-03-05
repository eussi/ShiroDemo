package com.eussi.shiro._01_authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wangxueming on 2019/1/28.
 */
public class _02_SimipleImplRealmTest {
    @Test
    public void testImplRealm() {
        //1、获取SecurityManager工厂，使用ini配置文件初始化
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:_01_authentication/shiro-realm.ini");

        //2、将SecurityManager实例并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject并创建Token
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("wangxm", "12345");
        //login fail：Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - wangxm, rememberMe=false] did not match the expected credentials.

        UsernamePasswordToken token = new UsernamePasswordToken("wangxm", "12345");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("login fail：" + e.getMessage());
        }
        Assert.assertEquals(true, subject.isAuthenticated());

        //6、退出
        subject.logout();
    }
}
