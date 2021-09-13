package leetcode.editor.cn;

//给定一个含有 n 个正整数的数组和一个正整数 target 。 
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 109 
// 1 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 
// 👍 760 👎 0

public class MinimumSizeSubarraySum{
    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 利用滑动窗口来解题，时间复杂度会大大降低
    public int minSubArrayLen(int target, int[] nums) {

        int len = nums.length ;

        // int minSubLength = 0 ;
        // ------------注意：minSubLength 千万不能初始化为0，解释在下面

        // 为什么最小子串要定义为一个大的值，因为这里是要求最小子串的长度，可能刚好是整个数组的大小，可能找不到，结果为0
        // 但是我们不能刚开始就定义 minSubLength = 0 ，这样就会 硬性加了一个指标，已经找不到最小子串的长度 大于 target
        // minSubLength是逐渐递减的过程，那么我们何不如先定义为一个 最大的值，而且题目中数组的长度 1 <= nums.length <= 10^5,
        // 所以就可以定义一个最大的值，然后如果找到一个子串，就更新 minSubLength
        int minSubLength = Integer.MAX_VALUE ;   // 将res定义为int型 最大的值，此时没有比其更大的值,作用为返回的值，即最小子串的长度

        int subLength = 0 ;     // 记录当前子串的长度

        int i = 0 ; // i：代表滑动窗口的起始位置，

        int sum = 0 ;   // 记录当前的窗口内的子串的元素的和

        for(int j = 0 ; j < len ; j++){     // j：滑动窗口的结束位置

            sum += nums[j] ;

            // 这个while循环很关键
            // 如果sum >= target,则需要将滑动窗口的起始位置往前挪，即压缩窗口
            // 注意这里使用while，每次更新 i（起始位置），并不断比较子序列是否符合条件
            while(sum >= target){

                // 此时需要移动滑动窗口的起始位置，即向前移动，压缩窗口
                subLength = j - i + 1 ;     // 取子序列的长度
                // 此处的三目运算符是为了说明，如果找到一个子串的长度小于 当前minSubLength，则需要更新，否则就不更新
                minSubLength = ((subLength) < minSubLength) ? subLength : minSubLength ;
                // 注意：压缩窗口的同时，sum的值要减去
                sum -= nums[i++] ;  // 这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置）

            }
        }

        if(minSubLength == Integer.MAX_VALUE){
            return 0 ;
        } else {
            return minSubLength ;
        }


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

