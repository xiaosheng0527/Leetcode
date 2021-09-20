package leetcode.editor.cn;

//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点数目在范围 [0, 104] 内 
// 1 <= Node.val <= 50 
// 0 <= val <= 50 
// 
// Related Topics 递归 链表 
// 👍 700 👎 0

import java.util.LinkedList;


public class RemoveLinkedListElements{
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
        int[] arr = {1,2,6,3,4,5,6} ;
        ListNode listNode = new ListNode(arr) ;
        solution.removeElements(listNode, 6) ;

        //solution.removeElements(linkedList,2) ;
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

public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头节点
    public ListNode(int[] arr){
        if(arr == null || arr.length == 0)
            // throw new IllengalArgumentException("arr can not be empty");
            // 这里会报错，所以，不需要使用异常
            return;

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 这里使用虚节点，为了删除头结点的方便，否则头结点会和删除其他节点不同
        // 1 <= Node.val <= 50 ,故这里为了不和val冲突，此时虚节点的数据域设置为-1
        // 此时的虚节点dummy 连接到 head 节点
        // 注意：虚节点不能动，否则我们就无法找到头结点了，如果想要遍历链表，可以再定义辅助节点
        ListNode dummy = new ListNode(-1,head) ;

        if(head == null){
            return head ;
        }

        ListNode cur = dummy ;

        while(cur.next != null){    // 为了遍历整个链表

            // 这里要考虑的一点是，如果要删除的节点是连续的，那我这里只删除一个，就会造成结果不严谨
            while(cur.next != null && cur.next.val == val){
                cur.next = cur.next.next ;
            }

            // 无论是要删除的节点，还是并非要删除的节点，在遍历不是之后，需要向后搜索
            // 这里需要加一个条件，如果cur遍历到最后一个节点，此时无需后移
            if(cur.next != null){
                cur = cur.next ;
            }

        }

        return dummy.next ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

