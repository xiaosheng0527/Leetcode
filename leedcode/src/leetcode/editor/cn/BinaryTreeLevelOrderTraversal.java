package leetcode.editor.cn;

//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 966 👎 0

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        
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
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>() ;

        if(root == null){
            return res ;
        }
        // 模拟一个队列来存储节点
        Deque<TreeNode> deque = new LinkedList<>() ;
        deque.offer(root);



        while(!deque.isEmpty()){
            // 定义一个一维集合用于记录每一层的节点的值
            List<Integer> level = new ArrayList<>() ;

            int len = deque.size() ;
            for(int i = 0 ; i < len ; i++){

                TreeNode cur = deque.poll() ;
                level.add(cur.val) ;

                if(cur.left != null){
                    deque.offer(cur.left) ;
                }
                if(cur.right != null){
                    deque.offer(cur.right) ;
                }
            }

            res.add(level) ;
        }

        return res ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

