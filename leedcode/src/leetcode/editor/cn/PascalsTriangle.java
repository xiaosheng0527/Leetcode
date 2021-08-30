package leetcode.editor.cn;

//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 
// 👍 542 👎 0

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle{
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 构造杨辉三角形，可以使用动态规划的思想，即下一行需要上面行已经排好的情况下才可以进行排这一行
    // 注意：杨辉三角形的图解中看着是这普通三角形，但是放在二维集合中相当于一个直角三角形
    // 但是，这并不影响我们的解题
    public List<List<Integer>> generate(int numRows) {
        // 初始化二维集合
        List<List<Integer>> triangle = new ArrayList<>() ;
        // 遍历每一行,逐行处理，即利用到动态规划
        for(int i = 0 ; i < numRows ; i++){
            // 初始化遍历二维集合中一维集合
            // 注意：此处的一维集合并不共享，因为它们隶属不同的行
            // 所以不能放在for循环外面
            List<Integer> row = new ArrayList<>() ;
            // 针对每一行，将 下标为j=0 和 j=i,赋初值为1
            for(int j = 0 ; j <= i ; j++){
                if(j == 0 || j == i){
                    row.add(1) ;
                } else{
                    row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j)) ;
                }

            }
        }

        return triangle ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

