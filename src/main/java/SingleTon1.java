/**
 * @author zengwang
 * @create 2022-03-31 11:36 上午
 * @desc:
 */
public class SingleTon1 {
    private static SingleTon1 instance = null;
    private SingleTon1() {

    }

    /** 懒汉模式：用时再创建
     * 1、适用于单线程的单例模式(面试就别写这个)
     * @return
     */
    public static SingleTon1 getInstanceA() {
        if (instance == null) {
            instance = new SingleTon1();
        }
        return instance;
    }

    /** 懒汉模式
     * 2、多线程场景的单例模式，这样加锁但效率低(面试别写这个)
     * @return
     */
    public static synchronized SingleTon1 getInstanceB() {
        if (instance == null) {
            instance = new SingleTon1();
        }
        return instance;
    }

    /**
     * 懒汉模式：面试写这个
     * 3、多线程场景的单例模式，双重检查加锁，效率更高
     * @return
     */
    public static SingleTon1 getInstanceC() {
        if (instance == null) {
            synchronized (SingleTon1.class) {
                if (instance == null) {
                    instance = new SingleTon1();
                }
            }
        }
        return instance;
    }
}
