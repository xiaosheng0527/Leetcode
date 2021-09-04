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
     * @param nums          待处理的数组集合
     * @param res           要返回的二维集合
     * @param temp          存放子集的临时一维集合
     * @param beginIndex    当前dfs处理到的递归栈的nums的下标，但这不一定是我们要删除元素对应的下标，因为里面存在了回溯，而此时的index恒不变，
     *                故index 不能保证一直都在temp集合中的最后一个位置
     */
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp,int beginIndex) {

        // 初始进入时候，添加空集
        // 之后每遍历到一个节点，就可以将节点所对应的temp添加到res
        res.add(new ArrayList<>(temp)) ;    // 重新将temp的内容覆盖到一个新的一维集合对象中

        /**
         在注释中，可以发现可以不写终止条件，因为本来我们就要遍历整颗树。
         有的同学可能担心不写终止条件会不会无限递归？
         并不会，因为每次递归的下一层就是从i+1开始的，而此时假设到达最左边的路径的叶子节点，则
         后面再进行for循环，你会发现，直接无法进行，然后就返回到上一个调用点，所以这里无所谓添不添加递归终止条件

         但是为了保持一致性，所以我这里也加上吧！！！
         */
        if(beginIndex == nums.length){
            return ;
        }

        // 这里是没有递归退出的条件，即全部循环一遍
        // 这里为什么会出现冗余，因为此时下标dfs深度优先遍历中
        // 如果出现回溯的情况，则在进行下一次循环时，访问的下标就不是beginIndex + 1，而是i + 1
        // 而此处写成了beginIndex + 1，说明如果进入了回溯，则会执行temp.remove(beginIndex),即后面的循环代码
        // 那么此处i虽然++，但由于beginIndex在当前递归栈中恒不变，故dfs会重复在一个地方重复执行，所以会出现冗余
        for(int i = beginIndex ; i < nums.length ; i++){

            temp.add(nums[i]) ;
            // dfs(nums, res, temp, index + 1);
            dfs(nums, res, temp, i + 1);
            // 删除temp一维集合中的最后一个数字
            temp.remove(temp.size() -1) ;
        }
    }
}



//leetcode submit region end(Prohibit modification and deletion)

}

