package c02;

import scala.reflect.internal.Symbols;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @author zengwang
 * @create 2022-04-08 11:21 下午
 * @desc:
 */
public class TestExp {
    public static void main(String[] args) throws FileNotFoundException {
        // TestExp.testCatch(1);
        testThrows();
    }

    public static String testCatch(int a) {
        int b = 0;
        try{
            System.out.println("a is:" + a);
            b = 1 / a;
            return "success";
        }catch (ArithmeticException e){
            System.out.println("Exception thrown :" + e);
            return "exception";
        }finally {
            System.out.println("finally");
        }
    }

    public static void testThrows() throws FileNotFoundException {
        System.out.println("1111");
        FileInputStream fileInputStream = new FileInputStream("./test.txt");
        System.out.println("222");
    }

}

class MyException extends Exception {
    public MyException() {

    }

    public MyException(String msg) {
        super(msg);
    }
}
