package leetcode.editor.cn;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1337 👎 0

import java.util.Arrays;

public class MinimumWindowSubstring{
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        String s = "ADOBECODEBANC" ;
        String t = "ABC" ;

        solution.minWindow(s, t) ;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *
      * @param s    待检测的字符串
     * @param t     待匹配的字符串
     * @return
     */
    public String minWindow(String s, String t) {

        // 获取二者的长度
        int sLen = s.length() ;
        int tLen = t.length() ;

        // 这是一个特殊条件
        if(sLen == 0 || tLen == 0 || sLen < tLen){
            return "" ;
        }

        // 将字符串转换为字符数组，这样便于后续的构造哈希表
        char[] sCharArray = s.toCharArray() ;
        char[] tCharArray = t.toCharArray() ;

        // 此处的distance 代表 滑动窗口内部包含 t中字符的个数，窗口内单个字符个数等于t中对应的字符个数的时候不再增加
        // 剖析：即如果滑动窗口的单个字符个数等于t中对应的字符个数的时候，此时后面的如果滑动窗口向右扩张后右边界还是这个字符
        // 即超过了t字符串的单个字符的个数，此时distance是不用计数的。因为可以冗余性的包含
        // 注意：什么时候需要进行收缩滑动窗口呢？
        //      若此时滑动窗口的对应的字符包含了 t字符串的 所有字符，此时就可以把这个子串记录下来，然后和原来得到的子串的长度比较，那个比较小，就保存下来
        // 当distance 等于 tLen 时候，此时就可以压缩窗口了
        int distance = 0 ;

        int begin = 0   ;   // 用于记录最后包含 t字符串的 最小子串的起始位置，这是需要动态判断的

        // 定义一个最小长度
        int minSubLength = Integer.MAX_VALUE ;   // 将res定义为int型 最大的值，此时没有比其更大的值,作用为返回的值，即最小子串的长度

        // 用数组来构建哈希表，注意：题目给出的 1 <= s.length, t.length <= 105
        // 说明 顶多数据为10万，故可以不使用 HashMap来存放

        // 定义字符频数的一维数组，记录 tCharArray 中 字符出现的次数
        // 数组初始化为 0// 为什么要初始化为128，因为这已经可以涵盖 ascii码的所有值【0~127】
        int[] tCounts = new int[128] ;

        for(char ch : tCharArray){  // 这里的char类型的数据在计算的时候会自动转换为int【ascii码】
            tCounts[ch] += 1 ;
        }

        // 测试了，没问题
//        System.out.println(Arrays.toString(sCounts));
//        System.out.println(Arrays.toString(tCounts));

        // 此时需要维护一个滑动窗口,也是需要维护一个哈希表
        int[] winCounts = new int[128] ;



        // 这里设定 为 左闭右开，故刚开始是不能构成子串区间【即滑动窗口】 [left,right) ，left = 0 ， right = 0 ，说明刚开始还没有元素进入滑动窗口
        // 哈哈，此时你是不是会想到Carl大佬说道的 区间不变性
        // 在遍历的时候，right左边的所有的元素是程序所可以看到的，而且窗口的长度恰好就是 right - left

        // 其实你可以注意到，刚开始left=0，right=0，[left,right) 是没有公共区间的，那刚开始判断，如果符合 才right++，此时的[left,right)才出现的所谓的含有一个元素的区间
        // 即从这我们看出，原来的 right 对于 [left,right) 还是不可见的，直到满足条件，right++，则之前的right才包含在区间里
        // 综上：这里的定义的区间 其实 是由人为控制的。
        // right对应的值是程序不可见的，因为这是开区间
        int left = 0 ;
        int right = 0 ;

        // 这是退出循环的条件，即遍历结束了，右指针right遍历结束
        while(right < sLen){

            // 剔除无效字符，即 s 中 不包含 t 中 的字符要过滤，不然考虑后会很复杂
            if(tCounts[sCharArray[right]] == 0){
                // 此时将 right指针右移，然后 continue，判断下一个位置
                right++ ;
                continue;
            }

            // 如果是有效字符，此时就要做逻辑性判断了
            if(winCounts[sCharArray[right]] < tCounts[sCharArray[right]]){
                // 说明滑动窗口的单个字符个数小于t中对应的字符个数
                // 此时需要更新 distance
                distance++ ;
            }

            // 执行这里的代码有两层含义：
            // 1. 如果是 winCounts[sCharArray[right]] >= tCounts[sCharArray[right]]，
            //      此时distance无需+1，但还是需要将字符出现的次数+1，同时right++，因为right对应的是开区间，对(right-left)长度的元素是无影响的。
            //      如果此时right 没有进行++，这会导致 [left,right) 所在的区间是上一次的判断下的区间，这肯定会出大问题
            // 2. 如果是 winCounts[sCharArray[right]] < tCounts[sCharArray[right]],
            //      此时distance需要+1，但还是需要将字符出现的次数+1，同时right++，因为right对应的是开区间，对(right-left)长度的元素是无影响的。

            // 故无论是否distance++，这里的代码都需要执行，因为你要永远记住 [left,right) right所在的右边界并不是闭区间
            // 如果是[left,right],那么对应的长度即为 right - left + 1，此时就需要另外考虑了
            // 更新滑动窗口中的字符出现的次数
            // 这里有两层含义，即满足条件，则需要将次数+1，然后此时先不要right++，因为可能满足包含关系

            // -----------------------------------------------------------------------------------------------
            // !!-------------------------------------------------------------------------------------------!!
            // 等等，我觉得 左闭右开 或者 左闭右闭其实只是影响着后面元素的长度
            // 左闭右闭 [left,right]，元素的长度为 right - left + 1
            // 左闭右开 [left,right)，元素的长度为 right - left
            // 这并没有影响上面考虑的这么复杂，无论如何在处理完一个元素后，right指针都需要右移，因为这是下一次判断的条件，如果没有right++，下一次用的还是当前循环下的right，那不是重复操作了吗？？？
            // 比较具有代表性的是这句话， 在遍历的时候，right左边的所有的元素是程序所可以看到的，而且窗口的长度恰好就是 right - left；

            // 即 right++，不管扩展窗口或者压缩窗口都是必须要执行的语句，需要判断，如果符合 distance == tLen 了 则压缩窗口，但right指向的元素程序不可见，因为还没有涉及到扩展窗口，
            // 如果符合 distance < tLen ，此时左边界固定，然后判断之前 right++ 所指向的元素是否满足条件，满足了 则当前 right指向的元素有效，此时需要 再次将 right++，

            // 综上：right指针的移动只是先起到一个向前移动的作用，此时还并没有处理任何代码语句，但这不能硬性理解为 只要right指针移动就代表可满足，这是不靠谱的理解方法。
            // 刚好这里使用 左闭右开区间 [left,right), 这里的right就可以体现这个特点。
            winCounts[sCharArray[right]]++ ;
            right++ ;

            // 当distance 等于 tLen 时候，此时就可以压缩窗口了
            while(distance == tLen){

                // 可行解，可行解可以有多个，但局部最优解只能是一个，即最后返回的子串
                if(right - left < minSubLength){
                    minSubLength = right - left ;
                    begin = left ;  // 重置当前的begin起始位置，即字符串中 包含t字符串的起始位置，这只是
                }

                // 剔除无效字符，即 s 中 不包含 t 中 的字符要过滤，不然考虑后会很复杂
                if(tCounts[sCharArray[left]] == 0){
                    // 此时将 right指针右移，然后 continue，判断下一个位置
                    left++ ;
                    continue;
                }

                // 如果是有效字符，此时就要做逻辑性判断了
                // 注意：这是一个边界线，若原来对应的某个字符刚好相等，此时压缩过程中，才会导致distance--

                // 而如果大于，那就无需对 distance 进行 -1，继续进行压缩，即左边界

                // 切记：这里永远不会进行判断 winCounts[sCharArray[left]] < tCounts[sCharArray[left]] 条件
                // 因为 上面的 原来对应的某个字符刚好相等，此时压缩过程中，才会导致distance-- 后，
                // 此时distance 必定 小于 tLen，此时就会退出循环，然后进行下一次循环，判断当前的right指针对应的代码块
                if(winCounts[sCharArray[left]] == tCounts[sCharArray[left]]){
                    // 说明滑动窗口的单个字符个数小于t中对应的字符个数
                    // 此时需要更新 distance
                    distance-- ;
                }

                // 执行这里的代码有两层含义：
                // 1. 如果是 winCounts[sCharArray[right]] >= tCounts[sCharArray[right]]，
                //      此时distance无需+1，但还是需要将字符出现的次数+1，同时right++，因为right对应的是开区间，对(right-left)长度的元素是无影响的。
                //      如果此时right 没有进行++，这会导致 [left,right) 所在的区间是上一次的判断下的区间，这肯定会出大问题
                // 2. 如果是 winCounts[sCharArray[right]] < tCounts[sCharArray[right]],
                //      此时distance需要+1，但还是需要将字符出现的次数+1，同时right++，因为right对应的是开区间，对(right-left)长度的元素是无影响的。

                // 故无论是否distance++，这里的代码都需要执行，因为你要永远记住 [left,right) right所在的右边界并不是闭区间
                // 如果是[left,right],那么对应的长度即为 right - left + 1，此时就需要另外考虑了
                // 更新滑动窗口中的字符出现的次数
                // 这里有两层含义，即满足条件，则需要将次数+1，然后此时先不要right++，因为可能满足包含关系
                winCounts[sCharArray[left]]-- ;
                left++ ;
            }


        }

        if(minSubLength == Integer.MAX_VALUE){
            return "" ;
        } else {

            // 这是最后的局部最优解
            return s.substring(begin, begin + minSubLength) ;   // 左闭右开
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

