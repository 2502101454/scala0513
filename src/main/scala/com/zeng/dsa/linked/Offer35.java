package com.zeng.dsa.linked;

import java.util.HashMap;

/**
 *
 * @author zengwang
 * @create 2022-03-02 1:07 下午
 * @desc:
 */

class Offer35 {

  public static void main(String[] args) {
    Offer35Node node = new Offer35Node(2);
  }


  /**
   * 复杂链表的复制
   * 在复杂链表中，每个节点除了有一个next指向下一个节点，还有一个random指针指向链表中的任意节点或者null
   *
   * 分析:
   * 遍历旧链表节点，不断new出新链表节点，这是比较容易实现的~
   * 关键在于： 基于旧链表的连接关系，新节点也要做出同样的连接，即旧的节点做一次连接(遍历发现)，新节点照做一次
   *
   * 注意： random指针不讲道理，可能提前创建了后面才会被遍历到的节点，
   * 因此，我们如果后面遍历到的节点，已经被创建了，那么我们应该从缓存中取出来
   *
   * 做法:
   * 如影随行，使用Hash表  {old_node: new_node, ...}
   * 1.遍历旧链表，
   *  1.1 旧链表节点不在hash表中，则把新节点进行生成，旧、新节点加入映射，走2
   *  1.2 旧链表节点在hash表中，则取出来旧、新节点，走2
   * 2.遍历的过程中，旧节点是如何连接的，新节点也照做
   *
   * null 的处理：
   * Node 类定义中，next、random默认引用就是null，对于旧节点 random是null，则新节点的random不处理也就是null了
   * @param head
   * @return
   */
  public Offer35Node copyRandomList(Offer35Node head) {
    HashMap<Offer35Node, Offer35Node> old2New = new HashMap<>();
    Offer35Node sentinel = new Offer35Node(-1);

    Offer35Node p = head;

    while (p != null) {
      // 使用next 不断向链表末尾遍历
      // 每个旧链表节点，都取自身、自身next、自身random 节点，做生成新节点、新节点进行连接(依据旧节点关系)
      if (!old2New.containsKey(p)) {
        old2New.put(p, new Offer35Node(p.val));
      }

      // 如果当前p之前被加进去过，这里也直接取出来
      Offer35Node newNode = old2New.get(p);

      // p.next 你能这么写，就代表着 这个p的 next 连接的是谁，那么新节点也要表示这个关系
      Offer35Node next = p.next;
      if (next != null) {
        if (!old2New.containsKey(next)) {
          Offer35Node newNextNode = new Offer35Node(next.val);
          // 连接next的关系
          newNode.next = newNextNode;
          old2New.put(next, newNextNode);
        } else {
          Offer35Node newNextNode = old2New.get(next);
          // 连接next的关系
          newNode.next = newNextNode;
        }
      }

      Offer35Node random = p.random;
      if (random != null) {
        if (!old2New.containsKey(random)) {
          Offer35Node newRandomNode = new Offer35Node(random.val);
          // 连接random关系
          newNode.random = newRandomNode;
          old2New.put(random, newRandomNode);
        } else {
          Offer35Node newRandomNode = old2New.get(random);
          // 连接random关系
          newNode.random = newRandomNode;
        }
      }

      p = p.next;
    }

    return old2New.get(head);
  }
}