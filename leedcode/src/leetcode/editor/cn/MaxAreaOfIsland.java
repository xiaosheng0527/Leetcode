package leetcode.editor.cn;

//给定一个包含了一些 0 和 1 的非空二维数组 grid 。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。) 
//
// 
//
// 示例 1: 
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// [[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 527 👎 0

public class MaxAreaOfIsland{
    public static void main(String[] args) {
        Solution solution = new MaxAreaOfIsland().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxAreaOfIsland(int[][] grid) {

        int res = 0 ;
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == 1){
                    res = Math.max(res, dfs(grid, i, j)) ;
                }
            }
        }
        return res ;
    }

    public int dfs(int[][] grid,int i,int j){
        // i < 0 || i >= grid.length || j < 0 || j >= grid[0].length：这个边界条件是总的区域的边界条件
        // grid[i][j] == 0：这个边界条件是陆地和水上的边界条件
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return 0 ;
        }
        grid[i][j] = 0 ;
        int area = 1 ;  // 因为上面已经设置为刚刚访问，故面积初始值为1
        area += dfs(grid, i - 1, j) ;
        area += dfs(grid, i + 1, j) ;
        area += dfs(grid, i, j - 1) ;
        area += dfs(grid, i, j + 1) ;
        // 注意：这里的area其实在每一个递归栈中都会存在一个上下左右进行判断，
        // 若满足陆地且未访问过，则最后得到结果area再返回上层，回溯
        // 而每一个递归栈因为返回了一个结果，给上层的栈提供了值，从而当前递归栈又可以求出
        return area ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

