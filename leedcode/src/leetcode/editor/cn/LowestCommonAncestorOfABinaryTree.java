package leetcode.editor.cn;

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1281 👎 0

public class LowestCommonAncestorOfABinaryTree{
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {

    /**
     *
     *  注意：此题默认节点都是不重复的，根据序号的不同分为不同的节点
     *
     *  1）在递归函数有返回值的情况下：如果要搜索一条边，递归函数返回值不为空的时候，立刻返回，【即LeetCode112.路径总和】
     *  2)如果搜索整个树，直接用一个变量left、right接住返回值，
     *      这个left、right后序还有逻辑处理的需要，也就是后序遍历中处理中间节点的逻辑（也是回溯）。【即LeetCode236. 二叉树的最近公共祖先】
     *
     *      但其实对于取不同的p,q节点，
     *      1）可能是只搜索一次【即假如根节点就满足，则直接就返回】
     *      2）如果p，q节点有一个在叶子节点处，则其相邻的路径都需要遍历到叶子节点处   【只有这个需要遍历整个树】
     *      3) 如果root节点的左右节点刚好就是p,q节点，则往左右搜索的dfs方法只需要执行一次，就可以退出了
     *
     *      但你要明白一点：第一个dfs()方法的调用结束是在root节点，即最后是需要传到头结点root的
     *      但具体是从哪一层传递，你就要看p,q的相对位置，哪个节点的深度越大，则就要从当前层 一层一层传到头结点的
     *
     */

    // 此题使用dfs+回溯
    //
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        /*  这里可以不用判断，直接在dfs中判断
        if(root == null){
            return null ;
        }*/
       return dfs(root,p,q) ;
    }

    /**
     *
     * @param cur      遍历二叉树的每个节点
     * @param p         待查找的节点p
     * @param q         待查找的节点q
     *
     *  // 注意：其实此题出的不严谨，因为在main函数创建待查找p,q节点对象的时候和二叉树原来保存的节点对象存储的地址空间不同，
     *          但我们只是为了解题而已，也就不用考虑那么多。
     */
    // 注意：这里为什么会有返回值，可以看Carl大佬的题解，我们需要将left和right的结果进行返回，所以必须遍历整个二叉树
    // 这里还是要注意一点：
    // 1. 在同一个dfs中，对于向左和向右搜索 其实都是在同一层里进行的，只是这需要回溯来进行操作。
    // 2. 在不同的dfs中，是在不同层进行搜索拍【即，纵向搜索】
    private TreeNode dfs(TreeNode cur, TreeNode p, TreeNode q) {

        // 若cur为空，或者遍历到的当前节点为p或者q，则说明，此路径下已经找到一个节点，无论是null还是非null
        if(cur == null || cur == p || cur == q){
            return cur ;
        }

        // 向左进行搜索，且存在返回值，是否为空，不清楚,但我们是需要
        TreeNode left = dfs(cur.left, p, q) ;

        // 注意：这里面的回溯其实是隐藏起来了，但为什么我们不用进行恢复现场，
        // 因为这里刚好二叉树的左右节点是一一联通的，所以无需我们 恢复现场。

        // 向右进行搜索，且存在返回值，是否为空，不清楚
        TreeNode right = dfs(cur.right, p, q) ;

        if(left != null && right != null){
            return cur ;    // 相当于当前的递归栈中的最近公共祖先
        } else if(left == null && right != null){
            return right ;  // 相当于当前的递归栈中的最近公共祖先
        } else {
            return left ;   // 相当于当前的递归栈中的最近公共祖先
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

