"""
抖音二面，算法题，简化版的leetCode两数相加(leet上是两个逆序的链表)

两个数字字符串A、B，他们可以无限长，求相加结果
比如: A='123' B='41' 结果是'164'

思路：
    1.两个游标，一个指向A的末尾，一个指向B的末尾
    2.从后向前倒着加，记录进位数、保留数，收集起来
    3.把两个字符串都给遍历干净了，即两者游标都 < 0再退出，所以游标可以出界，要有补0的操作

注意：
    1.谁长谁短其实无所谓先后顺序，因为我们有补0 操作，自己举例分析即可
    2.加法的最大进位就是1了，即 9 + 9 + 1 = 19，这里的1是进位，最大的两个加数9，产生最大的进位还是1而已
"""


def two_numstr_sum(numa, numb):
    i, j = len(numa) - 1, len(numb) - 1
    flag = 0
    c = []

    while i >= 0 or j >= 0:
        add1, add2 = 0, 0
        if i >= 0:
            add1 = numa[i]

        if j >= 0:
            add2 = numb[j]

        res = int(add1) + int(add2) + flag
        # 计算进位，和保留位，举例res的三种情况，9 10 19
        flag = res // 10
        remain = res % 10

        c.append(str(remain))
        i -= 1
        j -= 1

    # 遍历干净两个字符串后，如果最后一位的sum值有进位，也得存着
    # 比如 99 + 30
    if flag > 0:
        c.append('1')

    c.reverse()
    return ''.join(c)


if __name__ == '__main__':
    res = two_numstr_sum("1299", "20")
    res = two_numstr_sum("123", "41")
    res = two_numstr_sum("123", "999")
    res = two_numstr_sum("0", "9")
    print(res)