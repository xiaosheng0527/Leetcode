package leetcode.editor.cn;

//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 822 👎 0

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning{
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        String s = "aab" ;
        solution.partition(s) ;
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 利用回溯算法来解决分割回文串的问题
    // 注意：这里的分隔点是由beginIndex来充当的
    public List<List<String>> partition(String s) {

        int len = s.length() ;
        // 记录返回的结果到二维集合
        List<List<String>> res = new ArrayList<>() ;
        // 记录路径的一维集合
        List<String> path = new ArrayList<>() ;

        dfs(s,len,res,path,0) ;

        return res ;

    }

    /**
     *  @param s     待分隔的字符串
     * @param len   字符串长度【冗余参数】
     * @param res   返回的结果集合
     * @param path  记录搜索树的路径集合
     * @param beginIndex    记录当前子串的初始索引
     */
    private void dfs(String s, int len, List<List<String>> res, List<String> path, int beginIndex) {

        // 递归结束条件
        //如果起始位置大于s的大小，说明找到了一组分割方案，即 beginIndex >= len
        // 但，其实在 beginIndex == len 的时候，起始位置已经到字符串的尾部的后一个位置，
        // 故说明此时若满足回文串，则就符合题意，将path添加到res中
        // 如果不满足，则此path集合就为空，这种理解思路是错的***
        // 那如果是前面的子串满足回文串，而后面的子串不满足回文串，则path路径其实会把前面已经保存的的子串进行清空
        // 在非回文子串中不可能会出现beginIndex == len
        // why？
        //
        if(beginIndex == len){
            res.add(new ArrayList<>(path)) ;
            return ;
        }

        // 单层for循环
        for(int i = beginIndex ; i < len ; i++){

            // 这里进行判断是否为回文串的时候，只需要确定子串的初始和结束的索引值，左闭右闭。
            // 原始字符串s无需修改 【beginIndex,i】
            if(!isPalindrome(s,beginIndex,i)){
                // 如果不是回文串，则将右边的索引值+1，此时并没有影响到左边的beginIndex
                // 那么如果是"aab", 对于 a - ab 以 '-'进行分隔，ab不是回文串，则右边的索引值会+1，此时需要向后进行搜索，可能后面的就会满足
                // 千万不要理解为beginIndex会++，因为此时左边界是不会移动
                // 综上：此时如果if语句成立，则要进行横向搜索
                continue;
            } else{
                path.add(s.substring(beginIndex, i + 1)) ;  // 注意：substring是左闭右开的
                // //起始位置后移，保证不重复
                // 进行纵向分割
                dfs(s, len, res, path, i + 1);  // i + 1 是为了确保后面的子集合中选择的元素不重复
                path.remove(path.size() - 1) ;
            }
        }
    }

    //判断是否是回文串
    private boolean isPalindrome(String s, int beginIndex, int endIndex) {

        while(beginIndex <= endIndex){
            if(s.charAt(beginIndex) == s.charAt(endIndex)){
                beginIndex ++ ;
                endIndex-- ;
                continue;
            }else{
                return false ;
            }
        }

        return true ;


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

