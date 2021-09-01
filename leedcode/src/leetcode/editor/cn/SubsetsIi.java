package leetcode.editor.cn;

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// 
// 
// Related Topics 位运算 数组 回溯 
// 👍 642 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsIi{
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        int len = nums.length ;
        // 返回的二维集合
        List<List<Integer>> res = new ArrayList<>() ;
        // 记录路径的一维集合,这里的path路径用temp一维集合来表示
        List<Integer> temp = new ArrayList<>() ;

        // 注意：如果有重复的元素且又要做排列或者子集的话，我们一般先排序，方便后面剪枝
        Arrays.sort(nums);

        if(len == 0){
            return res ;
        }

        dfs(nums,res,temp,0,len) ;

        return res ;
    }

    /**
     *
     * @param nums      待处理的数组
     * @param res       返回子集的二维集合
     * @param temp      处理临时路径下的子集
     * @param index     待处理的数组的下标
     * @param len       数组的长度
     */
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, int index, int len) {

        // 调用dfs函数时候，先添加到集合中，刚开始添加为空集
        res.add(new ArrayList<>(temp)) ;

        // dfs：一条道走到黑，故这里需要 int i = index
        for(int i = index ; i < len ; i++){

            // 相当于进入到回溯时，然后此时的元素和前一个元素相同，则可以直接跳过
            // 如果回溯时，此时的元素和前一个元素不同，则还是需要添加到path路径中，即添加到temp中
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }

            // 一般如果是刚开始的情况，在每一个的递归栈中的i == index 恒成立
            // 就不会执行上面的if语句
            temp.add(nums[i]) ;
            dfs(nums, res, temp, i + 1, len);
            temp.remove(temp.size() - 1) ;  // 弹出一维集合尾部的元素，恢复现场

        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

