package leetcode.editor.cn;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 1795 👎 0

public class ClimbingStairs{
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        System.out.println(solution.climbStairs(45));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*// 利用递归的思想来解题，递推公式：
        当n=1时，climbStairs(1) = 1
        当n=2时，climbStairs(2) = 2
        当n >= 3时，climbStairs(n) = climbStairs(n - 1) + climbStairs(n - 2)
        其中：n：代表第n级台阶，climbStairs(n)：代表爬到第n级台阶的方案数
    我们用f(x) 表示爬到第 x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶
    【注：这里利用到初始状态来抽象复杂的问题，然后形象的考虑到最后一步跨台阶的情况，来进行模拟出递推公式】
    所以我们可以列出如下式子：f(x)=f(x−1)+f(x−2) 【x >= 3】
    它意味着爬到第 x 级台阶的方案数是爬到第 x - 1 级台阶的方案数和爬到第 x - 2 级台阶的方案数的和。
    很好理解，因为每次只能爬 1 级或 2 级，所以 f(x) 只能从 f(x - 1) 和 f(x - 2) 转移过来，
    而这里要统计方案总数，我们就需要对这两项的贡献求和。


    // 但此时会出现超时了，即死龟了，栈溢出
    // 所以，看看可不可以换成递推的思想，
    public int climbStairs(int n) { // n代表第n级台阶
        if(n == 1){
            return 1 ;
        }
        if(n == 2){
            return 2 ;
        }

        return climbStairs(n - 1) + climbStairs(n - 2) ;
    }*/
    /*// 方法2：记录化递归
    public int climbStairs(int n) {
        // 记忆数组，存储中间结果，避免重复计算
        int[] memory = new int[n + 1] ; // 注意：n = 0 级台阶不作为方案数，范围从[1,n]
        return climbStairs(n,memory) ;
    }

    // 重载方法
    public int climbStairs(int n,int[] memory){
        // 如果已经存在到某一级的方案数，就无需继续递归探测了，这里直接返回
        if(memory[n] > 0){
            return memory[n] ;
        }
        if(n == 1){
            memory[n] = 1 ;
        } else if(n == 2){
            memory[n] = 2 ;
        } else{
            memory[n] =  climbStairs(n - 1,memory) + climbStairs(n - 2,memory) ;
        }
        // 这里是因为如果刚刚好算的这一级还没有算过，此时需要一层层的递归
        return memory[n] ;
    }*/

    /*// 方法3：动态规划
    // 每一步的实现都需要前面步骤的完成，即利用dp
    public int climbStairs(int n){
        if(n == 1){
            return 1 ;
        }
        // 记录n个状态，从1到n依次更新
        int[] dp = new int[n + 1] ;
        dp[1] = 1 ;
        dp[2] = 2 ;
        for(int i = 3 ; i <= n ; i++){
            dp[i] = dp[i - 1] + dp[i - 2] ;
        }
        return dp[n] ;
    }*/

    // 方法4：菲波那切数列
//    我们不难通过转移方程和边界条件给出一个时间复杂度和空间复杂度都是 O(n) 的实现，
//    但是由于这里的 f(x) 只和 f(x - 1) 与 f(x - 2)有关，
//    所以我们可以用「滚动数组思想」把空间复杂度优化成 O(1)
//    注意：这里的数组是对于上面dp的状态数组进行优化，即可以模拟出一个滚动数组，但实际上不存在

    public int climbStairs(int n){
        if(n == 1){
            return 1 ;
        }
        int first = 1 ;
        int second = 2 ;
        for(int i = 3 ; i <= n ; i++){
            // 优化空间复杂度，只记录两个状态
            int third = first + second ;
            // 类似滚动数组，即状态整体向前滚动一位
            first = second ;
            second = third ;
        }
        return second ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

