package leetcode.editor.cn;

//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯 
// 👍 345 👎 0

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIii{
    public static void main(String[] args) {
        Solution solution = new CombinationSumIii().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
      * @param k    代表由k个数字构成
     * @param n     代表这k个数字的总和
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        // 这里无需再定义一个数组，因为刚好数组的元素为从小到大排序，那么刚好可以用索引值的变量来代替
        //int[] nums = {1,2,3,4,5,6,7,8,9} ;
        // 记录结果
        List<List<Integer>> res = new ArrayList<>() ;
        // 记录路径
        List<Integer> path = new ArrayList<>() ;

        // 记录路径下的对应的值之和
        int sum = 0 ;

        dfs(res,path,sum,1,k,n) ;

        return res ;

    }

    /**
     * @param res
     * @param path
     * @param sum
     * @param beginIndex
     * @param k
     * @param n
     */
    private void dfs(List<List<Integer>> res, List<Integer> path, int sum, int beginIndex, int k, int n) {

        // 已选元素总和如果已经大于n了，那么往后遍历就没有意义了，直接剪掉。
        // 剪枝的前提：默认为升序
        if (sum > n) { // 剪枝操作
            return; // 如果path.size() == k 但sum != n 直接返回
        }

        if(path.size() == k){
            if(sum == n){
                res.add(new ArrayList<>(path)) ;
            }
            return ;
        }

        for(int i = beginIndex ; i <= 9 ; i++){
            path.add(i) ;
            sum += i ;
            dfs(res,path,sum,i + 1,k, n);
            path.remove(path.size() - 1) ;
            sum -= i ;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

