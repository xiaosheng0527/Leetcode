package leetcode.editor.cn;

//在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。 
//
//
// 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。 
//
// 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。 
//
// 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。 
//
// 
//
// 示例 1： 
//
// 
//输入：mat = [[1,2],[3,4]], r = 1, c = 4
//输出：[[1,2,3,4]]
// 
//
// 示例 2： 
//
// 
//输入：mat = [[1,2],[3,4]], r = 2, c = 4
//输出：[[1,2],[3,4]]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n <= 100 
// -1000 <= mat[i][j] <= 1000 
// 1 <= r, c <= 300 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 224 👎 0

public class ReshapeTheMatrix{
    public static void main(String[] args) {
        Solution solution = new ReshapeTheMatrix().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *  注意：这道题的思路：
     *       1)将二维数组 nums 映射成一个一维数组；
     *       2）将这个一维数组映射回 r 行 c 列的二维数组。
     *         二维数组和一维数组的转换关系 ：此处以原先m * n 矩阵为例
     *         (i,j) -> i * n + j
     *         注释：(i,j) 代表二维数组，即相当于矩阵的索引点的坐标
     *              i * n + j   代表对应一维数组，即相当于一维矩阵 索引点的值
     *
     *  // 其实原先将mat映射为一个一维数组，
     *  // 而重塑后的r*c的矩阵其实也可以映射为一个一维数组，
     *  // 那么一维数组是公共的，并且一维数组的索引范围是[0,mn)
     *  // 而且切记：以下面原来的矩阵为例： m * n 二维矩阵
     *  //          二维数组其实可以拆成多个一维数组，而一维数组记录其元素个数的就是纵向索引(n),
     *  //          而和 横向索引m 没有任何关系
     */


    public int[][] matrixReshape(int[][] mat, int r, int c) {

        int m = mat.length ;
        int n = mat[0].length ;
        // 如果此时
        if(m * n != r * c){
            return mat ;
        }
        // 将矩阵元素模拟成一维数组获取下标的方式来访问对应的元素
        // 即为 将二维数组 nums 映射成一个一维数组；

        // 将这个一维数组映射回 r 行 c 列的二维数组。
        int[][] res = new int[r][c] ;
        for(int i = 0 ; i < m * n ; i++){   // i代表二维矩阵映射成一维数组的纵向索引
            // 其实原先将mat映射为一个一维数组，
            // 而重塑后的r*c的矩阵其实也可以映射为一个一维数组，
            // 那么一维数组是公共的，并且一维数组的范围都是[0,mn)
            // 而且切记：二维数组其实可以拆成多个一维数组，而一维数组记录其元素个数的就是纵向索引
            // 只是对于每一行的纵向索引的有效值范围不同，故会衍生出不同的重塑矩阵
            res[i / c][i % c] = mat[i / n][i % n] ;
        }

        return res ;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

