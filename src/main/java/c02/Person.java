package c02;

/**
 * @author zengwang
 * @create 2022-04-13 11:13 下午
 * @desc:
 */
public class Person {
    String name;
    protected int id;
    private int age;
    public static String gender = "male";

    public Person() {
        this.name = "default";
        name = "default no this";
    }
    private Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public void info() {
        String  mess = "info was recalled";
        System.out.println(mess);
    }

    public static void main(String[] args) {
        Person wz = new Person("wz", 10, 20);
        System.out.println(wz.name);
        System.out.println(wz.gender);
        wz.info();


    }
}

class Hello {
    // 各种方法名相同，但各自的参数不同，称为方法重载(Overload)
    public void hello() {
        System.out.println("Hello, word!");
    }

    public void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public int hello(String name, int age) {
        if (age < 18) {
            System.out.println("Hi, " + name + "，" + age);
        } else {
            System.out.println("Hello, " + name + "，" + age);
        }
        return 1;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.hello();
        hello.hello("wz");
        int a = hello.hello("wz", 26);
        System.out.println(a);
    }
}

class Person2 {
    private String name;
    private int age;

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student extends Person2 {
    private int score;

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
        // 子类继承父类的所有属性和方法，包括private的属性和方法，只是因为封装性，子类无法访问而已
        // this.name = 'wz'; // 编译报错
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.score = 1;
        System.out.println(student.getScore());;
    }
}



class Person3 {
    protected String name;
    protected int age;
    public Person3() {}

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected void thanks(String name) {
        System.out.println("Person3 want to thanks to " + name);
    }
}

class Student3 extends Person3 {
    protected int score;
    // super关键字，用来访问父类的属性
    public String hello() {
        return "Hello, " + super.name; // ok!
    }

    public Student3(String name, int age, int score) {
        // super(); // 自动加上调用父类构造方法
        // 也可以显式使用super调用父类的构造方法
        super(name, age);
        this.score = score;
    }

    @Override
    public void thanks(String name) {
        System.out.println("Student3 want to thanks to " + name);
    }
    public static void main(String[] args) {
        Person3 student3 = new Student3("wz", 20, 20);
        student3.thanks("wangzeng");
    }
}
