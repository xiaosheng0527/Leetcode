package leetcode.editor.cn;

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1473 👎 0

import java.util.ArrayList;
import java.util.List;

public class Permutations{
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        int[] nums = {1,2,3} ;
        solution.permute(nums) ;
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *  此题可以自己画个图，看到这不是属于dfs+回溯的问题
     *  我们可以看到，这个很像树形结构，故可以模拟树的遍历
     * @param nums  待排列的数组
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length ;
        List<List<Integer>> resList = new ArrayList<>() ;   // 全排列集合
        List<Integer> pathList = new ArrayList<>(len) ;  // 每一种情况保存在一个一维集合中,
        boolean[] isVisited = new boolean[nums.length] ;
        if(len == 0){
            return resList ;
        }
        dfs(resList, pathList, nums, 0, isVisited);
        return resList ;
    }

    /**
     *
     * @param resList   返回的全排列的二维集合
     * @param pathList    每一种情况下的一维集合
     * @param nums          待排列的数组
     * @param depth         模拟树的深度
     * @param isVisited     布尔数组，判断是否已经遍历过
     *                      注意：这里要使用布尔数组是因为，我们是使用的for循环，从i=0开始遍历，那么回溯过程中可能会重复访问之前访问的元素，就就造成影响
     *                      而组合的题目中不使用布尔数组是因为，因为i并不是从0开始，所以恢复现场只需要删除此元素，然后回溯
     */
    public void dfs(List<List<Integer>> resList,List<Integer> pathList,int[] nums,int depth,boolean[] isVisited){
        if(depth == nums.length){   // 递归退出条件
            resList.add(new ArrayList<>(pathList)) ;
            return ;    // 返回至上一层
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(!isVisited[i]){
                pathList.add(nums[i]) ;
                isVisited[i] = true ;
                dfs(resList, pathList, nums, depth + 1, isVisited);
                pathList.remove(depth) ;
                isVisited[i] = false ;  // 恢复现场
            }else{
                continue;   // 如果是已访问的，就遍历下一次，for循环
            }

        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

