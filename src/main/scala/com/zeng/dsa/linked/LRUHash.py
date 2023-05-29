"""
Leetcode 146
put(self, key, value) key 存在，则覆盖value, 说明key在链表中是唯一的

思路:
    1.单链表在找key时，总是O(1)的复杂度，可以使用Hash表<Key, KNode>的方式，减少为O(1)
    Hash表维护当前链表中的节点情况
    2.当要删除某个节点时，还要找到其pre节点，单链表也是O(n)，改成双链表，减少O(1)
    3.链表头部存最新访问的节点，尾部存先前访问的节点：
        当需要删除尾部时，尽管是双链表，也得从头遍历到尾部后才能确定尾结点
        可以单独维护伪尾节点tail(就和sentinel一样)， tail.pre = 尾结点

流程：
    1.初始化Map<key, KNode>、capacity、node_nums、双链表sentinel和tail节点
    2.get(k)时，从map中取：
        存在，return v，链表中调整KNode位置
        不存在，return -1

    3.put(k, v)时，从map中找:
        存在，覆盖v，链表中调整KNode位置
        不存在，要插入头部：
            3.1 缓存不满，直接插入(更新Map和链表)
            3.2 缓存已满，删除尾部后(更新Map和链表)，再直接插入

自己面试时候，可以画图分析下，别干巴巴硬想~

"""


class Node(object):

    def __init__(self, key=None, value=None):
        self.key = key
        self.value = value
        self.next = None
        self.pre = None


class LRUCache(object):
    """
    维护的双链表，头部是最新访问的节点，尾部是最早访问的节点,
    面试记忆法： 在不在都先从hash表中找，之后在考虑是否满capacity的case
    """
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.node_nums = 0
        self.map = {}
        self.sentinel = Node()
        self.tail = Node()
        # 注意初始时，两个伪节点是双链表结构
        self.sentinel.next = self.tail
        self.tail.pre = self.sentinel

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """

        # 优先从hash表中找
        if key in self.map:
            # 在hash表中，需要取出该节点，做链表删除后，再插入头部
            node = self.map.get(key)
            self.delete_node(node)
            self.insert_head_node(node)
            return node.value

        return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        # 先找hash表
        if key in self.map:
            # 在里面，就直接覆盖value值，然后调整节点到头部
            node = self.map.get(key)
            node.value = value
            self.delete_node(node)
            self.insert_head_node(node)
        else:
        # 不在里面，需要插入
            if self.node_nums < self.capacity:
                # 缓存不满，直接插入头部，发现代码复用

                # node = Node(key, value)
                # self.map[key] = node
                # self.insert_head_node(node)
                self.node_nums += 1
            else:
                # 缓存已满，删除尾部节点，再插入新node到头部

                delete_node = self.tail.pre
                self.delete_node(delete_node)
                # 去掉hash表中的引用
                self.map.pop(delete_node.key)
                del delete_node

            node = Node(key, value)
            self.map[key] = node
            self.insert_head_node(node)

    def delete_node(self, node):
        """
        双链表中删除一个节点
        :param node:
        :return:
        """
        pre_node = node.pre
        next_node = node.next
        # 连接前后节点
        pre_node.next = next_node
        next_node.pre = pre_node
        # 该节点自身前后引用可以摘可不摘
        # node.next = None
        # node.pre = None
        pass

    def insert_head_node(self, node):
        """
        双链表头部插入一个节点
        :param node:
        :return:
        """
        next_node = self.sentinel.next

        self.sentinel.next = node
        node.next = next_node
        next_node.pre = node
        node.pre = self.sentinel
