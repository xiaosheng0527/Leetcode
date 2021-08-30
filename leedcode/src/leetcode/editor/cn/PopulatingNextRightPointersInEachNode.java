package leetcode.editor.cn;

//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// 
//
// 示例： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 
//next 指针连接，'#' 标志着每一层的结束。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量少于 4096 
// -1000 <= node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 516 👎 0

import java.util.LinkedList;

public class PopulatingNextRightPointersInEachNode{
    public static void main(String[] args) {
        //Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();

    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

class Solution {
    public Node connect(Node root) {

        if(root == null){
            return root ;
        }

        LinkedList<Node> queue = new LinkedList<>() ;
        queue.add(root) ;

        // 获取到队列的第一个节点，每次都需要获取，故可以放在while循环外面
        Node temp = queue.get(0) ;

        // 队列长度大于0才可以，进行层次链接
        while(queue.size() > 0){
            int len = queue.size() ;
            // 注意：这里为什么要初始化为1，因为如果只是一个节点，则此循环可以直接跳过
            // 此循环是连接当前层次的所有节点
            for (int i = 1 ; i < queue.size() ; i++){
                // 如果是处理第一个节点，则需要先移除
                // 辅助节点，指向队列的第一个节点，要保留删除的节点
                temp.next = queue.get(i) ;
                temp = temp.next ;
            }

            // 当前层次链接完毕后，需要继续链接下一层次的节点，故需要删除队列头进行依次删除
            for(int i = 0 ; i < len ; i++){
                Node cur = queue.remove() ; // 注意：此处的cur是每一次删除队列头后然后处理是否有左右子节点，
                                            // 然后在同层次下，重新获取头结点的
                if(cur.left != null){
                    queue.add(cur.left) ;
                }
                if(cur.right != null){
                    queue.add(cur.right) ;
                }
            }
        }
        return root ;


    }
 }
}
//leetcode submit region end(Prohibit modification and deletion)

}

