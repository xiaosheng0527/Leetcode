package leetcode.editor.cn;

//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 868 👎 0

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix{
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();

        int[][] martix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}} ;

        List<Integer> resList = new ArrayList<>() ;

        resList = solution.spiralOrder(martix) ;

        System.out.println(resList);
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此题我们也可以进行模拟，即左闭右开
    // 这里存在的一个问题是，这里的m X n 中的【m和n不一定相等，那么我们就只能把一般性的情况都考虑在内】
    // 核心思想：利用循环不变量 【左闭右开】，和 起始位置的确定，如果将要遍历的位置的已经被访问过，此时就需要撤回之前的操作

    // 这里的循环退出条件比较 奇葩，这里初步为利用 startX 和 startY访问到的元素 为已访问，此时就直接退出循环
    // 我们这题可以使用布尔数组来判定是否已经访问过，或者是修改 原矩阵的值来 进行判断。【面试的时候一定要先问面试官】
    // 这里我使用两种方法，这只需修改一点代码，但布尔数组其实也不会消耗太多的内存，反而对原矩阵无影响。

    // 1. 通过修改原矩阵的值来进行判断是否已经访问过
    // 这里将 已经访问过的元素 赋值为 0 ，之后每一次的遍历都需要判断是否 元素值为0，
    // 但是我看到 -100 <= matrix[i][j] <= 100，所以还是使用布尔数组吧！

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>() ;

        // 确定有几行 和 几列
        int m = matrix.length ;
        int n = matrix[0].length ;

        int startX = 0 ;
        int startY = 0 ;

        // 矩阵中间的位置，例如：m=n=3， 中间的位置就是(1，1)，n为5，中间位置为(2, 2)
        int mid = m / 2 ;   // 假设m == n，此时中间的值需要最后加进去

        int i,j = -1 ;

        // 这里还需要定义一个位移量，来标明在转过每一圈之前，同时在 左闭右开的情况下，需要限制其搜索范围
        // 即：每一圈循环，需要控制每一条边遍历的长度
        int offset = 1 ;

        // 用来判断是否已经被访问过
        boolean[][] isVisited = new boolean[m][n] ;

        // 这里是循环的条件，即 起始点没有被访问过
        // 这里设置 !isVisited[startX][startY] 即对角线的元素是否已经访问过，其实是我主观的认为，
        // 可能存在 范围的扩大冗余【即可能会多比较几次循环条件】，但这个条件总不会出错
        while(startX < m && startY < n && !isVisited[startX][startY]){

            // 向右搜索
            for(j = startY ; j < n - offset ; j++){

                if(!isVisited[startX][j]){
                    res.add(matrix[startX][j]) ;
                    isVisited[startX][j] = true ;
                } else {
                    // 如果是已经访问过，此时需要退出这个循环
                    //j-- ;
                    break;
                }


            }

            // 向下搜索
            for(i = startX ; i < m - offset ; i++){

                if(!isVisited[i][j]){
                    res.add(matrix[i][j]) ;
                    isVisited[i][j] = true ;
                } else {
                    // 如果是已经访问过，此时需要退出这个循环
                    // i-- ;  // 注意：这里不需要恢复现场【即无需将i置为之前的值】
                    // 因为我们这里设置了布尔数组来判断，而且 这里我们使用了四个方向的搜素，故肯定都会搜索到
                    // 除了 m == n 时，中间位置的数据可能取不到 需要 最后用 mid索引 对应的值来获取
                    break;
                }
            }

            // 向左搜索
            for(; j > startX ; j--){

                if(!isVisited[i][j]){
                    res.add(matrix[i][j]) ;
                    isVisited[i][j] = true ;
                } else {
                    // 如果是已经访问过，此时需要退出这个循环
                    //j++ ;
                    break;
                }
            }

            // 向上搜索
            for(; i > startX ; i--){

                if(!isVisited[i][j]){
                    res.add(matrix[i][j]) ;
                    isVisited[i][j] = true ;
                } else {
                    // 如果是已经访问过，此时需要退出这个循环
                    //i++ ;
                    break;
                }
            }

            // 此时也需要处理偏移量，否则可能会出现问题，即下标越界
            // 为什么需要加1，因为遍历完毕一圈后，之后需要遍历下一圈【向内螺旋】
            // 但我们遍历下一圈的时候，初始位置也需要向前移动，那这需要如何处理，此时就需要更新startX和startY的值
            offset += 1 ;

            // 当处理好一层循环后，此时需要重新调整startX和 startY的位置
            // 第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            startX++ ;
            startY++ ;
        }

        if(m == n && m % 2 == 1){
            res.add(matrix[mid][mid]) ;
        }
        return res ;




    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

