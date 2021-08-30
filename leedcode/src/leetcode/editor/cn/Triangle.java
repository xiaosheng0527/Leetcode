package leetcode.editor.cn;

//给定一个三角形 triangle ，找出自顶向下的最小路径和。 
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
// 
//
// 示例 2： 
//
// 
//输入：triangle = [[-10]]
//输出：-10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= triangle.length <= 200 
// triangle[0].length == 1 
// triangle[i].length == triangle[i - 1].length + 1 
// -104 <= triangle[i][j] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？ 
// 
// Related Topics 数组 动态规划 
// 👍 810 👎 0

import java.util.List;

public class Triangle{
    public static void main(String[] args) {
        Solution solution = new Triangle().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
/*  // 方法1：利用递归，注意，你可以画出递归树，里面存在一些重复的操作，当数据量太大的时候，会存在栈溢出
    // 故最好记性记忆化递归
    // 若定义 f(i, j)f(i,j) 为 (i, j)(i,j) 点到底边的最小路径和，则易知递归求解式为:
    // f(i, j) = min(f(i + 1, j), f(i + 1, j + 1)) + triangle[i][j]f(i,j)
    // 注意：递归和递推是不同的计算方式
    // 递归是自顶向下，递推是自底向上；
    // 递归有递归推导式，递推有递推推导式，但递推的效率高，递归存在递归栈，极其消耗内存
    public int minimumTotal(List<List<Integer>> triangle) {
        // 初始坐标为(0,0)
        return dfs(triangle,0,0) ;
    }

    **
     *
     * @param triangle  待找寻路径的二维集合
     * @param i         横坐标
     * @param j         纵坐标
     * @return
     *
    public int dfs(List<List<Integer>> triangle, int i , int j){
        if(i == triangle.size()){
            return 0 ;
        }
        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j) ;
    }*/

    /*// 定义了二维数组进行记忆化递归，但效率还是很低，虽然不会报错
    Integer[][] memory = null ;
    public int minimumTotal(List<List<Integer>> triangle) {
        memory = new Integer[triangle.size()][triangle.size()] ;    // 可以模拟成一个等腰直角三角形
        // 初始坐标为(0,0)
        return dfs(triangle,0,0) ;
    }
        **
        *
        * @param triangle  待找寻路径的二维集合
        * @param i         横坐标
        * @param j         纵坐标
        * @return
        *
    public int dfs(List<List<Integer>> triangle, int i , int j){
        if(i == triangle.size()){
            return 0 ;
        }
        if(memory[i][j] != null){
            return memory[i][j] ;
        }
        return memory[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j) ;
    }*/

    // 方法3：利用动态规划，dp，效率明显就上来了，但还是可以优化，这里先不考虑
    // 定义二维 dp 数组，将解法二中「自顶向下的递归」改为「自底向上的递推」。
    // 自底向上的优势是①可以规避边界问题，②出口dp[0]就是最终答案
    // 1.状态定义：dp[i][j] 表示从点 (i, j)(i,j) 到底边的最小路径和。
    // 2.状态转移：dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
    // 注意：dp数组的每一个元素都是一个状态，这里是从后向前进行构建状态，即前面的状态由后面进行铺垫产生的，而且从后向前无太多临界条件
    // 其实我们也可以从前向后进行构建状态，但会存在一些临界条件，
    public int minimumTotal(List<List<Integer>> triangle){
        int n = triangle.size();
        // dp[i][j]表示从点(i,j)到底边的最小路径和
        // 为什么要 这里开辟空间需要多加上1，因为递推式中，里面的如果刚好判断的是最底层的节点 i+1 会越界，或者是最靠右边的节点 j+1 会越界，所以dp[][]，申请是n+1,n+1
        int[][]dp = new int[n + 1][n + 1] ;
        // 从三角形的最后一行开始递推
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = 0 ; j <= i ; j++){
                dp[i][j] = Math.min(dp[i + 1][j],dp[i + 1][j + 1]) + triangle.get(i).get(j) ;
            }
        }
        // 此时刚好dp数组最左边的状态的结果就是最小路径和
        return dp[0][0] ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

