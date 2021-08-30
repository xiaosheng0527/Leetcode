package leetcode.editor.cn;

//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 双指针 
// 👍 681 👎 0

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class RemoveDuplicatesFromSortedListIi{
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
        
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
    public ListNode deleteDuplicates(ListNode head) {

        // 没有节点或者只有一个节点，必然没有重复元素
        // 注意：此处的if语句既是刚开始如果链表只存在一个节点或没有节点的判断
        // 也是递归出口的判断
        if(head == null || head.next == null){
            return head ;
        }
        // 注意：这里使用递归来求解
        // 当前节点和下一个节点，值不同，则head的值是需要保留的，对head.next继续递归
        if(head.val != head.next.val){
            // 保留head节点,自顶向下，然后递归退层到出口
            head.next = deleteDuplicates(head.next) ;
        } else {
            // 当前节点与下一个节点的值重复了，重复的值都不能要。
            // 一直往下找，找到不重复的节点。返回对不重复节点的递归结果
            // head.val 代表当前递归栈中head节点的值
            // move指针是指向head
            ListNode move = head.next ;
            while(move != null && head.val == move.val){
                move = move.next ;  // 这里不考虑head节点，因为是重复元素，所以head还是指向之前指向的位置
            }
            return deleteDuplicates(move) ;  // 将此处不重复的节点move继续进行递归当做下一个节点的head
        }
        return head ;



        /*if(head == null || head.next == null){
            return head ;
        }

        ListNode dummy = new ListNode(0,head) ;

        ListNode cur = dummy ;

        while(cur.next != null && cur.next.next != null){

            if(cur.next.val == cur.next.next.val){
                // 注意：这个当前节点的下一个节点的值是动态获取的
                int elem = cur.next.val ;
                // 注意：要添加条件防止空指针异常
                // 所以需要加上一个cur.next != null ,本质上cur是指向要删除元素的前一个节点
                while(cur.next != null && elem == cur.next.val){
                    cur.next = cur.next.next ;
                }
            }else{
                cur = cur.next ;
            }
        }

        return dummy.next ;*/
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

