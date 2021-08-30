package leetcode.editor.cn;

//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。 
//
// 
//
// 示例：
//输入：S = "a1b2"
//输出：["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入：S = "3z4"
//输出：["3z4", "3Z4"]
//
//输入：S = "12345"
//输出：["12345"]
// 
//
// 
//
// 提示： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 字符串 回溯 
// 👍 290 👎 0

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation{
    public static void main(String[] args) {
        Solution solution = new LetterCasePermutation().new Solution();
        String s = "a1b2" ;
        List<String> res = new ArrayList<>() ;
        res = solution.letterCasePermutation(s) ;
        // [a1b2, A1b2, A1B2, a1B2] 输出结果的顺序和答案不同
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 注意：下面的代码可以见图解
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        // 这里将String类型的字符串S转换为StringBuilder类型的对象
        dfs(new StringBuilder(S), 0, res);  // 从索引值为0开始搜索
        return res;
    }

    /**
     *
     * @param combination   其实combination这个变量相当于一个路径path，但是这里面已经存在了数据，若想改变数据，直接将对应的值进行修改即可
     *                      而且为什么我们不用再定义一个全新的path路径字符串来保存每一条路径下的结果，因为combination一次只需要修改一个值，故无需另外的开销【进行原地操作的开销小】
     *                      那么对于全排列和组合的题目中，因为全排列中元素的位置顺序不同，组合中，元素的位置也不同，这是如果用combination会扩大代码逻辑的难度【不建议原地操作，开销大】
     *                      那还不如就直接创建一个path路径变量，集合，来保存每一次的结果，模拟栈的功能
     * @param index         字符串的索引值，范围是[0,combination.length()-1]
     * @param res
     */
    public void dfs(StringBuilder combination, int index, List<String> res) {
        // 递归出口
        if(index >= combination.length()) {
            res.add(combination.toString());
            return;
        }
        char ch = combination.charAt(index);
        if(ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
            // 保存现场，作用在于：左右子树中进行大小写转换之后，要进行恢复现场，其余时候都用不到
            char temp = ch ;
            // 搜索原字母的组合，一条道走到黑
            dfs(combination, index+1, res);

            // setCharAt()方法：指定索引处的字符设置为c
            // combination.setCharAt(index, (char)(ch ^ 32));
            ch = (char)(ch - 'a' >= 0 ? ch - 32 : ch + 32);
            combination.setCharAt(index, ch);

            // 搜索转换字母大小写后的组合
            dfs(combination, index+1, res);

            // 左右子树中进行大小写转换之后，要进行恢复现场，以便对于下一次大小写转换过程中，只会修改一个地方，其余位置和原始字符串一致
            combination.setCharAt(index, temp);

        } else {
            dfs(combination, index+1, res);				// 该位为数字，直接往后搜
        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)

}

