package com.example.design_pattern.proxy_pattern;

/**
 * 代理对象
 * Created by yzh on 2021/7/1 14:35.
 */
public class UserProxy implements UserService {
    UserService target;//被代理的对象

    public UserProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void select() {
        before();
        target.select();
        after();
    }

    @Override
    public void update() {
        before();
        target.update();
        after();
    }

    private void before(){
        System.out.println("静态代理-----方法执行前 添加自己的额外方法");
    }

    private void after(){
        System.out.println("静态代理-----方法执行后 添加自己的额外方法");
    }
}

