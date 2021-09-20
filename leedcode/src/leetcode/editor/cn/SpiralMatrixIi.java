package leetcode.editor.cn;

//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 481 👎 0

public class SpiralMatrixIi{
    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();

        solution.generateMatrix(3) ;
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此题主要是要进行模拟螺旋形的操作，这里始终要遵循循环不变量，即左闭右开原则，
    // 千万不要看条件太多了就 没有遵循 循环中的区间 的不变性原则
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n] ;

        // 定义遍历的初始点
        int startX = 0 ;    // 横坐标
        int startY = 0 ;    // 纵坐标

        // 定义要循环的圈数
        // 每个圈循环几次，例如n为奇数3，那么loop = 1 只是循环一圈，矩阵中间的值需要单独处理
        int loop = n / 2 ;

        // 如果n为奇数，则需要从此处来得到最终的值
        // mid变量是获取n为奇数时的对应的下标

        // 矩阵中间的位置，例如：n为3， 中间的位置就是(1，1)，n为5，中间位置为(2, 2)
        int mid = n / 2 ;

        // 最好在外面定义，不然里面还是在执行过一次后又进行初始化
        // 这里初始化为-1，说明和其他变量无关
        int i,j = -1 ;

        // 这里还需要定义一个位移量，来标明在转过每一圈之前，同时在 左闭右开的情况下，需要限制其搜索范围
        // 即：每一圈循环，需要控制每一条边遍历的长度
        int offset = 1 ;

        // 定义一个全局变量count来记录 依次增加的数值的变量
        int count = 1 ;


        while(loop != 0){

            // 这里的规则，向右搜索添加，之后向下搜索添加，向左搜索添加，向上搜索添加

            // 注意：这里的for循环是共享变量的，所以不要在for循环处定义，可以拉出来
            //for(int i)

            // 这里 的 i和j是互相衔接的，在转角处
//            i = startX ;
//            j = startY ;

            // 向右搜索添加
            // 模拟填充上行从左到右(左闭右开)
            // 注意：当j++后，不符合条件后退出循环，但是此时的j刚好指向拐角处
            for(j = startY ; j < n - offset ; j++){
                matrix[startX][j] = count++ ;
            }


            //向下搜索添加 ,此时j恒不变
            // 模拟填充右列从上到下(左闭右开)
            for(i = startX ; i < n - offset ; i++){
                matrix[i][j] = count++ ;
            }

            // 向左搜索添加 ,此时i恒不变
            // 模拟填充下行从右到左(左闭右开)
            for(; j > startX ; j--){
                matrix[i][j] = count++ ;
            }

            // 向上搜索添加 ,此时j恒不变
            // 模拟填充左列从下到上(左闭右开)
            for(; i > startY ; i--){
                matrix[i][j] = count++ ;
            }

            // 当处理好一层循环后，此时需要重新调整startX和 startY的位置
            // 第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            startX++ ;
            startY++ ;

            // 此时也需要处理偏移量，否则可能会出现问题，即下标越界
            // 为什么需要加1，因为遍历完毕一圈后，之后需要遍历下一圈【向内螺旋】
            // 但我们遍历下一圈的时候，初始位置也需要向前移动，那这需要如何处理，此时就需要更新startX和startY的值
            offset += 1 ;

            loop-- ;    // 将可遍历的圈数减1

        }

        // 当退出循环后需要进行判断是否存在中间位置的元素
        // 如果n为奇数的话，需要单独给矩阵最中间的位置赋值
        if(n % 2 != 0){
            matrix[mid][mid] = count ;
            return matrix ;
        } else {
            return matrix ;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

