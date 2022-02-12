package com.zeng.dsa.byteOP;

/**
 * @author zengwang
 * @create 2022-02-09 8:49 下午
 * @desc:
 */
public class Offer15 {
    public static void main(String[] args) {
        Offer15 offer15 = new Offer15();
        System.out.println(offer15.hammingWeight(5));
    }

    /**
     * 输入的是一个整数n，求这个整数n中的二进制位中，1的个数
     *
     * 分析：对于任意整数n，它的二进制位记做A，n-1的二进制位记做B
     *
     * B是A的最右边的1变成0之后，其后所有的位置都变成1
     * 比如n: 4  n-1: 3
     *    100   011         A&B = 000
     *
     * n: 5     n-1: 4
     * 101      100         A&B = 100
     *
     * n: 10    n-1: 9
     * 1010     1001        A&B = 1000
     *
     *
     * 所以A 和 B 做按位与操作，结果记做C，C和A相比，是消除了A的最右边的1。
     * 利用这个思路，每次消除一个1，直到最后消除所有的1，成了0，则退出
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        // while (n > 0) { 不是很懂为啥 > 0不能AC，因为无符号不区分没有负数的概念? 先拉闸
        while (n != 0) {
            // 消一次最右边的1
            n = n & (n - 1);
            i += 1;
        }
        return i;
    }
}
