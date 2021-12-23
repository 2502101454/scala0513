/**
 * @author zengwang
 * @create 2021-10-26 10:39 下午
 * @function:
 */
public class Student {
    private String name;
    private Integer age;
    // 静态变量，全局只有一份，不属于对象，只能用类去调用
    private static String school = "atguigu";

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void printInfo(){
        System.out.println(this.name + " " + this.age + " " + Student.school);
    }
    /* 面向对象中，任何方法、属性的调用都应该通过对象调用，而上面的school属性则体现了java不够面向对象
    所以，scala中去掉了static关键字
     */
    public static void main(String[] args) {
        Student alice = new Student("alice", 20);
        Student bob = new Student("bob", 23);
        alice.printInfo();
        bob.printInfo();
    }
}
