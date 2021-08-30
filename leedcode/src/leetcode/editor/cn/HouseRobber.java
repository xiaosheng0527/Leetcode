package leetcode.editor.cn;

//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 
// 👍 1591 👎 0

public class HouseRobber{
    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
        int[] nums = {1,2,3,1} ;
        System.out.println(solution.rob(nums));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 这里使用到动态规划dp
    /**
     *
     * @param nums  此数组中，索引范围[0,nums.length-1],每个索引下(房子内)对应的元素值存放着H0,H1...的钱
     *  f(k)表示从前k个房子(即H0,H1...,H(K-1))中能偷到的最大金额
     *  当k = 0时，f(0) = 0    【没有房子】
     *  当k = 1时，f(1) = H0   【只有一个房子】
     *  当k >= 2时，f(k) = max{f(k-1),H(k-1) + f(k-2)}
     *  剖析这个递推公式：
     *  k 个房子中最后一个房子是 H(k-1)。
     *  1）如果不偷这个房子，那么问题就变成在前 k-1 个房子中偷到最大的金额，
     *     也就是子问题 f(k−1)。
     *  2）如果偷这个房子，那么前一个房子 H(k-2)显然不能偷，其他房子不受影响。
     *    那么问题就变成在前 k−2 个房子中偷到的最大的金额。两种情况中，选择金额较大的一种结果
     *
     *   子问题：
     *      f(k) = 偷 [0..k) 房间中的最大金额
     *
     *      f(0) = 0
     *      f(1) = nums[0]
     *      f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
     *
     *   注意：1）你要深刻的理解dp数组和原始nums数组没有必然的联系，只是有money的联系
     *         dp数组会和递推公式联系，从而递推公式再和nums联系。
     *        2）nums数组和dp数组的下标含义不同，要区别对待
     */
    public int rob(int[] nums) {

        int len = nums.length ;
        if(len == 0){
            return 0 ;
        }
        // 为什么要定义为len+1?
        // 如果不是n+1的话，那么nums[n]即第n个房间就不能访问了
        // 而且这里dp数组索引值为0的元素值代表f(0)=0
        int[] dp = new int[len + 1] ;   // 索引范围：[0,len]
        // 下面这两行代表初始状态
        dp[0] = 0 ;
        dp[1] = nums[0] ;
        for(int k = 2 ; k <= len ; k++){
            dp[k] = Math.max(dp[k-1],dp[k-2] + nums[k-1]) ;
        }
        return dp[len] ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

