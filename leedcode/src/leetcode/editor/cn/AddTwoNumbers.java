package leetcode.editor.cn;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6515 👎 0

public class AddTwoNumbers{
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        
    }
    // 所以，此时如果想初始化链表也不行
    static class SingleLinkedList{
        ListNode head = null ;  // 头结点
    }
// 下面是leetcode提交区域开始（禁止修改和删除），故可以自行修改的是在上面
//leetcode submit region begin(Prohibit modification and deletion)

 //Definition for singly-linked list.
 public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    /**
     注意：此题并没有对原来链表进行修改，故需要新创建一个链表头
     而且这里我们也不需要重复利用原来两个链表，只需要遍历一遍
     所以，头结点l1，l2可以不用保留

     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null ;  // 代表新的链表的头结点，此头结点就相当于第一个有数据节点，用于后面的遍历使用，故不能动
        ListNode curNode = null ;   // 尾插法，在链表尾部插入元素，遍历链表
        int carry = 0 ; // 代表进位
        int sum = 0 ;   // 代表当前位的本位和 = 当前位 + 上一位的进位
        while(l1 != null || l2 != null){
            //如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0
            // 以保证链表长度比较的统一性 ，这里使用三目运算符，条件表达式
            int num1 = (l1 != null) ? l1.val : 0 ;
            int num2 = (l2 != null) ? l2.val : 0 ;
            sum = num1 + num2 + carry ;
            if(head == null){
                head = new ListNode(sum % 10) ;   // 链表处相应位置的数字
                curNode = head ;
            }else{
                // 此时添加的就不是第一个节点
                curNode.next = new ListNode(sum % 10) ; // 两个数相加后在链表处相应位置的数字
                curNode = curNode.next ;
            }

            carry = sum / 10 ;  // 代表新的进位
            // 当前位的两个数计算完之后，需要移动到后一个位置
            if(l1 != null){
                l1 = l1.next ;
            }
            if(l2 != null){
                l2 = l2.next ;
            }
        }

        // 当l1或者l2在最后一个节点，跳出循环后，可能存在最高位的数字sum的进位，
        // 此时需要判断,如果存在的话，需要再创建一个节点进行保存
        // 如果链表遍历结束后，carry > 0,还需要在链表的后面附加一个节点，节点的值为carry
        if(carry > 0){
            curNode.next = new ListNode(carry) ;
            //curNode = curNode.next ;
        }

        return head ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

