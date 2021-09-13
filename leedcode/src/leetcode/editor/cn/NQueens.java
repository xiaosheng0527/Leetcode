package leetcode.editor.cn;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2：
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 数组 回溯 
// 👍 1002 👎 0

import java.util.ArrayList;
import java.util.List;

public class NQueens{

    // 注意：这里是说彼此间的皇后不能攻击，即在递归树中的每一层处都必须满足：
    //      任何两个皇后都不能处于同一条横行、纵行或斜线上。 
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        solution.solveNQueens(4) ;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此处好像无法使用一维数组来算，即无法用一维数组来存储每种皇后的摆法
    // 另外一种做法是将n个皇后摆放到二维数组中，然后将每个一维数组以String的形式保存到res中
    // 但是我看了一道题目的做法，又可以了，所以这里我试试
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList<>() ;

        // 主要是保存对应行下的皇后的位置
        // 注意：这里的索引值代表的是行，元素值代表的是列值
        int[] queen_pos = new int[n] ;

        // 这里使用到一个list来保存每一行的Q和.的String信息，将其保存到一维集合中
        List<String> list = new ArrayList<>() ;

        dfs(res,queen_pos,n,0,list) ;

        return res ;


    }

    private void dfs(List<List<String>> res, int[] queen_pos, int n, int row, List<String> list) {

        if(row >= n){

            for(int i = 0 ; i < n ; i++){   // i：代表行标
                StringBuffer sb = new StringBuffer() ;
                for(int j = 0 ; j < n ; j++){   // j：代表列标
                    if(queen_pos[i] == j){  // 判断当前遍历到的元素的值是否和 列索引相等，若相等，则说明这是我们找到的皇后
                        sb.append('Q') ;
                    }else{
                        sb.append('.') ;
                    }
                }
                list.add(sb.toString()) ;
            }
            res.add(list) ;
            list.clear();   // 清空list
        } else{
            // 说明还没有摆完最后一个皇后
            // 这里的i代表列的信息
            for(int i = 0 ; i < n ; i++){
                queen_pos[row] = i ;    // 假设可以添加此皇后，i代表列数，将所在的列索引作为元素值
                // 判断当放置第row个皇后到i列时，是否冲突
                if(judge(row,queen_pos)){
                    dfs(res, queen_pos, n, row + 1, list);
                }

                // 冲突，就继续循环i+1， x[row] = i ; 即将第row个皇后，放置在本行的后一个列位置
                continue ;    // 加上这一行，主要是可以清晰的明白要进行下一次循环，找第row个皇后的，第i列的下一个位置，回溯。
            }
        }


    }

    private boolean judge(int row, int[] queen_pos) {

        for(int i = row - 1 ; i >= 0 ; i--){

            if(queen_pos[row] == queen_pos[i] || Math.abs(row - i) == Math.abs(queen_pos[row] - queen_pos[i])){
                return false ;
            }
        }

        return true ;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}

