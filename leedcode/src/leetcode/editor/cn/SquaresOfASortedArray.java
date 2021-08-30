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
     *
     * @param nums  待排序的数组
     * @return
     *
     * 注意：本题其实可以考虑使用归并排序，因为，原数组已然是排序好的，
     *      特殊点在于存在正数和负数，那么我们是不是可以将两个序列分开来，
     *      然后用一个temp数组，临时保存排序完的数组，之后进行返回
     */
    public int[] sortedSquares(int[] nums) {
        // 先求出正数和负数的分隔点【若存在0，则0属于正数】
        int division = -1 ; // 初始化为-1
        int len = nums.length ;
        for(int i = 0 ; i < len ; i++){
            if(nums[i] < 0){
                division = i ;
            }else{
                break ;
            }

        }
        //System.out.println(division);
        // 确定了分隔点，则可以使用归并排序
        int tempLeft = 0 ;
        int mid_L = division ;
        int mid_R = division + 1 ;
        int right = len - 1 ;
        int[] res = new int[len] ;
        while(division < 0){
            // 说明该数组没有一个是小于0的
            if(right < 0){
                return res ;
            }
            res[right] = nums[right] * nums[right] ;
            right-- ;
        }
        while(division >=0 && mid_L >= 0 && mid_R <= right){
            if(nums[mid_L] * nums[mid_L] < nums[mid_R] * nums[mid_R]){
                res[tempLeft] = nums[mid_L] * nums[mid_L] ;
                tempLeft++;
                mid_L-- ;
            }else{
                res[tempLeft] = nums[mid_R] * nums[mid_R] ;
                tempLeft++ ;
                mid_R++ ;
            }
        }
        while(mid_L >= 0){ // 说明负数还没有比较完，即负数的平方大
            res[tempLeft] = nums[mid_L] * nums[mid_L] ;
            mid_L-- ;
            tempLeft++ ;
        }
        while(mid_R <= right){ // 说明负数还没有比较完，即负数的平方大
            res[tempLeft] = nums[mid_R] * nums[mid_R] ;
            mid_R++ ;
            tempLeft++ ;
        }
        return res ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

