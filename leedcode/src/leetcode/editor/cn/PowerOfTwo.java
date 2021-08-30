package leetcode.editor.cn;

//给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1
//输出：true
//解释：20 = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 16
//输出：true
//解释：24 = 16
// 
//
// 示例 3： 
//
// 
//输入：n = 3
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：n = 4
//输出：true
// 
//
// 示例 5： 
//
// 
//输入：n = 5
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -231 <= n <= 231 - 1 
// 
//
// 
//
// 进阶：你能够不使用循环/递归解决此问题吗？ 
// Related Topics 位运算 递归 数学 
// 👍 394 👎 0

public class PowerOfTwo{
    public static void main(String[] args) {
        Solution solution = new PowerOfTwo().new Solution();
        int big = 4 << 2 ;
        System.out.println(big);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //    除了使用二进制表示判断之外，还有一种较为取巧的做法。
    //    在题目给定的 32 位有符号整数的范围内，最大的 22 的幂为 2^{30} = 10737418242 。
    //    我们只需要判断 n 是否是 2^{30}的约数即可

    // 左移30位，注意：移位运算一般都是适用于二进制
    // 所以这里 原先输入的值都会先转换为二进制，然后再进行移位计算
    static final int BIG = 1 << 30 ;    // 这里的1 同时也当做 2^0,左移30位，得到2^30
    public boolean isPowerOfTwo(int n) {
        return n > 0 && BIG % n == 0 ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

