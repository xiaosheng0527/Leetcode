package leetcode.editor.cn;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。） 
// Related Topics 递归 链表 
// 👍 1048 👎 0

public class SwapNodesInPairs{
    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
        
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
    public ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head ;
        }

        ListNode dummy = new ListNode(0,head) ;

        ListNode cur = dummy ;  // 注意：cur指针始终是指向 待交换 两个节点/一个节点 的前面
                                //      而head指针始终是指向 待交换 两个节点/一个节点 的第一个节点
                                //      你可以注意到，head指针指向的就是交换后 得到 的后面 的节点

         ListNode temp = null ;

        while(cur.next != null && cur.next.next != null){

            // 交换两个节点不是我们想象的这么简单，不能直接交换，需要引入辅助节点temp
            // 就像交换两个数字，也需要引入一个临时变量temp，这里直接交换会出现值的覆盖
            // cur.next.next = cur.next ;
            // cur.next = cur.next.next ;
            //cur = cur.next.next ;   // 向后移动两个位置

            // 如果是使用Carl大佬的思路，则就需要严格按照那三个思路，避免出错
            // temp指针，记录下一轮可能可以交换的节点的第一个节点，缓存next，避免在之前交换节点的过程中，存在覆盖现象
            // 而且对于链表的题目，最好要画图，不然 你可能莫名其妙就出现 空指针异常 或者 是因为 "覆盖" 导致 断链

            // 你有没有注意到，此题 损失了 head 节点，但是损失也没办法，因为节点都要交换了，那么head节点也必然会换
            // 所以最后要确定 头结点，需要用 dummy.next

            temp = head.next.next ;

            cur.next = head.next ;  // 将 prev 的 next 改为 head 的 next
            head.next.next = head ; // 将 head.next(prev.next) 的next，指向 head
            head.next = temp ;      // 将head 的 next 接上缓存的temp

            // 步进1位，此时只能进行引用的传递，而不能直接用 next域，因为在上面的三步骤中，原来链表的链接已经断链了，
            // 此时只能 用引用的传递，cur指针指向 下一次可能 待交换 两个节点/一个节点的前面
            cur = head ;
            // 步进1位，此时不是进行引用的传递 ，可以直接使用 next域，因为在上面的第三步中，head已经指向了下一轮可能可以交换的节点的第一个节点
            // 说明，已经链接起来了，
            head = head.next ;

        }

        return dummy.next ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

