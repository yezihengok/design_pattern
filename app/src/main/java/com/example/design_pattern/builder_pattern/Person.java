package com.example.design_pattern.builder_pattern;

/**
 * Created by yzh on 2021/7/1 16:08.
 */
class Person {
    //1、定义属性
    private String name;

    private String gender;
    private int age;
    private double height;

    //3.外面的Person定义一个构造函数，参数为builder并把builder里的值对应的赋给Person
    public Person(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
        this.height = builder.height;
    }

    //2、定义一个静态的Builder内部内
    static class Builder{
        private String name;//姓名 必传属性
        //非必传
        private String gender;//性别
        private int age;//年龄
        private double height;//身高

        public Builder(String name) {
            this.name = name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }

        //4、Builder添加build方法 掉用外面的Person构建函数创建Person实例，
        public Person build(){
            return new Person(this);
        }
    }
}
