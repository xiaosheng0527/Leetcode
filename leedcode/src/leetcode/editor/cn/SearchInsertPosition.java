package leetcode.editor.cn;

//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 示例 4: 
//
// 
//输入: nums = [1,3,5,6], target = 0
//输出: 0
// 
//
// 示例 5: 
//
// 
//输入: nums = [1], target = 0
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 为无重复元素的升序排列数组 
// -104 <= target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 1063 👎 0

public class SearchInsertPosition{
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 利用二分查找法  【循环不变量】
    // 这里使用 [left,right)  左闭右开
    public int searchInsert(int[] nums, int target) {

        int len = nums.length ;
        int left = 0 ;
        int right = len  ;
        int mid = 0 ;

        // 这里插入的位置有四种情况，但是可以将3种情况合并起来
        // 因为这三种情况具有共同点，target都在数组中某一个元素的前面或者刚好就是这个位置
        // 目标值在数组所有元素之前
        // 目标值等于数组中某一个元素
        // 目标值插入数组中的位置

        while(left < right){

            mid = left + (right - left) / 2 ;

            if(nums[mid] < target){     // 当left==right，区间[left, right]依然有效
                left = mid + 1;
            } else if(nums[mid] > target){
                right = mid ;
            } else{
                return mid ;    // 目标值等于数组中某一个元素 直接返回
            }
        }

        // 当退出while循环，说明 left > right，此时left指向的区域就是待添加的位置，right指向的区域的下一个位置就是待添加的位置
        // 这是相对而言的，注意：return left比较好理解，因为趋势就是left > right，但此时可以理解为：在不满足条件的情况下，left和right都需要向右移动一个位置此时才可以添加元素
        // 与此同时【从left->right的趋势来看】，left的值在while循环内部 被重新覆盖 ，故后面无需+1，而right 却在条件未满足的情况下没有移动指针，故若要添加元素，则需要right + 1
        // 目标值在数组所有元素之前  [0,-1]  return left 或者 return right + 1  ， eg 此时 退出循环时候，right = -1 ，此时若要添加到第0个位置，需要 right + 1
        // 目标值在数组所有元素之后的情况  [left, right]  return left  或者 return right + 1
        // 目标值在数组中元素的中间的位置 [left, right]  return left  或者 return right + 1
        return left ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

