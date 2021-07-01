package com.example.design_pattern.proxy_pattern;

/**
 * Created by yzh on 2021/7/1 15:31.
 */
class ProxyTest {
    public static void main(String[] args) {
        //使用代理模式主要有两个目的：
        // 一是保护目标对象，不直接操作功能类UserImpl，让其功能更加纯粹。
        // 二是增强目标对象功能：给实际功能类UserImpl，套一个中介UserProxy类 提供额外的其它功能。
        UserService userService = new UserImpl();
        UserProxy userProxy = new UserProxy(userService);
        userProxy.select();
    }
}
