import java.util.*;

public class Main {
    /*public static void main(String[] args) {
        *//*TreeNode root = structTree(1);
        ArrayList<Integer> list = PrintFromTopToBottom(null);
        for(int i:list)
        {
            System.out.print(" "+i);
        }*//*
        *//*ListNode head1 = new ListNode(1);
        for (int j = 4; j > 0; j--) {
            ListNode temp = new ListNode(j + 1);
            temp.next = head1.next;
            head1.next = temp;
        }
        while (head1 != null) {
            System.out.println(head1.val);
            head1 = head1.next;
        }*//*
        int a[] = {7,4,6,5};
        boolean b = VerifySquenceOfBST(a);
        System.out.println(b);
    }*/

    public static TreeNode structTree(int index) {
        if (index >15)
            return null;
        TreeNode root = new TreeNode(index);
        root.left = structTree(2*index);
        root.right = structTree(2*index+1);
        return root;
    }//完全二叉树的测试用例

    public static boolean find(int target, int[][] array) {
        int i = array.length - 1, j;
        while (i >= 0) {
            j = 0;
            while (j < array[i].length) {
                if (target == array[i][j])
                    return true;
                else if (target > array[i][j])
                    j++;
                else
                    break;
            }
            i--;
        }
        return false;
    }//剑指offer第一题

    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll("\\s", "\\%20");
    }//剑指offer第二题

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> s = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            s.push(listNode.val);
            listNode = listNode.next;
        }
        while (!s.isEmpty()) {
            list.add(s.pop());
        }
        return list;
    }//剑指offer第三题

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null && in == null)
            return null;
        TreeNode headNode = new TreeNode(pre[0]);
        int i, length;
        length = in.length;
        for (i = 0; i < length; i++) {
            if (pre[0] == in[i])
                break;
        }
        int lPre[] = Arrays.copyOfRange(pre, 1, i + 1);
        int lIn[] = Arrays.copyOfRange(in, 0, i);
        int rPre[] = Arrays.copyOfRange(pre, i + 1, length);
        int rIn[] = Arrays.copyOfRange(in, i + 1, length);
        if (lPre.length >= 1 && lIn.length >= 1)//左子树
            headNode.left = reConstructBinaryTree(lPre, lIn);
        else
            headNode.left = null;
        if (rPre.length >= 1 && rIn.length >= 1)//右子树
            headNode.right = reConstructBinaryTree(rPre, rIn);
        else
            headNode.right = null;
        return headNode;
    }//剑指offer第四题

    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0)
            return 0;
        int indexOne = 0;
        int indexTwo = array.length - 1;
        int mid = indexTwo;
        while (array[indexOne] >= array[indexTwo]) {
            mid = (indexOne + indexTwo) / 2;
            if (indexTwo - indexOne == 1) {
                mid = indexTwo;
                break;
            }
            if (array[mid] <= array[indexTwo])//最小值在前半部分
                indexTwo = mid;
            if (array[mid] >= array[indexOne])//最小值在后半部分
                indexOne = mid;
            if (array[indexOne] == array[indexTwo] && array[indexOne] == array[mid])
                return AuxiliaryMothod.minArrIntElement(array);
        }
        return array[mid];
    }//剑指offer第六题

    public static int Fibonacci(int n) {
        if (n == 0)
            return 0;
        int fibOne = 0;
        int fibTwo = 1;
        int temp;
        for (int i = 1; i < n; i++) {
            temp = fibOne;
            fibOne = fibTwo;
            fibTwo = temp + fibOne;
        }
        return fibTwo;
    }//第七题

    public int JumpFloor(int target) {
        if (target == 0)
            return 0;
        int jumpOne = 1;
        int jumpTwo = 1;
        int temp;
        for (int i = 1; i < target; i++) {
            temp = jumpOne;
            jumpOne = jumpTwo;
            jumpTwo = jumpOne + temp;
        }
        return jumpTwo;
    }//第八题

    public int JumpFloorII(int target) {
        if (target == 1)
            return 1;
        int jumpCountTatal = 0;
        for (int i = target - 1; i > 0; i--) {
            jumpCountTatal += JumpFloorII(i);
        }
        return jumpCountTatal + 1;
    }//第九题

    public static int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }//第十题

    public static double Power(double base, int exponent) {
        boolean negetive = false;//指数为负数的情况
        if (base > -0.00000001 && base < 0.00000001)//底数为0的情况
            return 0.0;
        if (exponent < 0) {
            negetive = true;
            exponent = -exponent;
        }
        if (exponent == 0)
            return 1.0;
        if (exponent == 1)
            return base;
        double result = Power(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1)
            result *= base;
        if (negetive)
            return 1.0 / result;
        return result;
    }//十一题

    public static void reOrderArray(int[] array) {
        int[] newArray = new int[array.length];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 0x1) == 1)
                newArray[k++] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 0x1) == 0)
                newArray[k++] = array[i];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }//十二题

    public static ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0)
            return null;
        ListNode p = head;
        int i = 0;
        while (i < k - 1) {
            if (p.next == null)
                return null;
            p = p.next;
            i++;
        }
        ListNode q = head;
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        return q;
    }//十三题

    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return null;
        else if (head.next == null) {
            return head;
        } else {
            ListNode mark = head;
            ListNode pCur = mark;
            ListNode pNext = pCur.next;
            while (pNext != null) {
                pCur.next = pNext.next;
                pNext.next = mark;
                mark = pNext;
                pNext = pCur.next;
            }
            return mark;
        }

    }//十四题

    public static ListDouDerNode ReverseDoubleDerictionList(ListDouDerNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListDouDerNode mark = head;
        ListDouDerNode pCur = mark;
        ListDouDerNode pNext = mark.next;
        while (pNext != null) {
            pCur.next = pNext.next;
            if (pNext.next != null)
                pNext.next.pre = pCur;
            pNext.pre = null;
            pNext.next = mark;
            mark.pre = pNext;
            mark = pNext;
            pNext = pCur.next;
        }
        return mark;
    }//双链表反转

    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode mark;
        ListNode newhead;
        ListNode temp;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val <= list2.val) {
            newhead = list1;
            list1 = list1.next;
        } else {
            newhead = list2;
            list2 = list2.next;
        }
        mark = newhead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                mark.next = list1;
                mark = mark.next;
                list1 = list1.next;
            } else {
                mark.next = list2;
                mark = mark.next;
                list2 = list2.next;
            }
        }
        mark.next = list1 == null ? list2 : list1;
        return newhead;
    }//十五题非递归

    /*public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;
        ListNode newHead;
        if (list1.val <= list2.val) {
            newHead = list1;
            newHead.next = Merge(list1.next, list2);
        } else {
            newHead = list2;
            newHead.next = Merge(list1, list2.next);
        }
        return newHead;
    }//十五题
*/
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 == null || root2 == null)
            return false;
        else {
            if (root1.val == root2.val) {
                result = AuxiliaryMothod.doesTheSameTree(root1, root2);
            }
            if (!result)
                result = HasSubtree(root1.left, root2);
            if (!result)
                result = HasSubtree(root1.right, root2);
        }
        return result;
    }//第十六题

    public void Mirror(TreeNode root) {
        /*if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;
        TreeNode temp;
        temp = root.left;
        root.right = root.left;
        root.left = temp;
        if (root.left != null)//去掉这行就ac
            Mirror(root.left);
        if (root.right != null)//去掉这行就ac
            Mirror(root.right);//递归求解*/
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null || node.left != null) {

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);//用栈的非递归
        }
    }//十七题

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix.length == 0)
            return list;
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++)
                list.add(matrix[rowBegin][i]);
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd; i++)
                list.add(matrix[i][colEnd]);
            colEnd--;
            if (rowEnd >= rowBegin) {
                for (int i = colEnd; i >= colBegin; i--)
                    list.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (colEnd >= colBegin) {
                for (int i = rowEnd; i >= rowBegin; i--)
                    list.add(matrix[i][colBegin]);
            }
            colBegin++;
        }
        return list;
    }//十八题

    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        if (popA.length != pushA.length || popA.length == 0 || pushA.length == 0)
            return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty() ? true : false;

    }// 二十题

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root==null)
            return list;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
        return list;
    }//二十一题

    public static boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length<1)
            return false;
        int rootValue = sequence[sequence.length-1];
        int i=0,j;
        boolean lSeq=true,rSeq=true;
        while(sequence[i]<rootValue)
            i++;
        j=i;
        while(j<sequence.length-1)//要把root节点排除
        {
            if(sequence[j]<rootValue)
                return false;
            j++;
        }
        int lArrSqe[] = Arrays.copyOfRange(sequence,0,i);
        int rArrSqe[] = Arrays.copyOfRange(sequence,i,sequence.length-1);//要把root节点排除
        if(lArrSqe.length!=0)
            lSeq=VerifySquenceOfBST(lArrSqe);
        if(rArrSqe.length!=0)
            rSeq = VerifySquenceOfBST(rArrSqe);
        return lSeq&&rSeq;
    }//二十二题
}
