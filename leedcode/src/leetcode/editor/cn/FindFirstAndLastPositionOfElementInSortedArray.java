package leetcode.editor.cn;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1131 👎 0

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] nums = {5,7,7,8,8,10} ;
        //int[] nums = {5,7,7,8,10} ;
        int[] res = solution.searchRange(nums, 8) ;
        System.out.println(Arrays.toString(res));

    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length ;
        if(len == 0){
            return new int[]{-1,-1} ;
        }
        int leftPosition = getLeftBorder(nums, target) ;
        if(leftPosition == -1){
            return new int[]{-1,-1} ;
        }

        int rightPosition = getRightBorder(nums, target) ;
        return new int[]{leftPosition,rightPosition} ;

    }

    public int getLeftBorder(int[] nums, int target){
        int left = 0 ;
        int right = nums.length - 1 ;
        while(left < right){    // 注意：这里的循环条件千万不能写成left <= right，不然下面的 right = mid ;可能会导致无限循环
            // int mid = (left + right) / 2 ;   // 默认向下取整
            int mid = left + (right - left) / 2 ;
            if(nums[mid] < target){ // 则需要将mid向target靠拢
                left = mid + 1 ;
            }else if(nums[mid] == target){
                // 注意：这里不会出现死循环，因为mid默认是向下取整
                right = mid ;   // 找到了target，但并不一定是第一个target，故需要向左继续查找
            }else{
                right = mid - 1 ;
            }
        }
        // 由于在退出循环时，一定存在left==right成立，但并不能保证目标元素target在这个数组中一定会出现
        // 所以需要加上下面的判断
        if(nums[left] == target){

            // 虽然此时left == right，但我们最好不要return right ;
            // 因为实际上，就是left指针一直想向right指针方向走，当left指针超过right或刚好位于right位置，就说明结束查找
            // 故一般都是返回left对应的值
            return left ;   // left指针就是指向target元素的第一个位置
        }
        return  -1 ;
    }

    public int getRightBorder(int[] nums, int target){
        int left = 0 ;
        int right = nums.length - 1 ;
        while(left < right){    // 注意：这里的循环条件千万不能写成left <= right，不然下面的left = mid ;可能会导致无限循环
            //int mid = (left + (right - left) + 1) / 2 ;  // 不能这样写，这样写式子都变了
            // 为什么需要向上取整?因为如果不向上取整，会造成死循环，while（left < right){}一直成立
            // 即一直占用内存，但因为不存在额外的开销，故CPU会一直执行内存中的代码
            // 那死循环是在哪一步出现，即nums[mid] == target条件下，left = mid ;继续向右查找，
            // 但因为此时默认mid是向下取整，故如果此时刚好两个相同的值就是target，且相邻，那么mid一直都是指向前一个元素
            // 所以会一直执行nums[mid] == target条件下的代码，因此，此bug的改法就是将(left+right) + 1，从而实现了向上取整
            // 你要明白，上面的getLeftBorder()方法，因为mid是向下取整，刚好nums[mid] == target条件下，right = mid ;故不会出现这个bug
            // int mid = (left + right + 1) / 2 ;
            int mid = (left + right + 1) / 2 ;  // 为什么需要向上取整
            if(nums[mid] < target){ // 则需要将mid向target靠拢
                left = mid + 1 ;
            }else if(nums[mid] == target){
                //
                left = mid ;   // 找到了target，但并不一定是最后一个target，故需要向右继续查找
            }else{
                right = mid - 1 ;
            }
        }
        // 注意：由于左边界如果不返回-1，说明目标元素在数组中一定会出现
        // 因此，数组中一定存在一个元素值为target，但可能只有一个，故最后的下标可能是两个都是一样的
        // 故我们无需下面的判断
//        if(nums[left] == target){
//            return left ;
//        }
//        return  - 1 ;
        // 虽然此时left == right，但我们最好不要return right ;
        // 因为实际上，就是left指针一直想向right指针方向走，当left指针超过right或刚好位于right位置，就说明结束查找
        // 故一般都是返回left对应的值
        return left ;   // left指针就是指向target元素的最后一个位置
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

