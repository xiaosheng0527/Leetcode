package leetcode.editor.cn;

//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100] 
//
// 示例 2： 
//
// 
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 已按 非递减顺序 排序 
// 
//
// 
//
// 进阶： 
//
// 
// 请你设计时间复杂度为 O(n) 的算法解决本问题 
// 
// Related Topics 数组 双指针 排序 
// 👍 262 👎 0

import java.util.Arrays;

public class SquaresOfASortedArray{
    public static void main(String[] args) {
        Solution solution = new SquaresOfASortedArray().new Solution();
        int[] nums = {1} ;
        int[] res = solution.sortedSquares(nums) ;
        System.out.println(Arrays.toString(res));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 数组其实是有序的， 只不过负数平方之后可能成为最大数了。
     *
     * 那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
     *
     * 此时可以考虑双指针法了，i指向起始位置，j指向终止位置。
     *
     * 定义一个新数组res，和A数组一样的大小，让k指向res数组终止位置
     *
     * 之后需要从大到小将结果添加到res中，然后k--，直到后面k=-1，退出循环
     */
    public int[] sortedSquares(int[] nums) {

        // 此题目可以使用双指针，i，j
        // 注意：此题的双指针并不是快慢指针，而是两个普通的指针，不要理解错两种的用途

        int len = nums.length ;
        int[] res = new int[len] ;  // 定义一个新数组用于保存最后保存的结果

        int k = res.length - 1 ;    // 指针k指向数组res的尾部，

        // 注意：这里是循环条件，i <= j ，而i > j ，则说明旧数组已经全部添加到res
        // 但为什么要 i <= j ，你可以想象两辆车相向运动，i有++的趋势，j有--的趋势，
        // 直到i == j，遇到，之后i > j，就相离【即结束循环，得到最后的res】
        for(int i = 0 ,j = res.length - 1 ; i <= j ; ){

            if(nums[i]*nums[i] < nums[j] * nums[j]){
                res[k--] = nums[j]*nums[j] ;
                j-- ;
            } else {    // nums[i]*nums[i] >= nums[j] * nums[j]
                res[k--] = nums[i]*nums[i] ;
                i++ ;
            }
        }

        return res ;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

