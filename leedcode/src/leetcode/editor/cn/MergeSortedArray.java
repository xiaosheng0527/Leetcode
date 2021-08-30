package leetcode.editor.cn;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 1044 👎 0

import java.util.Arrays;

public class MergeSortedArray{
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * @param nums1    目标数组
     * @param m         目标数组的元素长度，索引范围  [0,m - 1]
     * @param nums2     源数组
     * @param n         源数组的元素长度
     */
    // 根据上面的部分参数，好像和arraycopy()方法很像
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /* 方法1：直接利用java自带的库，直接求得
        // 参数1  nums2：源数组
        // 参数2  0：源数组中要拷贝的起始索引(位置)
        // 参数3  nums1：目标数组
        // 参数4  m：拷贝到目标数组的起始索引(位置)
        // 参数5  n：要拷贝的元素个数(长度)
        System.arraycopy(nums2,0,nums1,m, n);
        Arrays.sort(nums1);*/

        /*// 方法2：利用双指针，但需要额外开辟一个nums1的拷贝
        int[] nums1_copy = new int[m] ;
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0 ;    // 设置指针p1，指向nums1_copy开头
        int p2 = 0 ;    // 设置指针p2，指向nums2开头
        int p = 0 ;     // 设置指针p，指向nums1开头

        while((p1 < m) && (p2 < n)){
            if(nums1_copy[p1] < nums2[p2]){
                nums1[p++] = nums1_copy[p1++] ;
            }else{
                nums1[p++] = nums2[p2++] ;
            }
        }
        // 当nums1_copy还有剩余的元素
        if(p1 < m){
            // 注意：这里不考虑索引值的相减，直接是多少元素，就对应相减
            // 当p2 == n 时，此时的n就代表nums2的所有元素，
            // 而此时的p1就代表nums1_copy中已拷贝元素个数【要注意：最后执行完之后它们都会进行 +1 】
            // 如果要用索引值的差来计算长度的话，可以改为 (m - 1) + (n - 1) - (p1 - 1) - (p2 - 1) == m + n - p1 - p2
            System.arraycopy(nums1_copy,p1,nums1, p, m + n - p1 - p2);
        }
        // 当nums2还有剩余的元素
        if(p2 < n){
            System.arraycopy(nums2, p2, nums1, p, m + n - p1 - p2);
        }*/

        // 方法3：双指针/ 从后往前
        int p1 = m - 1 ;    // 设置指针p1 指向 nums1[m - 1]
        int p2 = n - 1 ;    // 设置指针p2 指向 nums1[n - 1]
        int p = m + n - 1 ; // 设置指针p 指向 nums1[m + n - 1]

        while((p1 >= 0) && (p2 >= 0)){
            if(nums1[p1] < nums2[p2]){
                nums1[p--] = nums2[p2--];
            }else{
                nums1[p--] = nums1[p1--];
            }
        }

        // 这里需要注意的是：
        // 如果此时nums2己经全部排序到nums1中的后面的位置中【即p2 = -1】，
        // 而且此时原来的p1指向的位置的前面所有元素都是有序的，所以不需要排列
        if(p1 >= 0){
            return ;
        }
        if(p2 >= 0){
            // 下面是说nums2还没有遍历完，故直接将nums2前面剩余的有序元素拷贝到前面的位置
            // 注意：当前面while循环中，p2指向的元素添加到nums1中后，p2--，刚好此时p2对应的是索引
            // 所以，此时nums2数组中还剩下 索引范围 [0,p2] ,即有 p2 + 1 个元素
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

