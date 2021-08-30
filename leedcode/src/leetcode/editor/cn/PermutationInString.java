package leetcode.editor.cn;

//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 104 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 
// 👍 396 👎 0

public class PermutationInString{
    public static void main(String[] args) {
        Solution solution = new PermutationInString().new Solution();
        String s1 = "aab" ;
        String s2 = "aamabaao" ;
        System.out.println(solution.checkInclusion(s1, s2));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *  请大家特别留意 winCount 的含义：
     *  滑动窗口中的字符要满足字符出现的次数 等于 s1 中对应字符出现的次数的时候才加 1，
     *  winCount 不仅统计了种类，还代表了次数。使得我们可以通过 winCount 的数值去了解整个滑动窗口的信息
        但要谨记一点的是：如果 滑动窗口中的字符要满足字符出现的次数 大于 s1 中对应字符出现的次数，此时先不做处理，在
        while(pCount == winCount){}循环下才进行判断，
     */
    // 利用滑动窗口的思想，以及存储字符的字典数组【Java里面直接没有自己】，就自己维护一个待匹配的字符串的频数数组
    public boolean checkInclusion(String s1, String s2) {

        char[] pattern = s1.toCharArray() ; // 待匹配的字符数组
        char[] text = s2.toCharArray() ;    // 原始的字符数组

        int pLen = pattern.length ; // 待匹配的字符数组的长度
        int tLen = text.length ;    // 原始的字符数组的长度

        // 维护一个字符数组pattern 在26个小写字母的数组【也可以用哈希表】中对应元素在索引值为0~25范围下出现的次数
        // 这里千万不要写成 new int[pLen]，因为我们 patternFrequency 是需要和 text 数组进行比较，而text数组存在a~z的字符，
        // 故如果写成上面的，会造成下标越界
        int[] patternFrequency = new int[26] ;
        for(int i = 0 ; i < pLen ; i++){
            patternFrequency[pattern[i] - 'a']++ ;  // 此数组保存 待匹配的字符数组 pattern 对应元素在索引值为 0~25【即26个字母】 范围下出现的次数
        }

        // 创建一个滑动窗口来实现移动，注意：此时的滑动窗口里面仅仅是保存着字符出现的次数，
        int[] windowFrequency = new int[26] ;    // 虽然这里我们初始化了26个int类型的数组，但实际上使用的只是pLen
        // 定义一个双指针，进行移动窗口,注意：此窗口的大小刚开始还没有趋于固定状态，故需要先进行动态移动为一个
        int left = 0 ;
        int right = 0 ;
        // 当滑动窗口中的某个字符个数 与 s1 中对应相等的时候才计数
        int winCount = 0 ;

        // 定义一个变量，记录 pattern 数组中 字符 一共出现的 字符种类
        int pCount = 0 ;
        for (int i = 0 ; i < 26 ; i++){
            if(patternFrequency[i] > 0){
                pCount++ ;
            }
        }


        while(right < tLen){

            // 此条件成立，说明在right指针遍历到的字符，即为 s1中存在的字符
            if(patternFrequency[text[right] - 'a'] > 0){
                windowFrequency[text[right] - 'a']++ ;  // 将对应的 字符的 频数（次数） +1
                // 当滑动窗口中出现的字符种类数和 s1 中出现的字符种类数相等（根据 winCount 的定义，对应次数也相等），

                // 如果某一个字符出现的次数恰好等于 s1 中对应字符出现的次数，winCount += 1
                // 切记：如果某一个字符出现的次数大于 s1 中对应字符出现的次数，那么此时频数就比 s1的要多，
                // 那么此时也不会有影响，后续的while循环中会对此条件进行剔除
                if(windowFrequency[text[right] - 'a'] == patternFrequency[text[right] - 'a']){
                    winCount++ ;
                }
            }
            // 此语句 无论上面的if语句是否成立都会继续移动执行
            right++ ;   // right指针向右继续遍历

            // 当滑动窗口中出现的字符种类数和 s1 中出现的字符种类数相等（根据 winCount 的定义，对应次数也相等），

            // 移动left指针是要在已经满足条件的情况下，然后再进行缩减滑动窗口，直到下面的right - left == pLen
            // 两个注意点：
            // 1.如果 不满足 pCount == winCount，则就不需要移动left指针
            // 2.如果 满足 pCount == winCount，可能刚好找到，返回；否则其他情况下都需要移动left指针
            while(pCount == winCount){

                // 并且 s2 上的滑动窗口的长度等于字符串 s1 的长度的时候，就找到了 s1 的一个排列
                // 为什么判断条件是right - left == pLen，而不是right - left + 1，
                // 因为上面的代码执行结束后，right++，但是此时的right并没有参与到下面的计算，只是指向了下一个位置
                // 我们这里判断，利用下标的减法，刚好可以抵消1个 1 ，故可以形象的看成 (right - 1) - left + 1
                if((right - 1) - left + 1 == pLen){
                    return true ;
                }

                // 如果此时 left指针指向的元素 在 pattern 中出现,则需要  将对应的 字符的 频数（次数） -1
                // 为什么要这样子，why？？？
                // 1.此时是为了剔除不连续的字符【注：剔除之后可能会造成winCount--，也可能winCount不受影响，这是取决于patternFrequency的频数】
                // 2.此时如果之前pattern中的种类数是一样的，但此时某些字符的频数大于 pattern ，那么就需要剔除
                // 上面的这两种情况其实是相互衔接的，一个有出现都会造成 不能匹配上
                // 所以需要移动左边界滑动窗口的left指针，继续搜索
                if(patternFrequency[text[left] - 'a'] > 0){
                    windowFrequency[text[left] - 'a']-- ;
                    if(windowFrequency[text[left] - 'a'] < patternFrequency[text[left] - 'a']){
                        winCount-- ;
                    }
                }
                // 此语句 无论上面的if语句是否成立都会继续移动执行
                // 如果对应的patternFrequency的频数 为0，说明此字符为无效 匹配，则也需要移动left指针
                // 切记：如果此时的left指针向右移动后，还是满足pCount == winCount，则不会跳出循环
                left++ ;
            }
        }
        return false ;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

