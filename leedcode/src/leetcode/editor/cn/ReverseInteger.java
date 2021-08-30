package leetcode.editor.cn;

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2966 👎 0

public class ReverseInteger{
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        System.out.println(solution.reverse(-123));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        // 注意 : 小于Integer.MIN_VALUE和大于Integer.MAX_VALUE的数不存在,出现overflow，溢出
        if(x == Integer.MIN_VALUE){ // 说明，并不存在反转后的数值，会溢出，故返回0
            return 0 ;
        }
        int signal = x < 0 ? -1 : 1 ;
        x *= signal ;
        int res = 0 ;
        while(x > 0){
            int n = res ;
            n *= 10 ;
            n += x % 10 ;
            x /= 10 ;
            if(n / 10 != res){
                return 0 ;  // 说明overflow，溢出，而且如果存在overflow，也只是在最后一次判断时会出现overflow
                            // 但我们不清楚到底是哪一次会出现overflow，可能算完了还没有overflow，故每一次都要进行judge
                            // 因为正常数，没有溢出的情况下，n / 10 == res 恒成立
            }
            res = n ;   // 将res的值更新成n

        }
        return signal * res ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

