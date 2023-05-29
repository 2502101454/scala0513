class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        str_nums = len(strs)
        # 由于后面要频繁使用到各个字符串的长度，做判断，所以提前缓存一下
        len_strs = {}
        for i in range(str_nums):
            len_strs[i] = len(strs[i])
        # 收集前缀
        prefixs = []
        target_len = len_strs[0]
        # 选取第一个串为目标串，开始比较，公共前缀的最好情况下，最长长度就是该串的长度
        for i in range(target_len):
            flag = True
            pf = strs[0][i]

            # 对于后面的每个字符串，依次比较，长度、字符值
            for j in range(1, str_nums):
                if (i >= len_strs[j]) or (pf != strs[j][i]):
                    flag = False
                    break

            if not flag:
                break

            prefixs.append(pf)

        return "".join(prefixs)


if __name__ == '__main__':
    solution = Solution()
    strs = ["dog","racecar","car"]
    res = solution.longestCommonPrefix(strs)
    print(res)