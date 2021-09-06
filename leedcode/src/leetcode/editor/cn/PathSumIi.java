package leetcode.editor.cn;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// 
// 
// Related Topics 树 深度优先搜索 回溯 二叉树 
// 👍 569 👎 0

import java.util.ArrayList;
import java.util.List;

public class PathSumIi{
    public static void main(String[] args) {
        Solution solution = new PathSumIi().new Solution();
        
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

    // 注意：此题依旧可以利用相减的思路，这样可以少提供一个变量
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> res = new ArrayList<>() ;
        List<Integer> path = new ArrayList<>() ;
        if(root == null){
            return res ;
        }

        // 注意：先将root节点的结果添加到path中
        // 注意：其实这里我有猜的成分，因为我不清楚是否会遍历到root节点的右侧
        // 但是我又结合图形，并进行分析，看到刚开始进入dfs时候是传入的root节点，那么一定会遍历右子树
        // 所以一定会得到所有解
        path.add(root.val) ;
        int remain_num = targetSum - root.val ;

        dfs(root,res,path,remain_num) ;

        return res ;
    }

    /**
     *
     * @param cur       遍历二叉树的所有节点的辅助节点
     * @param res       记录返回所有符合的路径的二维集合
     * @param path      记录符合条件的一条路径
     * @param remain_num
     */
    private void dfs(TreeNode cur, List<List<Integer>> res, List<Integer> path, int remain_num) {

        if(cur.left == null && cur.right == null){
            if(remain_num == 0){
                res.add(new ArrayList<>(path)) ;
                return ;

            } else {
                return ;    // 同样需要返回上一级
            }
        }

        if(cur.left != null){

            remain_num -= cur.left.val ;
            path.add(cur.left.val) ;
            dfs(cur.left, res, path, remain_num);

            // 回溯
            remain_num += cur.left.val ;
            path.remove(path.size() - 1) ;
        }

        if(cur.right != null){

            remain_num -= cur.right.val ;
            path.add(cur.right.val) ;
            dfs(cur.right, res, path, remain_num);

            // 回溯
            remain_num += cur.right.val ;
            path.remove(path.size() - 1) ;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

