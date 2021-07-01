package com.example.design_pattern.observer_pattern;

/**
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。 当一个对象被修改时，则会自动通知依赖它的对象。
 * Created by yzh on 2021/7/1 15:32.
 */
class ObserverTest {
    public static void main(String[] args) {
        //1、初始化一个被观察的对象（老师）
        Teacher teacher=new Teacher();

        //2、模拟一堆观察者（学生）去订阅老师
        for (int i = 0; i <5 ; i++) {
            Student student=new Student();
            teacher.addObserver(student);
        }
        //3、被观察者老师 执行布置作业动作时 即循环遍历通知学生开始写作业
        teacher.addHomework();
    }
    //打印
//    老师布置了作业~~
//    学生0收到通知开始做作业
//    学生1收到通知开始做作业
//    学生2收到通知开始做作业
//    学生3收到通知开始做作业
//    学生4收到通知开始做作业
}
