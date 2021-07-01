# Design pattern
 
 

### 一、singleton pattern（单例模式）

> 懒汉与恶汉单例模式因为缺陷明显实际运用上很少，便不再列举这里使用双重校验单例模式：
```java
/**双重校验 的单例模式
 * Created by yzh on 2021/7/1 16:34.
 */
class MyUtils {
    private volatile static MyUtils singleton;
    private MyUtils (){}
    public static MyUtils getInstance() {
        //外面在套一层检查为空的情况下在走同步，目的是为了避免同步带来的性能影响。在多线程下，每个线程一进来就加同步比较影响性能
        //因为后面的线程需要等前面的线程执行完了同步块才能执行。
        if (singleton == null) {
            //添加同步块，多线程掉用时保证线程安全
            synchronized (MyUtils.class) {
                //初次检查，为空才创建
                if (singleton == null) {
                    singleton = new MyUtils();
                }
            }
        }
        return singleton;
    }

    public void test(){
        System.out.println("test");
    }
}
```
- 调用测试：

```java
 MyUtils.getInstance().test();
```
 

### 二、singleton pattern（builder模式）
> 这里列举常用的链式掉用的builder模式，在android才用这种模式的典型案例AlertDialog：
 ```java
    new AlertDialog.Builder(this)
            .setTitle("标题")
            .setIcon(R.mipmap.ic_launcher)
            .setMessage("测试")
            .create()
            .show();
```
builder模式实现：
```java
/**
 *一个类的构造函数参数较多，而且这些参数有些是必传与非必传的参数时，考虑这种使用构造者模式。可以按需添加属性
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
```
- 调用测试：
```java
Person mPerson=new Person.Builder("阿三")
        .setGender("男")
        .setAge(18)
        .setHeight(175)
        .build();
```

### 三、observer pattern（观察者模式）
>当对象间存在一对多关系时则使用观察者模式. 一个对象被修改时，则会自动通知依赖它的对象。
这里以学生老师做比喻 观察者是学生有多个，被观察的对象是老师。 老师布置作业后所有的学生将会收到通知开始做作业。

- 1、先定义一个抽象的观察者
```java
/**
 * 抽象观察者Observer
 * Created by yzh on 2021/7/1 15:18.
 */
interface Observer {
    void doHomeWork(int i);
}
```
- 2、定义一个观察者,学生 实现抽象方法：做作业
```java

class Student implements Observer {
    @Override
    public void doHomeWork(int i) {
        System.out.printf("学生%d收到通知开始做作业%n",i);
    }
}
```
- 3、定义一个被观察者 -老师
```java
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
```

- 4、调用测试：
```java
    public static void main(String[] args) {
        //1、初始化一个被观察的对象（老师）
        Teacher teacher=new Teacher();

        //2、模拟一堆观察者（学生）去订阅老师
        for (int i = 0; i <5 ; i++) {
            Student student=new Student();
            teacher.addObserver(student);
        }
        //3、被观察者老师 执行布置作业动作时 即循环遍历通知学生开始写作业，学生观察到老师的动作便开始写作业
        teacher.addHomework();
    }
```

```text
  打印结果：
    老师布置了作业~~
    学生0收到通知开始做作业
    学生1收到通知开始做作业
    学生2收到通知开始做作业
    学生3收到通知开始做作业
    学生4收到通知开始做作业
```


 

### 四、proxy pattern（代理模式）
>使用代理模式主要有两个目的：
 一是保护目标对象，不直接操作功能类UserImpl，让其功能更加纯粹。
 二是增强目标对象功能：给实际功能类UserImpl，套一个中介UserProxy类 提供额外的其它功能。
 
 - 1、 新建代理模式的接口类UserService ，代理的类和被代理的类都需要实现此接口
```java
interface UserService {
    void select();
    void update();
}
```
 - 2、新建被代理对象UserImpl --即UserService的具体实现类
 ```java
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
```
 - 3、新建一个代理对象UserProxy
 ```java
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
```
- 4、 调用测试：
```java
UserService userService = new UserImpl();
UserProxy userProxy = new UserProxy(userService);
userProxy.select();
```
```text
  打印结果：
    静态代理-----方法执行前 添加自己的额外方法
    查询~
    静态代理-----方法执行后 添加自己的额外方法
```



