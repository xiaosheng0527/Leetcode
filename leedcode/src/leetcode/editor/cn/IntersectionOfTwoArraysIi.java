package leetcode.editor.cn;

//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 数组 哈希表 双指针 二分查找 排序 
// 👍 529 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoArraysIi{
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoArraysIi().new Solution();
        int[] nums1 = {1,2,2,1} ;
        int[] nums2 = {2,2} ;
        int[] res = solution.intersect(nums1,nums2) ;
        System.out.println(Arrays.toString(res));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * @param nums1 需要将nums中的元素值依次添加到哈希表中，如果重复，则计数+1
     * @param nums2 遍历此数组，需要和已添加到哈希表中的key进行匹配，
     * @return
     *
     * // 这里最好选择长度最小的数组，然后将其存放到哈希表中，因为内存消耗比较小
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length){
            // 注意：如果此时执行了这里的if语句后，然后进行递归调用，
            // 那么在递归栈中里面nums1 < nums2
            return intersect(nums2, nums1) ;    // 这是交换顺序
        }

        Map<Integer,Integer> map = new HashMap<>() ;
        for(int i = 0 ; i < nums1.length ; i++){
            if(map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i]) + 1) ;
            }else{
                map.put(nums1[i],1) ;   // 第一次添加不同的元素，则计数为1
            }
        }
        // 当for循环遍历结束后，此时已经将nums1数组的全部元素添加到哈希表中

        // 定义遍历intersection数组的指针
        int k = 0 ;

        // 定义一个数组，保存交集
        // 注意：交集数组的长度只能比nums1要小或相等
        // 但是此时数组的长度并不是固定的，如果固定可能会无端出现0
        // 所以需要通过指针k 对intersection元素进行切片拷贝
        int[] intersection = new int[nums1.length] ;

        for(int j = 0 ; j < nums2.length ; j++){
            if(map.containsKey(nums2[j]) && map.get(nums2[j]) > 0){

                intersection[k++] = nums2[j];
                map.put(nums2[j], map.get(nums2[j]) - 1) ;
            }else{
                continue;
            }
        }
        // copyOfRange() :原地操作：将指定数组的指定索引范围复制到新数组中，此处的数组元素相当于按照索引范围原地copy
        return Arrays.copyOfRange(intersection, 0, k) ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

