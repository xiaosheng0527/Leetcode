package leetcode.editor.cn;

//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
//
// 
// 
// Related Topics 数组 数学 双指针 
// 👍 1045 👎 0

import java.util.Arrays;

public class RotateArray{

    public static void main(String[] args) {
        Solution solution = new RotateArray().new Solution();
        int[] nums = {1,2,3,4,5,6,7} ;
        solution.rotate(nums,3);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 利用数组翻转的方式

    /**
     *
     * @param nums  代表要移动的数组
     * @param k     代表数组元素移动的的个数【类似队列的性质，即数组模拟队列】
     *
     *  该方法基于如下的事实：当我们将数组的元素向右移动 kk 次后，尾部 k mod n 个元素会移动至数组头部，其余元素向后移动 k mod n 个位置。
     * 该方法为数组的翻转：我们可以先将所有元素翻转，这样尾部的 k mod n 个元素就被移至数组头部，然后我们再翻转 [0, k mod n - 1]区间的元素和 [k mod n, n-1]区间的元素即能得到最后的答案。
     *              这里的第一个下标区间即为[0,(k mod n) - 1] 有k个元素，第二个下标区间为[(k mod n),n-1]，即有 n-k个元素
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}

