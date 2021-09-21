package leetcode.editor.cn;

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 1886 👎 0

import java.util.List;

public class ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    // 递归
    public ListNode reverseList(ListNode head) {

        // 将链表长度为0或者 链表长度为1 的 链表分开考虑
        // 这样可以减少进入 递归函数 判断的时间
        if(head == null || head.next == null){
            return head ;
        }

        return reverse(null, head) ;

    }

    private ListNode reverse(ListNode prev,ListNode cur) {

        // 注意，如果是递归结束后，此时cur == null ，此时的prev指针刚好就是指向反转链表的 有效头节点
        // 之后会一直 递归返回，此时一直弹栈，直到最后 返回的 prev 值就是 递归深处的 prev指针指向的节点
        if(cur == null){
            return prev ;
        }

        ListNode temp = null ;
        temp = cur.next ;   // 先保存下一个节点
        cur.next = prev ;   // 反转
        // 更新prev、cur位置
        prev = cur;
        cur = temp;
        return reverse(prev, cur) ;


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

