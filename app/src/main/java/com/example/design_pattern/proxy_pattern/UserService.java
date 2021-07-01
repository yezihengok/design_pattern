package com.example.design_pattern.proxy_pattern;


/**
 * 代理模式的接口类，代理的类和被代理的类都需要实现此接口
 * Created by yzh on 2021/7/1 14:29.
 */
interface UserService {
    void select();
    void update();
}
