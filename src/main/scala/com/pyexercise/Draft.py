import random


class Solution(object):
    def sortArrayQS(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        self.qs(nums, 0, len(nums)-1)
        return nums

    def qs(self, nums, start, end):
        """
        快速排序，先排后分
        排成 左边小 右边大
        m = partition(start, end)
        qs(start, end) = qs(start, m-1) + qs(m+1, end)
        """
        if start >= end:
            return
        m = self.partition(nums, start, end)
        self.qs(nums, start, m-1)
        self.qs(nums, m+1, end)

    def partition(self, nums, start, end):
        """
        选择排序的思想，i维护小于pivot的值下标，j从头开始遍历;
        遍历结束后，[0, i-1]一定都是< pivot的，[i, end] 一定都是 >= pivot

        让pivot移动到中间 才能左小，pivot，右大


        得带点优化：pivot不随机会出现超时的提交:
        1.随机选择一个p下标
        2.让p元素和末尾元素交换，pivot再选择为末尾元素，可以接着排序
        """
        # 返回[start, end] 闭区间中的随机整数
        p = random.randint(start, end)
        nums[p], nums[end] = nums[end], nums[p]
        pivot = nums[end]
        i, j = start, start
        for j in range(start, end + 1):
            if nums[j] < pivot:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1

        nums[i], nums[end] = nums[end], nums[i]
        return i

    def sortArrayMS(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        self.ms(nums, 0, len(nums)-1)
        return nums

    def ms(self, nums, start, end):
        """
        归并排序：先分再排
        1.分到最后，认为1个元素，自身就是有序的，然后返回;
        2.在回溯的过程中，进行合并两个有序序列
        :param nums:
        :param start:
        :param end:
        :return:
        """
        if start >= end:
            return
        m = start + (end - start) // 2
        self.ms(nums, start, m)
        self.ms(nums, m+1, end)

        self.merge_seq(nums, start, m, m+1, end)

    def merge_seq(self, nums, s1, e1, s2, e2):
        """
        合并两端有序序列，并覆盖掉原有的数组
        :param nums:
        :param s1:
        :param e1:
        :param s2:
        :param e2:
        :return:
        """
        res = []
        l1 = s1
        while s1 <= e1 and s2 <= e2:
            if nums[s1] < nums[s2]:
                res.append(nums[s1])
                s1 += 1
            else:
                res.append(nums[s2])
                s2 += 1

        while s1 <= e1:
            res.append(nums[s1])
            s1 += 1

        while s2 <= e2:
            res.append(nums[s2])
            s2 += 1

        # 覆盖原数组
        for i in range(0, len(res)):
            nums[l1 + i] = res[i]

    def sortArrayHS(self, nums):
        """
        堆排序
        :param nums:
        :return:
        """
        return self.heap_sort(nums)

    def heap_sort(self, nums):
        """
        堆排序，从小到大的话，就是大顶堆：
        1.建堆，从最后一个父节点开始 自上而下堆化
        2.排序，不断交换堆顶元素和堆尾元素，做自上而下堆化，直到堆中只有一个元素结束
        :param nums:
        :return:
        """
        end = len(nums) - 1
        # 堆头从下标0开始存，父节点的计算公式
        i = (end - 1) // 2
        # 建堆
        while i >= 0:
            self.heapfiy(nums, i, end)
            i -= 1

        # 排序，end 是下标，0代表有一个元素，while end > 0，表示最后堆大小为1是退出
        while end > 0:
            # 交换后，堆尾即为最大元素，做堆化，堆顶又是次大元素了，然后堆大小减一。重复此过程
            nums[0], nums[end] = nums[end], nums[0]
            self.heapfiy(nums, 0, end-1)
            end -= 1
        return nums

    def heapfiy(self, nums, start, end):
        """
        对数组 [start, end] 区间的数据做自上而下堆化

        大顶堆 -- 当前节点的值 >= 左右子节点的值
        堆化思路：
            当不满足这个关系的时候，就继续比较交换，满足则停止;
        做法：求当前节点、其左子节点、其右子节点，三者中的最大值，两两比较
        :param nums:
        :param start: 开始元素
        :param end:
        :return:
        """
        max_index = start
        while True:
            # 左节点
            left = 2 * start + 1
            if left <= end and nums[left] > nums[max_index]:
                max_index = left
            # 右节点
            right = 2 * start + 2
            if right <= end and nums[right] > nums[max_index]:
                max_index = right

            # 找到最大元素
            if max_index != start:
                nums[start], nums[max_index] = nums[max_index], nums[start]
                start = max_index
            else:
                # 当前父节点已经是最大的了，关系已经满足，退出
                break


if __name__ == '__main__':
    # res = Solution().sortArrayQS([5, 2, 3, 1])
    # res = Solution().sortArrayMS([5, 2, 3, 1])
    res = Solution().sortArrayHS([5, 2, 3, 1])
    print(res)