package c01;

/**
 * @author zengwang
 * @create 2022-04-01 9:54 上午
 * @desc:
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World");
        System.out.println(new Hello2().a);
        for (int i = 1; i < 4; i++) {
            System.out.println(i);
        }
    }
}
class Hello2 {
    // scala类型写后面别扭啊, val name: String = "wz
    String name = "wz";
    int a = 2 > 1 ? 2 : 1;
}