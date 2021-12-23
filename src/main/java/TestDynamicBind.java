/**
 * @author zengwang
 * @create 2021-11-06 3:59 下午
 * @function:
 */
public class TestDynamicBind {
    public static void main(String[] args) {
        Worker worker = new Worker();
        System.out.println(worker.name);
        worker.hello();

        System.out.println("====================");
        Person person = new Worker();
        System.out.println(person.name); //属性是静态绑定的，在编译期间就做了
        // person.hi(); // error
        person.hello(); // 方法是动态绑定的，在运行的时候做
    }
}

class Person {
    String name = "Person";
    void hello(){
        System.out.println("hello Person");
    }
}

class Worker extends Person {
    String name = "Worker";
    void hello(){
        System.out.println("hello Worker");
    }
    void hi(){
        System.out.println("hi worker");
    }
}