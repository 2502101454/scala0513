"""
实验python 基本的数据结构

Python的数据结构都是动态的，不用考虑静态场景
"""


class DSAConcept(object):

    def __init__(self):
        pass

    def list_or_array(self):
        # python 数组就是列表，

        # 创建
        a = [1, 2, 3]
        b = []
        c = list([1, 2, 3])     # 不能写成list(1, 2, 3)
        print(a)    # [1, 2, 3]
        print(b)    # []
        print(c)    # [1, 2, 3]

        # 插入元素
        a.append(3)
        # 获取长度
        print(len(a))   # 4
        # 获取元素， 切片啥的就不说了
        print(a[0])     # 1
        # 删除元素
        del a[0]
        print(a)        # [2, 3, 3]

        # 集合排序，key是用那个字段排，reverse代表是否降序
        d = sorted(a, key=lambda line: line, reverse=True)
        print(d)    # [3, 3, 2]
        pass

    def set(self):
        # 去重的，无序的 集合

        # 创建
        a = {1, 3, 1}
        b = set([1, 3, 1])
        print(a)    # {1, 3}
        print(b)    # {1, 3}

        a.add(4)
        print(a)    # {1, 3, 4}
        pass

    def map(self):
        # 创建
        a = {"a": 1, "b": 2}
        b = dict({"w": 1, "v": 2})
        print(b)    # {'w': 1, 'v': 2}
        # 添加元素
        a['c'] = 3
        print(a)    # {'a': 1, 'b': 2, 'c': 3}
        # 删除
        del a['b']
        # 求长度
        print(len(a))   # 2
        pass

    def stack(self):
        # python的 栈使用list实现，LIFO
        stack = [1, 2, 4]
        # 往栈顶加入元素
        stack.append(6)
        # 从栈顶弹出元素
        print(stack.pop())  # 6
        # 求栈长度, 没有提供一个empty的方法
        print(len(stack))   # 3
        # 求栈顶元素，但不弹出
        print(stack[-1])    # 4
        pass

    def queue(self):
        # python 队列， FIFO
        from collections import deque
        # 声明一个空队列
        queue = deque()
        # 往队列中添加元素，加入队尾
        queue.append(1)
        queue.append(2)
        print(queue)    # deque([1, 2])

        # 声明一个非空队列
        queueC = deque(['a', 'b'])
        print(queueC)

        # 从队列中取元素
        print(queue.popleft())  # 1

        # 队列长度, 没有提供一个empty的方法
        print(len(queue))   # 1

        # 还支持下标自由访问
        print(queueC[0])    # a
        pass

    def forloop(self):
        a = [1, 2, 3, 4]
        # 直接遍历数组元素的方式
        for elem in a:
            print(elem)
        print("==============")
        # 使用下标遍历数组， range 默认从0开始，左闭右开，即[0, 4)
        for i in range(len(a)):
            print(f"range pos {i}")
            print(a[i])

        # 反向遍历 range(x, y) 也是要求y > x
        # 反序肯定x > y了，这时候需要设置步长为 -1，即range(x, y, -1)，
        # 依然是左闭右开，即从x 只到y - 1的位置
        for i in range(len(a) - 1, -1, -1):
            print(f"--range pos {i}")
            print(a[i])

        # 反序用while更清晰!!
        i = len(a) - 1
        while i >= 0:
            print("？？？？" + str(i))
            i -= 1

    def multi_dimension(self):
        # python 创建二维数组的正确姿势
        # 1.手写累死人
        a = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
        # 这样写是错的，里面的[0, 0, 0]只是浅copy
        b = [[0, 0, 0]] * 3
        print(a)    # [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
        b[0][1] = 1
        print(b)    # [[0, 1, 0], [0, 1, 0], [0, 1, 0]]

        # 2.常用列表生成式创建
        c = [[0, 0, 0] for i in range(3)]
        print(c)
        c[0][1] = 1
        print(c)    # [[0, 1, 0], [0, 0, 0], [0, 0, 0]]

        # 3.列表生成式套列表生成式
        d = [[0 for i in range(3)] for i in range(3)]
        print(d)    # [[0, 0, 0], [0, 0, 0], [0, 0, 0]]

    def op_comp(self):
        print(max(1,2,4))
        print(max([2,1,1]))

        print(min(1,2,4))
        print(min([2,1,1]))


if __name__ == '__main__':
    dsa = DSAConcept()
    # dsa.list_or_array()
    # dsa.set()
    # dsa.map()
    # dsa.forloop()
    # dsa.stack()
    # dsa.queue()
    # dsa.multi_dimension()
    dsa.op_comp()