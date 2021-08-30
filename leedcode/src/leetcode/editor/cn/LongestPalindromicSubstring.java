package leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3890 👎 0

public class LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("babdcbaabcd"));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 暴力解法
    // 注意：牛客17题是求最长回文子串的长度，力扣是返回最长回文子串
    // 暴力破解
    public String longestPalindrome(String s) {

        int len = s.length() ;
        if(len < 2){    // 如果字符串长度小于2，则直接返回此字符串作为回文串
            return s ;
        }
        // 注意：截取字符串会造成一定的性能消耗，故一个等价的方式是
        // 记录最长回文子串的起始下标和它的长度 ，在最后输出的时候进行截取就可以了
        int maxLen = 1; // 回文串的最大长度，其值的重置：是在出现一个子字符串长度> maxLen 且此子字符串是回文串
        int begin = 0 ; // 回文串的起始索引位置，其值的重置是：是在出现一个子字符串长度> maxLen 且此子字符串是回文串

        // s.charAt(i)  每次都会检查数组下标越界，因此先转换为字符数组，这一步非必须
        char[] charArray = s.toCharArray() ;

        // 枚举所有长度严格大于1的子串，charArray[i...j]
        for(int i = 0 ; i < len - 1 ; i++){     // 只需要处理(len-2)次，因为下面的j=i+1刚好处理最后一个位置的元素
            for(int j = i + 1 ; j < len ; j++){
                // 最长回文串：长度>之前的max，且，是回文串
                // 注意：这里为什么if语句的判断是 j-i+1，因为i和j是子字符串的下标，我们要获取其长度即为j-i+1
                // eg [9,10]中有几个元素， 即为 10 - 9 + 1 【我们要加上本身的那个数】
                if(j - i + 1 > maxLen && validPalindromic(charArray, i, j)){
                    // 这两个值的具体重置在，前面定义的时候就指明了
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);  // 注意：substring()方法是左闭右开，非原地操作
    }

    /**
     验证子串s[left...right],是否为回文
     大家最好由浅入深
     */
    private boolean validPalindromic(char[] charArray,int left,int right) {
        while(left < right){   // 判断是否为回文串时候，退出循环的条件是left >= right，即左指针指向右指针的位置或右边
            if(charArray[left] != charArray[right]) {
                return false ;
            }
            left++ ;
            right-- ;

        }
        return true ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

