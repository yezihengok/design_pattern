package com.example.design_pattern.observer_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者 -老师
 * Created by yzh on 2021/7/1 15:11.
 */
public class Teacher {
    private List<Observer> observers = new ArrayList<>();
    //增加一个观察者
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    //移除
    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }

    //通知所有观察者
    public void notifyObserver() {
        for (int i = 0; i <observers.size() ; i++) {
            observers.get(i).doHomeWork(i);
        }
    }

    public void addHomework(){
        System.out.println("老师布置了作业~~");
        notifyObserver();
    }
}
