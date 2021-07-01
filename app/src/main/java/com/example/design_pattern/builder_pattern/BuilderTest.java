package com.example.design_pattern.builder_pattern;

import com.example.design_pattern.R;

/**
 * 链式掉用的Builder模式
 * Created by yzh on 2021/7/1 16:04.
 */
class BuilderTest {
    //一个类的构造函数参数较多，而且这些参数有些是必传与非必传的参数时，考虑这种使用构造者模式。可以按需添加属性
    Person mPerson=new Person.Builder("阿三")
            .setGender("男")
            .setAge(18)
            .setHeight(175)
            .build();

//android里的典型Builder案例：AlertDialog
//    new AlertDialog.Builder(this)
//            .setTitle("标题")
//            .setIcon(R.mipmap.ic_launcher)
//            .setMessage("测试")
//            .create()
//            .show();
}
