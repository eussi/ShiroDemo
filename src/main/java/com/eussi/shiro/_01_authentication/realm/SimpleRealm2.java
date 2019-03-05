package com.eussi.shiro._01_authentication.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by wangxueming on 2019/1/28.
 */
public class SimpleRealm2 implements Realm {
    @Override
    public String getName() {
        return "SimpleRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken 类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        String username = (String)token.getPrincipal(); //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码

        if(!"wangxm2".equals(username)) {
            throw new UnknownAccountException("用户名错误"); //如果用户名错误
        }
        if(!"12345".equals(password)) {
            throw new IncorrectCredentialsException("密码错误"); //如果密码错误
        }
//        throw new IncorrectCredentialsException("密码错误"); //如果密码错误

        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}