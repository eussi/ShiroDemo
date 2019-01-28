package com.eussi.shiro.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 *
 * Created by wangxueming on 2019/1/28.
 */
public abstract class BaseTest {

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

    public void login(String configFile, String username, String password) {
        //1、获取SecurityManager工厂，使用ini配置文件初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、将SecurityManager实例并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject并创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //4、登录，即身份验证
        subject.login(token);
    }

    @After
    public void tearDown() throws Exception {
        //退出时解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }
}
