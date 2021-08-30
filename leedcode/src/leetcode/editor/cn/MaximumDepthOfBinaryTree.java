package leetcode.editor.cn;

//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 939 👎 0

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaximumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
        
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
    /*// 递归法
    public int maxDepth(TreeNode root) {

        // 递归出口
        if(root == null){
            return 0 ;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1 ;
    }*/

    public int maxDepth(TreeNode root){
        if(root == null){
            return 0 ;
        }
        // 模拟一个队列来存储节点
        Deque<TreeNode> deque = new LinkedList<>() ;
        deque.offer(root);

        int depth = 1 ;// 用来记录层数,若root不为null，则默认为一层

        while(!deque.isEmpty()){



            int len = deque.size() ;
            for(int i = 0 ; i < len ; i++){

                TreeNode node = deque.poll() ;

                if(node.left != null){
                    deque.offer(node.left) ;
                }
                if(node.right != null){
                    deque.offer(node.right) ;
                }
            }

            depth++ ;
        }

        return depth ;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}

