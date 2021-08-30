package leetcode.editor.cn;

//在给定的网格中，每个单元格可以有以下三个值之一： 
//
// 
// 值 0 代表空单元格； 
// 值 1 代表新鲜橘子； 
// 值 2 代表腐烂的橘子。 
// 
//
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。 
//
// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[2,1,1],[1,1,0],[0,1,1]]
//输出：4
// 
//
// 示例 2： 
//
// 输入：[[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
// 
//
// 示例 3： 
//
// 输入：[[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 10 
// 1 <= grid[0].length <= 10 
// grid[i][j] 仅为 0、1 或 2 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 387 👎 0

import java.util.Deque;
import java.util.LinkedList;

public class RottingOranges{
    public static void main(String[] args) {
        Solution solution = new RottingOranges().new Solution();
        int[][] grid =  {{2,1,1},{1,1,0},{0,1,1}} ;

        System.out.println(solution.orangesRotting(grid));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 使用bfs，将所有的 腐烂的 橘子当做是 一个整体，利用队列来模拟层次的关系
    // 定义四个方向的单位长度 的坐标
    int[] dx = {0,0,-1,1} ;
    int[] dy = {-1,1,0,0} ;
    public int orangesRotting(int[][] grid) {

        int m = grid.length ;
        int n = grid[0].length ;
        Deque<int[]> queue = new LinkedList<>() ;
        int newCount = 0 ; // 记录新鲜橘子的个数
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if (grid[i][j] == 1) {
                    newCount += 1 ;
                }else if(grid[i][j] == 2){
                    // 直接依次添加对应的坐标到队列中
                    queue.offer(new int[]{i,j}) ;   // 腐烂的橘子入队
                }else{
                    continue ;
                }
            }
        }
        // 此for循环结束后，全部坏橘子就相当于是放置在第一层，故而下面需要先全部遍历第一层

        int round = 0; // round 表示腐烂的轮数，或者分钟数【注意：最小的分钟数，即为遍历的层数】

        // 上面的for循环结束之后，就说明已经统计完新鲜橘子的个数，以及将坏橘子放进队列里
        // 如果此时的newCount == 0，那么尽管队列中还有节点，但之后就不用继续遍历，因为已经全部感染了
        while(newCount > 0 && !queue.isEmpty()){

            // 注意：这道题出现了严重的bug，我是一个个处理队头的坏橘子，
            // 而忽略坏的橘子是同步进行扩散的，所以我们要一层for循环处理所有的橘子，当此for循环结束后，才算过去了一分钟
            // 而且注意：我们设置的所有坏橘子是放在第一层【假设整体大坏橘子为第0层】，所以需要同步执行
            // 官方回答
            //但是用 BFS 来求最短路径的话，这个队列中第 1 层和第 2 层的结点会紧挨在一起，无法区分。
            // 因此，我们需要稍微修改一下代码，在每一层遍历开始前，记录队列中的结点数量 n ，然后一口气处理完这一层的 n 个结点。
            // 谨记：虽然坏橘子感染好橘子后，又添加了下一层的节点，然后因为len长度的限制，使得不会和第2层的执行冲突

            int len = queue.size() ;    // 获取当前层次的所有橘子

            // 下面的这个for循环很重要，避免掉坑
            for(int i = 0 ; i < len ; i++){
                int[] deal = queue.poll() ;
                int x = deal[0] ;
                int y = deal[1] ;

                // 代表一层的遍历
                for(int k = 0 ; k < 4 ; k++){
                    // 注意：这里不能进行值的覆盖，否则下次for循环x 和 y 就不是本身原来的坐标了
                    int sr = x + dx[k] ;
                    int sc = y + dy[k] ;

                    if(sr >= 0 && sr < m && sc >= 0 && sc < n && grid[sr][sc] == 1){

                        grid[sr][sc] = 2 ;
                        newCount-- ;
                        queue.offer(new int[]{sr,sc}) ;
                    }
                }
            }
            // 假设 第一层全部遍历结束后，此时才消耗1分钟，
            round++ ;
        }

        if(newCount > 0){
            return -1 ;
        }

        return round ;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

