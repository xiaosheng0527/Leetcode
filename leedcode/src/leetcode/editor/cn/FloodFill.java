package leetcode.editor.cn;

//有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。 
//
// 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。 
//
// 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方
//向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。 
//
// 最后返回经过上色渲染后的图像。 
//
// 示例 1: 
//
// 
//输入: 
//image = [[1,1,1],[1,1,0],[1,0,1]]
//sr = 1, sc = 1, newColor = 2
//输出: [[2,2,2],[2,2,0],[2,0,1]]
//解析: 
//在图像的正中间，(坐标(sr,sc)=(1,1)),
//在路径上所有符合条件的像素点的颜色都被更改成2。
//注意，右下角的像素没有更改为2，
//因为它不是在上下左右四个方向上与初始点相连的像素点。
// 
//
// 注意: 
//
// 
// image 和 image[0] 的长度在范围 [1, 50] 内。 
// 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。 
// image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 202 👎 0

public class FloodFill{
    public static void main(String[] args) {
        Solution solution = new FloodFill().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * @param image     待处理的二维数组
     * @param sr    行坐标
     * @param sc    列坐标
     * @param newColor  染上新的颜色
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        dfs(image, sr, sc,newColor,image[sr][sc]);  // image[sr][sc]刚开始调用的时候，image[sr][sc]还未访问过，所以是旧的颜色
        return image ;
    }

    /**
     *
     * @param image     待处理的二维数组
     * @param row       行坐标
     * @param col       列坐标
     * @param newColor  处理后染上新的颜色
     * @param oldColor  还未处理前的颜色，注意：任何没有被处理 过的旧色，这里定义的值都是一样的
     */
    public void dfs(int[][] image,int row,int col,int newColor,int oldColor){
        // 如果满足下面的 条件，则就不需要染色了，直接返回image到上一层调用点
        if(row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != oldColor || newColor == oldColor){
            return ;
        }
        // image[row][col] == oldColor，则说明此时正在访问的还没有被染上新色
        image[row][col] = newColor ;    // 先将刚访问到的像素染成新的颜色

        // 注意：这里主要是涉及到dfs，回溯后的恢复现场没有提及，因为，这里的涂色是永久性的，即不会再擦掉
        // 按图索骥，没有涉及到多条路径问题，故不用恢复现场
        // 注意：这里的oldColor变量虽然不像image是共享的，但是oldColor初始值是一样的，故无所谓，dfs的第五个参数就可以是oldColor
        dfs(image, row - 1, col, newColor, oldColor);
        dfs(image, row + 1, col, newColor, oldColor);
        dfs(image, row, col - 1, newColor, oldColor);
        dfs(image, row, col + 1, newColor, oldColor);

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

