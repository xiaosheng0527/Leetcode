package leetcode.editor.cn;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1481 👎 0

public class RemoveNthNodeFromEndOfList{
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        //solution.removeNthFromEnd()
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
*
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
    /**
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，并且first 比 second 超前 n 个节点。
     * 当 first 遍历到链表的末尾时[即first此时为null]，second 就恰好处于倒数第 n 个节点。
     * 具体地，初始时first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 n。
     * 此时，first 和 second 之间间隔了 n−1 个节点，即 first 比 second 超前了 n 个节点。
     * 在这之后，我们同时使用 first 和 second 对链表进行遍历。当 first 遍历到链表的末尾（即 first 为空指针）时，second 恰好指向倒数第 n 个节点。
     */
    // 这里利用快慢指针进行删除倒数第n个节点
    // 根据方法一和方法二，如果我们能够得到的是倒数第 n 个节点的前驱节点而不是倒数第 n 个节点的话，删除操作会更加方便。
    // 因此我们可以考虑在初始时将second 指向哑节点dummy
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head) ;
        ListNode fast = head ;
        ListNode slow = dummy ;
        int i = 0 ;   // 定义指针i，将快指针fast移动n次
        while(i < n){
            fast = fast.next ;
            i++ ;
        }
        // 跳出while循环后，此时快指针和慢指针间隔n个节点，
        // 其中的slow指向的节点作为前驱结点，便于删除

        // 此时需要判断fast指针是否到达最后最后一个节点的后一个位置
        // 若没有，则相继移动fast和slow指针
        // 否则，就说明找到了，退出这里的while循环
        while(fast != null){
            fast = fast.next ;
            slow = slow.next ;
        }
        // 跳出循环说明此时fast指向null，
        // 此时slow慢指针指向的是待删除节点【即倒数第n个节点】的前一个节点
        // 如果之前没有定义虚节点的话，那么此时slow指向的节点就是待删除的节点
        slow.next = slow.next.next ;

        return dummy.next ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

