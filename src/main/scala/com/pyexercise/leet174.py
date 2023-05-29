"""
骑士救公主

二维数组，从左上角开始，要到右下角，求所需最少的体力值

分析： 动态规划

1.初始化dp数组
dp[i][j] 代表到达[i][j]位置后，所需的最少体力

2.转换方程
当前位置的最少体力消耗 = Min(上边位置的最小消耗 , 右边的最小消耗) + 当前位置自身的消耗   if cost[i][j] < 0
                    else Min(上边位置的最小消耗 , 右边的最小消耗) + 当前位置自身的消耗

注意，如果当前位置 是 加血的，则不计入体力消耗
比如：走到上边位置的最小消耗是-10， 当前位置是5，如果计入的话，-10 + 5 = -5
-5 根本不够支持骑士走到上边位置，骑士就死亡了

dp[i][j] = min(dp[i-1][j], dp[i][j-1]) +


拉闸!! 想错了，没这么简单的
"""