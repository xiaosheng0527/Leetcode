package leetcode.editor.cn;

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 只会存在一个有效答案 
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？ 
// Related Topics 数组 哈希表 
// 👍 11703 👎 0

import java.util.HashMap;
import java.util.Map;

public class TwoSum{
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 之前利用暴力枚举的方法，时间复杂度为O(n^2),空间复杂度为O(1)
    // 所以，这里需要优化算法，利用时间换空间
    // 故这里可以维护一个哈希表，其时间复杂度为O(n),空间复杂度为O(n)

//     当我们使用HashMap(int initialCapacity)来初始化容量的时候，HashMap并不会使用我们传进来的initialCapacity直接作为初识容量。
//      JDK会默认帮我们计算一个相对合理的值当做初始容量。所谓合理值，其实是找到第一个比用户传入的值大的2的幂。
//      也就是说，当我们new HashMap(7)创建HashMap的时候，JDK会通过计算，帮我们创建一个容量为8的Map；当我们new HashMap(9)创建HashMap的时候，JDK会通过计算，帮我们创建一个容量为16的Map。

    // 方法2：查找表法
    // 在遍历的同时，记录一些信息，以省去一层循环，这是“以空间换时间”的想法
    // 需要记录已经遍历过的数值和它所对应的下标，可以借助查找表实现
    // 查找表有两个常用的实现：
    // 哈希表
    // 平衡二叉树
    public int[] twoSum(int[] nums, int target) {
        int size = nums.length ;
        // 这里哈希表的键存放着元素的值，值存放着索引值
        // java的官方文档建议初始化哈希表时候，尽量指定哈希表的容量，以避免哈希表扩容所带来的性能消耗
        // 这里为什么初始化为size-1？？？
        // 因为这里涉及到哈希表的扩容因子，这里如果想知道的话可以看下hashMap底层实现
        Map<Integer,Integer> map = new HashMap<>(size - 1) ;
        for(int i = 0 ; i < size ; i++){
            int another = target - nums[i] ;    // 记录第一个元素的互补所对应的值
            if(map.containsKey(another)){
                return new int[]{map.get(another),i} ;
            }
            // 注意：下面这个代码有两层含义：
            // 1.刚开始哈希表没有值，就直接存放
            // 2.如果在上面的if语句比较过程中，没有匹配上，则就继续添加
            map.put(nums[i],i) ;
        }
        // 如果执行到这里，说明没有找到，则要么抛出异常，要么直接返回一个空数组
        throw new IllegalArgumentException() ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

