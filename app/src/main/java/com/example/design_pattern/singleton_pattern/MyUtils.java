package com.example.design_pattern.singleton_pattern;

/**双重校验 的单例模式
 * Created by yzh on 2021/7/1 16:34.
 */
class MyUtils {
    private volatile static MyUtils singleton;
    private MyUtils (){}
    public static MyUtils getInstance() {
        //外面在套一层检查为空的情况下在走同步，目的是为了避免同步带来的性能影响。 在多线程下，每个线程一进来就加同步比较影响性能
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
