package leetcode.editor.cn;

//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1708 👎 0

public class LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        String[] strs = {"flower","flow","flight"} ;
        //solution.longestCommonPrefix(strs) ;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*// 注意：方法1：横向扫描
    // 具体思想就是先将第一个字符串作为已经排好的公共前缀，依次和后面的字符串进行比较，然后重复此工作
    // 故可以再定义一个方法来获取比较的两个字符串的公共前缀


//      @param strs  待求公共前缀的字符串数组
//      @return

    public String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0){
            return "" ;
        }

        String prefixFirst = strs[0] ;    // 将第一个字符串先作为公共前缀

        String tempCommonPrefix = "" ;

        for(int i = 1 ; i < strs.length ; i++){
            tempCommonPrefix = commonPrefix(prefixFirst,strs[i],tempCommonPrefix);
            prefixFirst = tempCommonPrefix ;
        }
        return prefixFirst ;

    }



//      @param prefixFirst   已经是公共前缀字符串，没比较一次后要将prefixFirst = commonPrefix ，即覆盖prefixFirst
//      @param compare       待比较的字符串
//      @param tempCommonPrefix  在for循环中的临时公共前缀字符串
//      @return  代表返回的临时公共前缀字符串

    public String commonPrefix(String prefixFirst,String compare,String tempCommonPrefix){
        int minLength = Math.min(prefixFirst.length(), compare.length()) ;  // 以最小的字符串长度值进行判断前缀的一个条件
        for(int i = 0 ; i < minLength ; i++){
            if(prefixFirst.charAt(i) == compare.charAt(i)){
                continue ;
            }else{
                tempCommonPrefix = prefixFirst.substring(0,i) ;
                return tempCommonPrefix ;
            }
        }
        // 跳出for循环说明，刚好prefixFirst和compare的minLength中的前缀相同，故直接返回
        if(prefixFirst.length() <= compare.length()){
            return tempCommonPrefix = prefixFirst ;
        }
        return tempCommonPrefix = compare ;
    }*/

    // 方法2：纵向扫描
    public String longestCommonPrefix(String[] strs){

        if(strs == null || strs.length == 0){
            return "" ;
        }
        int minLength = strs[0].length() ;
        // 获取字符串中长度的最小值
        for(int i = 0 ; i < strs.length ; i++){
            minLength = Math.max(strs[i].length(),minLength) ;
        }

        for(int j = 0 ; j < minLength ; j++) {    // j代表字符串的长度
             for(int i = j ; i < minLength ; i++){

             }
        }
        return null ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

