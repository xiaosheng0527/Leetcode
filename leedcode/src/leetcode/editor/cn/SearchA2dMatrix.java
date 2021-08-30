package leetcode.editor.cn;

//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 矩阵 
// 👍 479 👎 0

public class SearchA2dMatrix{
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrix().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 此题需要用到两次二分查找法
    // 第一次是获取target所在范围的行，第二次是获取target在此行中是否存在
    public boolean searchMatrix(int[][] matrix, int target) {

        int rowPos = rowSearch(matrix,target) ;
        if(rowPos == -1){
            return false ;
        }

        return binarySearch(matrix,target,rowPos) ;
    }

    public int rowSearch(int[][] matrix,int target){

        int m = matrix.length ;
        int low = - 1;  // 初始化为-1，为了判定是否找到对应大于当前行首元素且小于下一行的行首元素的下标
        int high = m - 1 ;
        int mid = 0 ;

        // 注意：这里定义为low < high,是为了避免死循环
        while(low < high){
            mid = low + (high - low + 1) / 2 ;  // 向上取整
            // 这里使用的方法类似简单插入排序中，如何寻找待插入位置的方法类似
            if(target < matrix[mid][0]){
                high = mid - 1 ;
            }else{  // target >= matrix[mid][0]
                // 下一次搜索的区间为 [mid,right],因为mid是向上取整，所以不会死循环
                // 死循环是发生在只剩下一个元素或两个元素的情况下。此时无论如何，mid恒不变
                low = mid ; // 注意：这样写要记得，上面的mid变量需要向上取整，以防死循环
            }
        }

        return low ;

        // 当 low ==  high 时，退出循环，得到
    }

    public boolean binarySearch(int[][] matrix,int target,int rowPos){

        int[] temp = matrix[rowPos] ;
        int len = temp.length ;
        int low = 0 ;
        int high = len - 1 ;
        int mid = 0 ;
        while(low <= high){
            mid = low + (high - low) / 2 ;
            if(target == temp[mid]){
                return true ;
            }else if(target > temp[mid]){
                low = mid + 1 ;
            }else {
                high = mid - 1 ;
            }
        }
        return false ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

