package leetcode.editor.cn;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 5842 👎 0

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {


        Map<Character,Integer> map = new HashMap<>() ;
        //char[] chars = s.toCharArray() ;
        int maxLen = 0 ;
        int left = 0 ;  // 作为滑动窗口的左边界
        // 当前的right可看做是快指针【即遍历到当前滑动窗口的右边界，但这一定是最终的有边界】，但这需要和前面的maxLen取最大值
        for(int right = 0 ; right < s.length() ; right++){
            char ch = s.charAt(right) ;
            if(map.containsKey(s.charAt(ch))){
                left = Math.max(map.get(ch) + 1,left) ; // 覆盖左边界,避免后面出现 map.get(ch) + 1 出现歧义
            }
            // 这里是添加right指针指向字符串对应的字符，将其添加到
            map.put(ch, right) ;
            maxLen = Math.max(maxLen, right - left + 1) ;

        }
        return maxLen ;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}

