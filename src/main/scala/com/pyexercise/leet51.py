"""
n后问题，不能同行，同列，同对角线

分析：回溯法
1.划分阶段，每一行的皇后摆放作为一个阶段
2.代码实现当前阶段的选择，承上启下
    对于n x n的棋盘，当前阶段(当前行) 可以摆放的列 有n个选择；
    但是只有选择的位置 `正确` 后，才可以继续走下一个阶段

"""
from copy import deepcopy


class Solution(object):
    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        # 初始化二维数组(棋盘)
        np = [['.' for j in range(n)] for i in range(n)]
        results = []
        self.recursive_plans(np, 0, n, results)
        return results

    def recursive_plans(self, np, i, n, results):
        """
        回溯 所有的棋盘摆法
        :param np: 棋盘
        :param i: 当前处于第i阶段, 即第i行
        :param n: 棋盘列数
        :param results: 收集结果
        :return:
        """

        # 当所有阶段遍历完毕，收集结果, 因为i从0开始
        if i >= n:
            plan = deepcopy(np)
            # 合并每一行一维数组为成字符串
            plan = ["".join(plan[x]) for x in range(n)]
            results.append(plan)
            return

        # 列出当前行的 摆法，一共有n列位置
        for j in range(n):
            # 先标记mark
            np[i][j] = 'Q'
            # 检查所选位置是否合理，如果合理才会继续下一阶段的执行
            if self.checker(i, j, n, np):
                self.recursive_plans(np, i + 1, n, results)
            # 取消mark，有两种情况:
            # 1.当前阶段内，位置选择不正确，需要重置
            # 2.当前阶段位置选择合理，但是下一个阶段调用完成后，回溯回来，当前阶段也得把使用过的位置重置
            np[i][j] = "."


    def checker(self, i, j, n, np):
        """
        检查np[i][j]位置是否合理, 画图得知，只需要检查当前行的上部分棋盘即可
        :param i: i行，第i阶段
        :param j: j列
        :param n: 棋盘宽度
        :param np:
        :return:
        """

        # i的上面一行坐标
        head_i = i - 1
        # 左边对角线 列下标
        left_j = j - 1
        # 右边对角线 列下标
        right_j = j + 1

        while head_i >= 0:
            # 同一列检查
            if np[head_i][j] == 'Q':
                return False
            # 对角线检查
            if left_j >= 0 and np[head_i][left_j] == 'Q':
                return False
            if right_j <= n - 1 and np[head_i][right_j] == 'Q':
                return False

            head_i -= 1
            left_j -= 1
            right_j += 1

        return True


if __name__ == '__main__':
    solution = Solution()
    res = solution.solveNQueens(1)
    print(res)