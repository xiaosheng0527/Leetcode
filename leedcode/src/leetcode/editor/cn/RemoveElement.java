package leetcode.editor.cn;

//给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。 
//
// 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。 
//
// 
//
// 说明: 
//
// 为什么返回数值是整数，但输出的答案是数组呢? 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
//int len = removeElement(nums, val);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,2,3], val = 3
//输出：2, nums = [2,2]
//解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 num
//s = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,2,2,3,0,4,2], val = 2
//输出：5, nums = [0,1,4,0,3]
//解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面
//的元素。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 50 
// 0 <= val <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1009 👎 0

public class RemoveElement{
    public static void main(String[] args) {
        Solution solution = new RemoveElement().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 使用快慢指针，即双指针来求解
    // 注意：slow指针遍历的数组中的元素就是新的数组，此时要以slow为中心，fast只是辅助移动

    /**
     * 注意：slow指针移动并添加的元素对应数组中的元素就是新的数组，此时要以slow为中心，fast只是辅助移动。
     * 1）当fast指针没有遇到val时，那么slow对应的位置刚好对应的fast指针所指向的元素，然后进行覆盖给slow指向的位置赋值。
     * 2）当fast指针遇到val时，此时需要继续向前遍历，直到遇到第一个不是val的元素，然后将其覆盖到slow所指向的位置。
     * 重点：
     *      slow只是进行添加元素涉及的指针移动，而没有起到遍历的作用，全程都是fast指针在遍历。
     *      同时判断是否等于val也都是fast指针在干活，
     *      而slow指针全程都是在进行覆盖的操作【而这里的覆盖存在原地覆盖和跨越覆盖，这取决于slow和fast的相对位置】
     */
    public int removeElement(int[] nums, int val) {

        int len = nums.length ;
        // 快慢指针初始值 都指向 索引值为0 的位置
        // 这里具体为什么要slow设置为0，因为slow指针遍历到的元素就是新的长度，而这里slow要从索引值为0开始计数
        // 刚好这的新数组的长度即为slow的变化趋势，当slow最后++，然后fast++，fast越界结束循环，此时对应的slow的数值就是新数组的长度
        int slow = 0 ;

        // 双指针只需要遍历一次数组，即可进行该覆盖的覆盖，跳过的跳过，最后慢指针slow指向的索引值即为所要求的长度
        // 注意：数组的长度好像也在动态变化，即要考虑的数组长度变小了
        for(int fast = 0 ; fast < len ; fast++){


            if(val != nums[fast]){
                // 注意：这里fast指针如果遍历到的位置 存在val，那么slow指针就不动，直到fast找到之后的第一个非val值对应的元素
                nums[slow] = nums[fast] ;   // 将fast指针指向的元素赋给slow
                slow++ ;
            } else{ // val == nums[fast]，具体的解释见如上
                continue;
            }
        }

        return slow ;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

