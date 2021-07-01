package com.example.design_pattern.observer_pattern;

/**
 * 具体观察者-学生
 * Created by yzh on 2021/7/1 15:23.
 */
class Student implements Observer {
    @Override
    public void doHomeWork(int i) {
        System.out.printf("学生%d收到通知开始做作业%n",i);
    }
}
