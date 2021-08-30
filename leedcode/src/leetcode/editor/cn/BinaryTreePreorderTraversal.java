package leetcode.editor.cn;

//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 625 👎 0

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        // 利用双端队列来模拟栈，利用非递归解法，即递推法
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        // 退出while循环的条件：栈为空，以及 当前节点node为null
        // 注意：如果只是限制一个条件，栈不为空，此时如果刚好搜索完左子树后，此时栈就为空，那么就结束搜索了，
        // 但右子树还没有搜索，故还需要node==null才可以指明搜索完毕
        while (!stack.isEmpty() || node != null) {
            // 此处的while循环是为了辅助节点搜索到左子树的最底部
            // 要满足根左右的性质，前序遍历
            while (node != null) {
                res.add(node.val);  // 将遍历到的根节点添加到res
                stack.push(node);   // 将根节点入栈
                node = node.left;   // 向左搜索
            }
            node = stack.pop(); // 尾部弹栈
            node = node.right;  // 将弹栈的元素向右进行处理，即向右进行搜索
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

