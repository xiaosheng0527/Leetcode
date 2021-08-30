package leetcode.editor.cn;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1423 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
      * @param digits   按键的2~9对应的字符串，eg "23"
     *                 此时最好的方法就是将'2'对应的字符串 "abc" ,'3'对应的字符串"def"分别存储到哈希表中
     * @return
     *
     * 首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
     *
     * 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
     * 该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，
     * 然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。
     * 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
     */
    public List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<>() ; // 用来保存字母组合的情况
        StringBuffer combination = new StringBuffer();  // 最好是在外面传入到dfs，不然每一次递归调用dfs，可能还会出现重复声明和定义
        if(digits.length() == 0){
            return combinations ;
        }

        Map<Character,String> numsMap = new HashMap<>() ;
        numsMap.put('2', "abc") ;
        numsMap.put('3', "def") ;
        numsMap.put('4', "ghi") ;
        numsMap.put('5', "jkl") ;
        numsMap.put('6', "mno") ;
        numsMap.put('7', "pqrs") ;
        numsMap.put('8', "tuv") ;
        numsMap.put('9', "wxyz") ;

        dfs(combinations, numsMap,digits,0, combination);   // index从0开始，即 digits下标为0的位置

        return combinations ;

    }

    /**
     *
     * @param combinations  保存数组组合的集合
     * @param numsMap       号码映射的哈希表
     * @param digits        存储数字对应的字符串
     * @param index         digits 对应的下标
     * @param combination   用于拼接digits中每一个字符[key]对应的value的所有组合
     *
     * 注意：此处的的电话号码的拼接和回溯的思想，以及dfs很相似，即一条道走到黑，找不到再回溯到上一个调用点，然后继续递归
     *      局部变量一定要自己初始化，否则输出会报错
     */
    public void dfs(List<String> combinations,Map<Character,String> numsMap,String digits,int index,StringBuffer combination){
        // 递归出口，即dfs到最底得到一个答案，然后回溯，【注意理解回溯的思想，和dfs类似】
        if(index == digits.length()){
            combinations.add(combination.toString()) ;
        } else{
            char ch = digits.charAt(index) ;    // 获取到digits中下标为index对应的字符
            String str = numsMap.get(ch) ;  // 获取到digits中下标为index对应的字符 映射的 字符串
            for(int i = 0 ; i < str.length() ; i++){
                combination.append(str.charAt(i)) ; // 获取到获取到第一个数字映射的字符
                dfs(combinations, numsMap, digits, index + 1, combination); // 相当于回溯，dfs
                combination.deleteCharAt(index) ;   // 删除刚好结合的后一个字符，然后进行遍历for循环，再次结合，然后递归入栈到dfs，满足条件，就添加一个结合的字符串，然后再次回溯，删除
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

