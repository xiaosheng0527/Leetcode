package leetcode.editor.cn;

//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 
// 👍 787 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsIi{
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {

        int len = nums.length ;
        Arrays.sort(nums);  // 先进行排序
        List<List<Integer>> res = new ArrayList<>() ;
        // 记录路径
        List<Integer> path = new ArrayList<>() ;
        // 记录是否访问，利用布尔数组
        boolean[] isVisited = new boolean[len] ;

        if(len == 0){
            return res ;
        }

        dfs(nums,res,path,len,isVisited,0) ;

        return res ;
    }

    /**
     *
     * @param nums      待处理的数组
     * @param res       返回的全排列的集合
     * @param path      记录每个分支的路径
     * @param len       nums数组的长度
     * @param isVisited  标记是否已经访问过
     * @param depth      标记此时待处理第几层【结合递归树】，[这里使用index变量可能理解得会比较麻烦，最好使用depth，层数，比较贴切]
     */
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> path, int len, boolean[] isVisited, int depth) {

        if(depth == len){
            res.add(new ArrayList<>(path)) ;
        }

        // 为了要进行全排列，则在每次dfs中，都需要重新 int i = 0，故需要isVisited来标记
        for(int i = 0 ; i < nums.length ; i++){
            // 避免相同元素的重复排列
            // 如果nums[i-1]访问过了，再dfs到nums[i]，刚好二者元素值相同，则回溯到num[i-1]的时候，nums[i-1]的状态已经被清空了【false】，
            // 所以此时nums[i-1]的布尔值一定为false，因此才可以进行跳过，这个相同的元素
            if(isVisited[i] || (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1])){
                continue;
            }

            path.add(nums[i]) ;
            isVisited[i] = true ;
            dfs(nums, res, path, len, isVisited, depth + 1);
            path.remove(depth) ;
            isVisited[i] = false ;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

