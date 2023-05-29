package c02;
import java.util.Arrays;

/**
 * @author zengwang
 * @create 2022-04-06 11:02 下午
 * @desc:
 */
public class TestArray {
    public static void main(String[] args) {
        // 创建数组，3种方式
        // 先创建数组，后续再给值
        int [] ints = new int[4];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 3;
        ints[3] = 4;
//        ints = {1, 2, 3}; 报错

        // 下面两种都是创建数组时直接给值
        int [] ints2 = new int[] {1, 2 ,3,4};
        char [] chars = {'a', 'c', 'e'};
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

        // 二维数组 的创建方式 和上面一样
        // 先创建 后给值
        int [][] intsMul = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                intsMul[i][j] = i * j;
            }
        }
        // 创建并同时给值
        int [][] intsMul2 = new int[][] {{1,2}, {3,4}};
        int [][] intsMul3 = {{1,2}, {3,4}};


        // Arrays工具类使用
        int [] arr2 = new int[] {3, 1, 2, 4};
        // 判断两个数组是否相等
        System.out.println(Arrays.equals(arr2, ints2)); // false
        // 打印数组
        System.out.println(Arrays.toString(arr2)); // [3, 1, 2, 4]
        // 数组进行排序, 修改数组自身
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2)); // [1, 2, 3, 4]
    }
}
