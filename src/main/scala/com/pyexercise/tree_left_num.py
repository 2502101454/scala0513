class Node(object):
    def __init__(self, value, left, right):
        self.value = value
        self.left = left
        self.right = right

"""
求二叉树，最左侧节点的值
"""

def sum_left(node, sum_res):
    if node is None:
        return sum_res
    return sum_left(node.left, sum_res + node.value)


"""
错误版本最后返回None了。。。
"""
def sum_left_wrong_me(node, sum_res):
    if node is None:
        return sum_res
    sum_res += node.value
    if node.left is not None:
        return sum_left(node.left, sum_res)