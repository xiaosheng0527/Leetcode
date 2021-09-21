package leetcode.editor.cn;

//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
///引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。 
//
// 在链表类中实现这些功能： 
//
// 
// get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。 
// addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。 
// addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。 
// addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
//到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。 
// deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。 
// 
//
// 
//
// 示例： 
//
// MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
// 
//
// 
//
// 提示： 
//
// 
// 所有val值都在 [1, 1000] 之内。 
// 操作次数将在 [1, 1000] 之内。 
// 请不要使用内置的 LinkedList 库。 
// 
// Related Topics 设计 链表 
// 👍 289 👎 0

public class DesignLinkedList{
    public static void main(String[] args) {
        //Solution solution = new DesignLinkedList().new Solution();
        //ListNode node1 = new ListNode() ;
    }

//leetcode submit region begin(Prohibit modification and deletion)

class ListNode {

    int val ;
    ListNode next ;
    ListNode prev ;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next, ListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}


class MyLinkedList {

    // size 存储链表元素的个数
    int size ;

    // Sentinel node：哨兵节点
    ListNode head ;     // 一直指向链表头部
    ListNode tail ;     // 一直指向链表尾部

    /** Initialize your data structure here. */
    //初始化链表
    public MyLinkedList() {

        size = 0 ;
        // 所有val值都在 [1, 1000] 之内
        // 注意：这里的head和tail节点其实也是虚拟节点
        head = new ListNode(0) ;    // 虚拟头结点
        tail = new ListNode(0) ;    // 虚拟尾结点
        // 刚开始头结点指向尾结点
        head.next = tail ;
        tail.prev = head ;
    }

    /**
     *      如果index指的是：是待插入的位置，则是说要遍历到index的位置的前面 即遍历范围[0,index-1），因为要遍历到待插入节点的前一个节点
     *           【即加上dummy后的 index 位置 的 前面 ，但是我们需要通过多一次遍历来控制范围，即遍历范围[0,index)】
     *
     *      如果index指的是：是待删除的位置，则是说要遍历到index的位置的前面 即遍历范围[0,index-1），因为要遍历到待删除节点的前一个节点
     *          【即加上dummy后的 index 位置 的 前面 ，但是我们需要通过多一次遍历来控制范围，即遍历范围[0,index)】
     *
     *      // 你可以观察一下，之前，我描述的过程中，加上 dummy 后 写的是 (index+1) 的前面，这种理解思路已然是错了，因为 我们要保持和题目描述的信息的一致性。
     *      综上：加上 dummy 后 写的还应该是 index 的前面
     *
     *      index 存在两种含义：【通性：插入和删除的 位置可以从0开始计数，即可以在第0个位置前插入节点和删除第0个位置的节点】
     *      但是这里出现的一个情况是，我们定义了一个虚拟头结点dummy，此时操作就变的复杂了
     *      如下考虑插入到节点的头部，这种最特殊
     *
     *      你要理解的一个层面，要区分考虑index ？？
     *      a)一个是题目中没有虚拟头结点dummy，此时需要插入的位置或者是需要删除的位置 所对应的 index
     *      b) 还有一个是我们根据自己的代码编写带有虚拟头结点的代码，此时需要插入到index位置的前面或者是需要删除的index位置
     *      c) 对于添加了 虚拟头结点后，那么 index = 0 到底是指向 虚拟头结点 还是 指定第一个有效节点
     *
     *      我们始终不清楚 a) 和 b) 和 c) 的 index 表达的含义 是否相同
     *      上面为什么一直弄混了，是因为 我没有理解这个层面的含义。
     *
     *      你可以根据上面的解释：其实 要插入的index位置前 在 a) 和 b) 对应的 表示的都是同一种意思
     *      然后根据 a) 和 b) 表达相同的意思，推出 c) 中的 index = 0 是始终指向 的是 第一个有效节点
     *      只是需要控制遍历的范围 稍微变化了
     *
     *      剖析：
     *      1）实际上 a) 和 b) 讲的 index 说的是 同一个index ，但是区别在于遍历过程中的遍历的次数
     *          而且 你要明白一点：index变量 是相对 还没有 改变链表结构 的 时候的 需要插入 或者 删除 的位置，如果是想插入到链表首部，
     *          那么 原来链表的 首部的 节点 是  index = 0 ，那么我们肯定也只能插入到 原链表 第0个位置 的前面 ，然后将其充当 新链表的 index = 0
     *          如果是 index = 1 ， 说明 相对还没有改变 链表结构 的 时候 需要插入 或者 删除 的位置，这里是 要插入到原链表的第1个位置之前，之后将其充当 新链表的 index = 1
     *
     *          所以，index = 0 表示插入到第0个位置前或删除第0个位置的节点，且index 表示的始终是 有效节点的索引， 即存放实际数据的节点
     *
     *      2）那么我们要怎么区分，其实你有没有注意到，相对于原来链表的 index 位置，具体的操作还是通过 cur 指针来操作，所以我们只需要控制好遍历到的位置是正确的，那么一定就可以添加或者删除
     */

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    //获取第index个节点的数值
    public int get(int index) { // index代表索引值

        // 如果index非法，返回-1
        if(index < 0 || index >= size){
            return -1 ;
        }

        ListNode cur = head ;   // head是虚拟头结点

        // 通过判断 index < (size - 1) / 2 来决定是从头结点还是尾节点遍历，提高效率
        // (size - 1) ：获取最后一个链表所在的索引值，此时可以利用取中间索引 来考虑
        //              是从头结点还是尾节点遍历，提高效率
        // 其实这个表达式和二分查找法获取中间索引mid的方法一致。
        if(index < (0 + (size - 1)) / 2){

            for(int i = 0 ; i < index + 1 ; i++){   // 这边的遍历右考虑到虚拟头结点的情况
                cur = cur.next ;
            }
        } else {
            cur = tail ;
            // 注意：如果此时的size = 10，则index 索引值 最大就是 9
            // 且 cur指针刚开始指向的是 虚拟尾结点，所以这里需要多遍历一次
            for(int i = 0 ; i < size - index ; i++){    // 这里面具体范围要加1还是减1，自己可以带个值验证
                cur = cur.prev ;
            }
        }
        return cur.val ;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    //在链表最前面插入一个节点
    public void addAtHead(int val) {

        ListNode cur = head ;
        ListNode newNode = new ListNode(val) ;
        // 双向链表添加元素也要注意一点：要先考虑要添加节点和其后面的节点的连通性，
        // 如果顺序搞反了，可能会出现覆盖现象。
        // 而要添加节点 和 其 前面 节点的连通，则顺序

        // 注意：可以存在多个引用指向同一个地址，
        // 但是如果一个引用指向不同的地址空间，则以最后指向的地址空间为主
        newNode.next = cur.next ;
        cur.next.prev = newNode ;

        // 下面这两步骤没有明显的依赖关系，故可以调换位置
        cur.next = newNode ;
        newNode.prev = cur ;
        size++ ;
    }

    /** Append a node of value val to the last element of the linked list. */
    //在链表的最后插入一个节点
    public void addAtTail(int val) {

        ListNode cur = tail ;
        ListNode newNode = new ListNode(val) ;
        // 注意：tail节点在链表的最尾部，此时cur在要添加节点的后面，
        // 此时添加的方式要和cur在要添加节点的前面 有所区别，
        // 但你要记住一点，我们创建的newNode可以先指向前后两个节点，而且不会造成影响，
        // 可以存在多个引用指向同一个地址，故newNode的next和prev指向的地址空间是允许的。
        newNode.next = tail ;
        newNode.prev = cur.prev ;
        // 之后也需要按照 先连接 要添加节点和后面的cur， 之后 再链接 要添加节点 和 前面的 节点
        // 注意：这两部的顺序也不能调换，否则也会出现 覆盖的现象，导致之前的链接 失效
        // 为什么不能调换顺序呢？？？
        // 注意一下：cur.prev.next 和 cur.prev 如果调换顺序，则 cur.prev先执行，即已经修改了所指向的地址空间，
        // 但是 cur.prev.next的本意是 并不修改cur指向的地址空间，而是 要修改 cur.prev所指向的地址空间，
        //  这两者 的含义是不同的，所以这里千万不能调换位置，否则此链表就会 断链。
        cur.prev.next = newNode ;   // 注意：cur在要添加节点的后面
        cur.prev = newNode ;
        size++ ;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    // index 代表 待插入节点的 位置
    public void addAtIndex(int index, int val) {

        if(index > size){
            return ;
        }
        if(index < 0){
            index = 0 ;
        }


        // 注意：双向链表 也需要 找待添加节点的前一个节点，不论其是否存在prev域
        // 因为添加节点是需要在index的位置添加，所以我们必须知道其前面的位置
        ListNode cur = head ;
        // 这个for循环有点疑惑，明天再看看
        // 注意：这个for循环只移动到待添加节点的前一个位置
        // 因为我们不清楚是
        for(int i = 0 ; i < index ; i++){   // 这里的区间的长度为index，我们这里应该考虑了虚拟头结点
            cur = cur.next ;
        }

        // 当退出循环时，cur指向的是待添加元素的位置

        ListNode newNode = new ListNode(val) ;
        newNode.next = cur.next ;
        cur.next.prev = newNode;
        newNode.prev = cur;
        cur.next = newNode ;
        size++ ;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    //删除第index个节点，注意：index默认从0开始
    public void deleteAtIndex(int index) {

        if(index < 0 || index >= size){
            return ;
        }

        ListNode cur = head ;
        // 要找到要删除节点的前一个节点，这里的遍历包括虚拟头结点
        // 这个for循环有点疑惑，明天再看看
        // 这是因为我们以为是直接遍历到待删除节点的位置，然后因为其有prev和next域，所以删除过程中无需pre节点
        // 但是我们可以看看，直接删除好像和遍历到前一个节点的时间复杂度和空间复杂度差别不大，那这里为什么我还要搞特殊
        // 直接遍历到待删除节点的前一个节点不就行了
        for(int i = 0 ; i < index ; i++){
            cur = cur.next ;
        }
        cur.next.next.prev = cur;
        cur.next = cur.next.next ;
        size-- ;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

