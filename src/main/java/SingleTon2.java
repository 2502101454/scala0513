/**
 * @author zengwang
 * @create 2022-03-31 12:55 下午
 * @desc:
 */
public class SingleTon2 {
    private static SingleTon2 instance = new SingleTon2();
    private SingleTon2() {

    }

    /**
     * 饿汉模式，在类初始化的时候，自行实例化，多线程取也不影响
     * @return
     */
    public static SingleTon2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SingleTon2.getInstance();
    }
}
