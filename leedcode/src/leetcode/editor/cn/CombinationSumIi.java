package leetcode.editor.cn;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 
// 👍 684 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumIi{
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 使用回溯算法
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        int len = candidates.length ;
        int sum = 0 ; // 模拟记录在当前路径下元素的长度
        // 记录符合条件的数据【即一维集合】
        List<List<Integer>> res = new ArrayList<>() ;
        // 记录路径一维集合
        List<Integer> path = new ArrayList<>() ;
        // 定义一个布尔数组，来记录在递归树中的同一层中是否出现重复的元素，若存在，则要进行去重
        boolean[] isUsed = new boolean[len] ;
        Arrays.sort(candidates);
        if(target < candidates[0]){
            return res ;
        }

        dfs(candidates,target,res,path,0,sum,isUsed) ;
        return res ;
    }

    /**
     *  @param candidates
     * @param target
     * @param res
     * @param path
     * @param sum
     * @param isUsed    布尔数组，来记录是否在同一层存在重复的元素
     */
    private void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int beginIndex, int sum, boolean[] isUsed) {

        // 这一部分代码完全可以在for循环内部进行剪枝
        // if(sum > target){
        //     return ;
        // }

        if(sum == target){
            res.add(new ArrayList<>(path)) ;
            return ;
        }

        for(int i = beginIndex ; i < candidates.length ; i++){

            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过

            // 进行剪枝
            // 如果此时当前递归栈中的sum和即将遍历到的当前递归栈的集合的之和 大于target，说明同层次的后面都不符合要求，所以可以直接退出此循环，break
            if(sum + candidates[i] > target){
                break ;
            }
            // 进行去重
            // 这里为什么要i > 0，保证i-1 所在的数组不会越界
            if(i > 0 && (candidates[i] == candidates[i - 1] && !isUsed[i - 1])){
                continue;
            }

            path.add(candidates[i]) ;
            sum += candidates[i] ;
            isUsed[i] = true ;
            dfs(candidates, target, res, path, i + 1, sum,isUsed);
            path.remove(path.size() - 1) ;
            sum -= candidates[i] ;
            isUsed[i] = false ;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

