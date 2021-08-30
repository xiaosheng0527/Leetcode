package leetcode.editor.cn;

//给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。 
//
// 两个相邻元素间的距离为 1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
//输出：[[0,0,0],[0,1,0],[0,0,0]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
//输出：[[0,0,0],[0,1,0],[1,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n <= 104 
// 1 <= m * n <= 104 
// mat[i][j] is either 0 or 1. 
// mat 中至少有一个 0 
// 
// Related Topics 广度优先搜索 数组 动态规划 矩阵 
// 👍 464 👎 0

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Zero1Matrix{
    public static void main(String[] args) {
        Solution solution = new Zero1Matrix().new Solution();
        int[][] mat = {{0,0,0},{0,1,0},{0,0,0}} ;
        int[][] res = solution.updateMatrix(mat) ;
        for(int i = 0 ; i < res.length ; i++){
            System.out.println(Arrays.toString(res[i]));
        }
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 题目给出了多个1，要找出每个1到0的最近曼哈顿距离。由于1到0的距离和0到1的距离一样的，
     * 所以其实我们可以换个思维：找出每个0到1的距离。
     * 因此，题目可以抽象成：多个起始点的BFS。恭喜你已经解决了一半问题。
     * 但是，此时你也可以不用换个思维，但是这时候就需要多判断，并不能只修改mat[i][j] == 1,这会曲解此算法
     *
     * 那么为什么不可以从1来找到0的最近距离？
     * 因为bfs相当于满天开花，即向外层扩展，
     * 那么此时如果是从1到0，那么最靠近1的就是距离最小的，最靠近0的就是距离最大的
     * 这和我们的题意不符，即我们的题意是需要 从 mat 中对应位置元素到最近的 0 的距离
     * 但是我们写代码的时候需要反过来dfs，即每个0到1的距离。这样漫天开花刚好就可以得到
     * 靠近0的是最小距离，靠近1的是最大距离
     */
    /**
     * 注意：在图解中：模拟出了一个超级0，连接着所有的0，然后这所有的0再依次向外连接其他元素【刚好此时就碰到了我们最熟悉的单源bfs】
     *
     * 但在实际的题目中，我们会有不止一个 0。我们会想，要是我们可以把这些 0 看成一个整体好了。
     * 有了这样的想法，我们可以添加一个「超级零」，它与矩阵中所有的 0 相连，这样的话，任意一个 1 到它最近的 0 的距离，会等于这个 1 到「超级零」的距离减去一。
     * 由于我们只有一个「超级零」，我们就以它为起点进行广度优先搜索。
     * 这个「超级零」只和矩阵中的 0 相连，所以在广度优先搜索的第一步中，「超级零」会被弹出队列，而所有的 0 会被加入队列，
     * 注意：超级零」会被弹出队列 这只是我们进行想象出的一个场景，其实是虚构的，但这对解题大有帮助
     * 它们到「超级零」的距离为 1。这就等价于：一开始我们就将所有的 0 加入队列，它们的初始距离为0。
     * 这样以来，在广度优先搜索的过程中，我们每遇到一个 1，就得到了它到「超级零」的距离减去一，也就是 这个 1 到最近的 0 的距离。
     */
    // 利用bfs广度优先遍历，
    // 罗列出 上下左右的单位为1 的位移，在二维数组中【即相当于x-y轴的坐标】
    // private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 此处定义两个一维数组，依次保存着对应上下左右的单位x,y坐标
    // 这样在后面向四周扩展时候，变量看着很醒目
    private int[] dx = new int[] {-1, 1, 0, 0};
    private int[] dy = new int[] {0, 0, -1, 1};

    /**
     *
     * @param mat   注意：二维数组构成的矩阵mat 存储的 每个一维数组 的元素 其实是 对应的 x,y坐标
     *                  但此时 m>x>=0,n>y>=0,为了避免下标越界
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length ;
        int n = mat[0].length ;
        int[][] distance = new int[m][n] ;  // 距离矩阵
        // 定义一个二维数组，来记录是否已经遍历过当前点，避免重复遍历
        boolean[][] isVisited = new boolean[m][n] ; // 默认值为false
        // 此队列中每个 一维数组都是存放着 mat矩阵 中 0 的坐标
        Deque<int[]> queue = new LinkedList<>() ;   // 定义一个双端队列的接口，实现类是双向循环链表

        // 将所有的 0 添加进初始队列中
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(mat[i][j] == 0){
                    // 则将指定元素插入此双端队列表示的队列中
                    queue.offer(new int[]{i,j}) ;   // 此处插入一维数组
                    isVisited[i][j] = true ;    // 依次将每一个0标记为已经访问，因为0就是起始点【多源点】
                }
            }
        }

        // 广度优先遍历 bfs，我要一层一层的剥开你的心
        while(!queue.isEmpty()){
            // 注意：这里没有对矩阵是否已经全部遍历结束进行判断，故这里还会出现一些冗余的代码
            // 即虽然已经全部遍历结束了，但是队列可能还是不为空： !queue.isEmpty()
            // 但如果进行判断了，那么每一次while循环都要经过这个二重for循环，那么代码的执行效率显然就得不偿失了

            // 检索并移除此双端队列表示的队列的头部（换句话说，此双端队列的第一个元素）
            // cell ：代表细胞，一小个蜂巢
            // 其实弹出节点，就是要进行处理
            int[] cell = queue.poll() ;
            // 下面的i 和 j 代表 待处理的 0【刚开始位于最左上角】 对应 的 x,y坐标
            int i = cell[0] ;
            int j = cell[1] ;
            // 这里相当于往四个方向进行广度优先遍历，bfs
            // 注意：这里没有用到递归的思想，即没有栈的思想，因为这是bfs，不是dfs
            // 而且此处的for循环对于，每弹出队列头部的一个节点，只会向外扩展一层
            // 如果此时弹出的节点对应矩阵中的元素是0，那么就相当于还是在第一层中搜索
            // 上面的queue.poll()方法弹出的是队列头部中的非0值对应的点，那么就在遍历第二层
            for(int d = 0 ; d < 4 ; d++){
                // 此处的ni 代表 加上 单位位移 后 的 x坐标
                int ni = i + dx[d] ; //int ni = i + dirs[d][0] ;
                // 此处的nj 代表 加上 单位位移 后 的 y坐标
                int nj = j + dy[d] ; //int nj = j + dirs[d][1] ;
                // 下面的 ni >= 0 && ni < m && nj >= 0 && nj < n 条件，是为了确保边界不会超出
                // !isVisited[ni][nj]条件，是为了确保，还没有遍历到的点可以进行添加，
                // 注意：这里排除了包含0的 多源点 的坐标，故如果刚好遍历到，就不会执行此处的if语句
                if(ni >= 0 && ni < m && nj >= 0 && nj < n && !isVisited[ni][nj]){
                    distance[ni][nj] = distance[i][j] + 1 ; // 将遍历到的点 的 权值 相较于内层的 权值 + 1
                    // 下面这一步是 将 刚才 符合上面的if语句的条件 遍历到的点 添加到 队列的尾部，
                    // 以便于，下一次继续for循环进行bfs 层次搜索，直到在当前0的所有的1都访问完毕后，才退出
                    queue.offer(new int[]{ni,nj}) ; // 将此时符合条件的点 添加到 双端队列的尾部
                    isVisited[ni][nj] = true ;  // 标记为已访问，注意：这里不需要恢复现场，没有利用到回溯
                }
            }
        }

        return distance ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
