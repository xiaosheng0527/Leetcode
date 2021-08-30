package leetcode.editor.cn;

//给定一个头结点为 head 的非空单链表，返回链表的中间结点。 
//
// 如果有两个中间结点，则返回第二个中间结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = 
//NULL.
// 
//
// 示例 2： 
//
// 
//输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
// 
//
// 
//
// 提示： 
//
// 
// 给定链表的结点数介于 1 和 100 之间。 
// 
// Related Topics 链表 双指针 
// 👍 375 👎 0

public class MiddleOfTheLinkedList{
    public static void main(String[] args) {
        Solution solution = new MiddleOfTheLinkedList().new Solution();
        
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
class Solution {
    /**
     * 我们可以对方法一进行空间优化，省去数组 A。
     * 我们可以对链表进行两次遍历。第一次遍历时，我们统计链表中的元素个数 N；
     * 第二次遍历时，我们遍历到第 N/2 个元素（链表的首节点为第 0 个元素）时，将该元素返回即可。
     */
//    public ListNode middleNode(ListNode head) {
//        int index = 0;
//        ListNode cur = head;
//        while (cur != null) {
//            ++index;
//            cur = cur.next;
//        }
//        int k = 0;
//        cur = head;
//        while (k < (0 + index - 1 + 1) / 2) {
//            ++k;
//            cur = cur.next;
//        }
//        return cur;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

