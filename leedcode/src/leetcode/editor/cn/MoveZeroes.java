package leetcode.editor.cn;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 1159 👎 0

import java.util.Arrays;

public class MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        int[] nums = {2,1} ;
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
    // 这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
    // 这的中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]
    public void moveZeroes(int[] nums) {
        // 利用双指针，其实类似快慢指针，
        // 快指针fast：遍历数组到达的下标位置
        // 慢指针slow：表示下一个非0元素要填充的下标位置
        // 这里的快慢指针的初始默认值为0，因为我们不清楚第一个元素是不是非0元素
        // 如果此时初始默认值设置为1的话，那么此时

        // 左指针指向非0元素的最后一个位置【但刚开始移动的时候，还没有排序故left=0】
        // 右指针指向含0元素的第一个位置，故right = 0
        // 类似利用了快排的思想
        // 但是你要注意的是：这里面存在了交换自身的问题，
        // eg：left和right都是指向的同一个元素，而且此时的元素还是!=0，那么会出现
        int left = 0 ;
        int right = 0 ;
        int len = nums.length ;
        while(right < len){ // 注意：在移动的过程中left<=right,故退出条件是right==len
            // 此处的if语句说明：无序序列是否不含有0，都需要交换left和right指针，然后将left右移
            // 为什么要右移？？？因为前一个已经判断好了，故需要移动到下一个位置
            if(nums[right] != 0){
                swap(nums,left,right) ;
                left++ ;    //
            }
            // 无论无序序列是否含有0，都需要移动right指针
            // 如果无序序列含有0，则不用交换，即不用移动left指针，只需让right指向下一个位置
            // 如果无序序列的元素不含有0，则需要交换，即之后需要移动left指针，然后让right指向下一个位置
            right++ ;
        }
    }

    public void swap(int[] nums,int left,int right){
        // 因为存在刚好left==right，而且此时元素!=0，那么交换元素不是白瞎吗？
        // eg：第一个位置的时候
        if(left == right){
            return ;
        }
        int temp = nums[left] ;
        nums[left] = nums[right] ;
        nums[right] = temp ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

