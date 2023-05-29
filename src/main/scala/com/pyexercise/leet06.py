"""
Z字形 字符串，变换
原字符串是按照Z字形存储的，现在让你返回按行存储的字符串

比如原子符传: LEETCODEISHIRING，按Z字存储的行数为rowNum, 存储形式如下
L   D   R
E  OE  II
E C I H N
T   S   G

我们现在要按行读取，返回行拼接的字符串: LDREOEIIECIHNTSG

分析：
1.已知有rowNum行，那么我们产出也是rowNum个字符串，每个代表一行，最后把他们拼一起就行
2.提前声明有rowNum个空串，代表每行初始值，都放到数组里面，记做A
3.为A中的串进行更新(不断的取s中的字符), 可以拆分为两个方向，
    从上到下：为下标为 0 ~ rowNum-1 的空串进行更新
    从下到上：只需要为下标为 rowNum-2 ~ 1 的空串进行更新（边界不重复遍历）

    如此往复

新的思维模式： 在内层循环中，我们去修改外层循环的变量 (以往总是先从外层循环变量去推到内层循环变量)

"""


class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """

        if numRows <= 1:
            return s
        s_len = len(s)
        # 初始化每行对应的空串
        res = ['' for i in range(numRows)]
        length = len(res)
        # s的起点下标
        i = 0
        # 主体是 res 自身，而不是原字符串，res中每行依次取字符才是驱动循环的灵魂
        while i < s_len:
            # 从上到下
            for j in range(length):
                if i >= s_len:
                    break
                res[j] = res[j] + s[i]
                i += 1


            # 从下到上
            j = length - 2
            while j >= 1:
                if i >= s_len:
                    break
                res[j] = res[j] + s[i]
                j -= 1
                i += 1

        return "".join(res)


if __name__ == '__main__':
    solution = Solution()
    res = solution.convert("PAYPALISHIRING", 4)
    print(res)