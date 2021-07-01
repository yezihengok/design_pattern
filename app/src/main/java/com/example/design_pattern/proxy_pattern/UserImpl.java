package com.example.design_pattern.proxy_pattern;

/**
 * 被代理对象 -User接口的实现类
 * Created by yzh on 2021/7/1 14:32.
 */
class UserImpl implements UserService {
    @Override
    public void select() {
        System.out.println("查询~");
    }

    @Override
    public void update() {
        System.out.println("更新~");
    }
}
