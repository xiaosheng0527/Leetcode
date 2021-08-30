package leetcode.editor.cn;

//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 
// 👍 650 👎 0

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combinations{
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        solution.combine(4, 2) ;
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    注意：这里要使用布尔数组是因为，我们是使用的for循环，从i=0开始遍历，那么回溯过程中可能会重复访问之前访问的元素，就会造成影响
//    而组合的题目中不使用布尔数组是因为，因为i并不是从0开始，所以恢复现场只需要删除此元素，然后回溯

    // 组合：解题方法：回溯 + 剪枝

    /**
     *
     * @param n     代表有多少个数       1 <= n <= 20
     * @param k     代表由k个数字进行组合 1 <= k <= n
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>() ;
        if(k < 0 || k > n){
            return res ;
        }
        // 从1开始是题目的设定
        //叶子结点的信息体现在从根结点到叶子结点的路径上，因此需要一个表示路径的变量 path，
        // 它是一个列表，特别地，path 是一个栈；这里使用双端队列的实现类，数组双端队列，效率高，最好初始化容量
        Deque<Integer> path = new ArrayDeque<>(k) ;
        dfs(n, k, 1, path, res) ;
        return res ;

    }

    /**
     *
     * @param n     代表有多少个数       1 <= n <= 20
     * @param k     代表由k个数字进行组合 1 <= k <= n
     * @param begin 每一个结点递归地在做同样的事情，区别在于搜索起点；故begin变量代表在区间 [begin, n] 里选出若干个数的组合；这里的begin用来确定初始值的区间
     * @param path  代表表示路径的变量，它是一个列表，特别地，path 是一个栈；这里使用ArrayDeque更高效
     * @param res
     */
    // 这里不需要定义布尔数组来标记是否已经访问，与之代替的是删除前面添加到path集合中的节点；然后回溯到下一个节点
    // 但像八皇后问题，我们不能改变原来的棋盘，即此时临时定义一个布尔数组来记录是否已经访问。
    // 注意：共同点在于：它们都是为了在一条道走到黑后，如果不符，则进行回溯，恢复原来的现场。【只是采取的手段不同】
    public void dfs(int n,int k , int begin,Deque<Integer> path,List<List<Integer>> res){
        // 递归终止的条件是：path的长度等于 k
        if(path.size() == k){
            // 注意：ArrayDeque没有实现List接口,故原来 是不能进行转化的，但下面是通过迭代器进行的
            // 按照集合的迭代器返回的顺序构造一个包含指定集合元素的列表。
            res.add(new ArrayList<>(path)) ;
            return ;
        }

        // 遍历所有可能的搜索起点
        // 这里的begin是在变化的
        // 注意：int i = begin;是在每个递归栈中只定义一次，之后的for循环就和其无关了
        // 如果下面写成begin + 1，那么说明有关系，但实际上有关系的是i，即需要i + 1
        for(int i = begin ; i <= n ; i++){
            // 向路径向量里添加一个数
            path.offer(i); // 压栈
            // 这里千万不要给整懵逼了，这是dfs的特点，一条道走到黑，再进行回溯
            // dfs(n, k, begin + 1, path, res); 这里深度探索要写成i+1，不能写成begin+1，
            // 因为在此递归栈中begin是固定值，如果在回溯后eg：要找[1,3],此时begin
            // dfs()调用过程中：begin是在变化的，但是在当前递归栈中是固定的begin变量
            // 而且：此时我们只是需要组合情况，并不是排列情况，
            // begin+1会存在冗余的结果,即如下的结果,而且还出现了重复的元素，说明搜索起点设置了错误
            // 尽管刚开始没有发现什么错误，但到后几个数据的时候，尤其是在回溯中，才会暴露出代码逻辑的错误
            // [[1,2],[1,3],[1,4],[2,2],[2,3],[2,4],[3,2],[3,3],[3,4],[4,2],[4,3],[4,4]]
            // debug一下
            // dfs(n, k, begin + 1, path, res);
            // 但我们需要的只是组合的结果，不考虑顺序,即如下的结果
            // [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]

            // 下一轮搜索，，设置的搜索起点要加 1 ，因为组合数字中不允许出现重复的元素
            dfs(n, k, i + 1, path, res);

            // 相当于恢复现场，等待下一个遍历点
            // 重点理解这里：dfs有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.pollLast() ;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

