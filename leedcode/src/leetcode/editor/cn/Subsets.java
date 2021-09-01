package leetcode.editor.cn;

//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 
// 👍 1302 👎 0

import java.util.ArrayList;
import java.util.List;

public class Subsets{
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
        int[] nums = {1,2,3} ;
        solution.subsets(nums) ;

        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此题总体也使用到回溯的思想，只是此处是每得到一种结果都需要将其封装到一维集合中然后存放到二维集合中
    public List<List<Integer>> subsets(int[] nums) {

        int len = nums.length ;

        List<List<Integer>> res = new ArrayList<>() ;

        if(len == 0){
            return res ;
        }

        List<Integer> temp = new ArrayList<>() ;

        dfs(nums,res,temp,0) ;

        return res ;

    }

    /**
     *
     * @param nums   待处理的数组集合
     * @param res     要返回的二维集合
     * @param temp    存放子集的临时一维集合
     * @param index   当前dfs处理到的递归栈的nums的下标
     */
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp,int index) {

        // 初始进入时候，添加空集
        res.add(new ArrayList<>(temp)) ;    // 重新将temp的内容覆盖到一个新的一维集合对象中

        // 这里是没有递归退出的条件，即全部循环一遍

        for(int i = index ; i < nums.length ; i++){

            temp.add(nums[i]) ;
            dfs(nums, res, temp, index + 1);
            temp.remove(temp.size() -1) ;
        }
    }
}



//leetcode submit region end(Prohibit modification and deletion)

}

