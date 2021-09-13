package leetcode.editor.cn;

//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。 
//
// 
//示例 1: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
// 
//
// 示例 2: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设 nums 中的所有元素是不重复的。 
// n 将在 [1, 10000]之间。 
// nums 的每个元素都将在 [-9999, 9999]之间。 
// 
// Related Topics 数组 二分查找 
// 👍 408 👎 0

public class BinarySearch{
    public static void main(String[] args) {
        Solution solution = new BinarySearch().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *      大家写二分法经常写乱，主要是因为对区间的定义没有想清楚，区间的定义就是不变量。
     *      要在二分查找的过程中，保持不变量，就是在while寻找中每一次边界的处理都要坚持根据区间的定义来操作，
     *      这就是循环不变量规则。
     *
     *      二分法第二种写法
     *      如果说定义 target 是在一个在左闭右开的区间里，也就是[left, right) ，那么二分法的边界处理方式则截然不同。
     *
     *      有如下两点：
     *      while (left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
     *         if (nums[middle] > target) right 更新为 middle，
     *      因为当前nums[middle]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，
     *      所以right更新为middle，即：下一个查询区间不会去比较nums[middle]
     */
    public int search(int[] nums, int target) {

        // 这里的left指针指向的是第一个位置，right指针指向当前区间的最后一个位置的下一个位置
        int left = 0 ;
        int right = nums.length ;  // 定义target在左闭右开的区间里，即：[left, right)

        int mid = 0 ;

        while(left < right){    // 因为left == right的时候，在[left, right)是无效的空间，所以使用 <

            mid = left + ((right - left) >> 1) ;

            if(nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid ;   // target 在左区间，在[left, middle)中
            } else {    // nums[mid] == target
                return mid ;
            }
        }

        return -1 ;


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

