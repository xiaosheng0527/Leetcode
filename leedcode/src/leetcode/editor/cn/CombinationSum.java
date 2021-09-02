package leetcode.editor.cn;

//给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的
//唯一组合。 
//
// candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
//
// 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入: candidates = [2,3,6,7], target = 7
//输出: [[7],[2,2,3]]
// 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 示例 4： 
//
// 
//输入: candidates = [1], target = 1
//输出: [[1]]
// 
//
// 示例 5： 
//
// 
//输入: candidates = [1], target = 2
//输出: [[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1506 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum{
    public static void main(String[] args) {
        Solution solution = new CombinationSum().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>() ;
        List<Integer> path = new ArrayList<>() ;

        int sum = 0 ;       // 记录在当前所在层的路径的长度大小

        // 非剪枝的解法，效率不高

        // 剪枝的解法，效率稍微高点，但是需要满足排序
        Arrays.sort(candidates);

        if(target < candidates[0]){
            return res ;
        }

        dfs(candidates,res,path,target,0,sum) ;

        return res ;


    }

    /**
     *
     * @param candidates    待处理的数组
     * @param res           返回的二维集合结果
     * @param path          路径集合
     * @param target        待比较的数字
     * @param beginIndex    当前节点所在的索引位置，【这里最好不要用depth，不然会被误导】
     * @param sum           记录path集合的元素的长度
     */
    private void dfs(int[] candidates, List<List<Integer>> res, List<Integer> path, int target, int beginIndex, int sum) {

        if(sum > target){
            return ;
        }

        // 找到了一个子集，然后返回到上一层
        if(sum == target){
            res.add(new ArrayList<>(path)) ;
            return ;
        }

        for(int i = beginIndex ; i < candidates.length  ; i++){

            // 这里是先进行判断，如果不满足题意，则就不用添加到路径集合中，直接结束本层的递归和回溯，
            // 即如果sum 和 本次递归到的元素之和大于target，则后面的肯定是不满足题意的
            // 直接进行剪枝，因为后面都是不满足的
            if(candidates[i] + sum > target){
                break ;
            }

            path.add(candidates[i]) ;   // 先添加到路径下
            sum += candidates[i] ;
            // 这里为什么还是用i，而不用i + 1，因为candidates 中的数字可以无限制重复被选取，
            // 所以这不像普通的组合问题，普通的组合问题是只能选一次，不能重复选
            dfs(candidates, res, path, target, i, sum);

            path.remove(path.size() - 1) ;
            sum -= candidates[i] ;

        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

