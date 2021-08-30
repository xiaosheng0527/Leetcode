package leetcode.editor.cn;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1305 👎 0

public class NumberOfIslands{
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        char[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}} ;
        solution.numIslands(grid);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {

        if(grid == null || grid.length == 0){
            return 0 ;
        }

        // 这里使用二层for循环遍历二维数组，得到岛屿的数量
        // 一般最好在一个函数里面保存一个变量count来记录岛屿的数量
        // 最好不要放在dfs里面，不然有可能会出现重置变量，那么修改后的失效了

        int num = 0 ;

        for(int i = 0 ; i < grid.length ; i++){

            for(int j = 0 ; j < grid[0].length ; j++){

                if(grid[i][j] == '1'){
                    num ++ ;
                    dfs(grid, i, j) ;
                }
            }
        }

        return num ;
    }

    /**
     *
     * @param grid  待遍历的二维数组
     * @param i     行标
     * @param j     列标
     * @return      返回值即为有几片岛屿，注：如果找到了就一直找
     *
     * // 切记：dfs一般的返回值void，如果是int类型的，那么必然存在栈的作用，即变量的维护，临时存储变量的值
     * // 栈的作用，而此处无需此作用，故需要设置为void
     */
    private void dfs(char[][] grid, int i, int j) {

        int rowLen = grid.length ;
        int colLen = grid[0].length ;


        // 确定临界条件 1. 遇到四周的边，或者遇到海域
        if(i < 0 || i >= rowLen || j < 0 || j >= colLen || grid[i][j] == '0'){
            return  ;
        }
        // 将 陆地区域【1】置为【0】，来记录已经访问过陆地的区域，
        // 这里无需一个boolean数组来记录是否已经访问，直接修改一个变量值进行模拟定义
        //此处是重点

        grid[i][j] = '0' ;
        dfs(grid, i - 1,j);
        dfs(grid, i + 1,j);
        dfs(grid, i,j - 1);
        dfs(grid, i,j + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

