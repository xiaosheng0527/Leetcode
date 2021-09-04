package leetcode.editor.cn;

//给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。 
//
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,6,7,7]
//输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,4,3,2,1]
//输出：[[4,4]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 15 
// -100 <= nums[i] <= 100 
// 
// Related Topics 位运算 数组 哈希表 回溯 
// 👍 328 👎 0

import java.util.ArrayList;
import java.util.List;

public class IncreasingSubsequences{
    public static void main(String[] args) {
        Solution solution = new IncreasingSubsequences().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 递增子序列
    // 注意：此题默认就是个序列，千万不要自己给它排序，否则就曲解题意了
    public List<List<Integer>> findSubsequences(int[] nums) {

        int len = nums.length ;

        List<List<Integer>> res = new ArrayList<>() ;
        List<Integer> path = new ArrayList<>() ;

        dfs(nums,len,res,path,0) ;

        return res ;
    }

    /**
     * @param nums  待处理的数组
     * @param len   数组的长度【冗余变量】
     * @param res   返回的递增子序列的集合
     * @param path  记录每个路径中满足递增子序列的集合
     * @param beginIndex    当前层的要处理的子序列的起始索引
     */
    private void dfs(int[] nums, int len, List<List<Integer>> res, List<Integer> path, int beginIndex) {

        /*
        本题其实类似求子集问题，也是要遍历树形结构找每一个节点，所以和回溯算法：和求子集问题一样，
        可以不加终止条件，startIndex每次都会加1，并不会无限递归。
        但本题收集结果有所不同，题目要求递增子序列大小至少为2
         */

        if(path.size() > 1){
            res.add(new ArrayList<>(path)) ;
            // 注意这里不要加return，因为要取树上的所有节点
            // 如果用了return，则可能下面还可以取到不止两个元素的子序列
            // 但因为你使用了return，则当前路径就不再考虑了
        }

        // 注意：默认为0 表示未使用过
        // 若赋值为1 表示使用过，此时就需要考虑在 同层下是否重复出现，重复出现，相当于需要去重【即剪枝】
        // 这里的自定义的used数组，其实相当于本层的判断是否重复出现，而对于其他层毫无影响，所以直接在dfs里面定义即可
        // 定义一个数组，来记录同一层中是否存在重复，即相当于要进行去重
        // 这里不像之前的题目，重复的元素是相邻的，此时可能是同层却不相邻。
        // 这里的 nums[i]的范围是： -100 <= nums[i] <= 100 ，
        // 而我们需要用数组来模拟哈希表，但哈希表的索引值是从0开始，故这里的大小就需要右移100个大小
        // 所以used的初始容量为 201，综上，之后的判断是否为同一层的就需要以 索引值100 为基准点
        // 综上：你要把这个数组当做是 -100 <= nums[i] <= 100 数组中的 哈希映射，即将nums的元素值映射到used数组的索引值上。
        //        然后通过used的索引下 对应的元素值 即可查找在 nums数组的元素值是否在used数组中已经出现
        int[] used = new int[201] ; // 这里会默认初始化为0

        for(int i = beginIndex ; i < len ; i++){

            // 如果刚开始path集合为空以及当前序列的末尾元素小于当前准备要添加的元素，
            // 那么就无需考虑这里的重复元素，直接跳过if语句的判断
            // 或者还有一种情况，在同一层出现重复的元素，此时也要进行去重
            if(!path.isEmpty() && (path.get(path.size() - 1) > nums[i]) || used[nums[i] + 100] == 1){
                continue;
            }

            path.add(nums[i]) ;
            used[nums[i] + 100] = 1 ;
            dfs(nums, len, res, path, i + 1);
            path.remove(path.size() - 1) ;

            // 注意：此时的used数组只在本递归栈，即本层下，才有效果，而在别的层下需要另外判断，所以这里的used数组中的构建的哈希表无需恢复现场。
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

