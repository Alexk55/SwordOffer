import java.util.*;

/**
 * Created by Alexk on 2017/5/25.
 */
public class MainForth {
    public static void main(String[] args) {
        System.out.println(movingCount(10, 1, 10));
    }

    public static TreeLinkNode structTreeLink(int index) {
        if (index > 7)
            return null;
        TreeLinkNode newLinkNode = new TreeLinkNode(index);
        newLinkNode.left = structTreeLink(2 * index);
        newLinkNode.right = structTreeLink(2 * index + 1);
        return newLinkNode;
    }

    public static void structParentLink(TreeLinkNode root) {
        if (root.left != null) {
            root.left.next = root;
            structParentLink(root.left);
        }
        if (root.right != null) {
            root.right.next = root;
            structParentLink(root.right);
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        HashSet<ListNode> set = new HashSet<>();
        if (pHead.next != null) {
            while (!set.contains(pHead)) {
                set.add(pHead);
                pHead = pHead.next;
            }
            return pHead;
        } else
            return null;
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        HashSet<Integer> set = new HashSet<>();
        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode lastNode = pHead;
        while (pHead != null && (pHead.val != -1)) {
            if (set.contains(pHead.val)) {
                while (pHead.next != null) {
                    if (set.contains(pHead.next.val))
                        pHead = pHead.next;
                    else
                        break;
                }
                ListNode temp = pHead;
                pHead = head;
                while (pHead.next != lastNode)
                    pHead = pHead.next;
                lastNode = pHead;//--------维护上一个节点
                if (temp.next != null) {//删除节点；
                    pHead.next = temp.next;
                    temp.next = null;
                    pHead = pHead.next;
                } else {
                    pHead.next = null;
                    break;
                }
            } else {
                set.add(pHead.val);
                lastNode = pHead;
                if (pHead.next != null)
                    pHead = pHead.next;
                else
                    break;
            }
        }
        if (head.next == null)
            return null;
        else
            return head.next;
    }

    public static TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            if (pNode.next.left == pNode)
                return pNode.next;
            else
                pNode = pNode.next;
        }
        return null;
    }

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return judgeSymmetric(pRoot.left, pRoot.right);
    }

    public boolean judgeSymmetric(TreeNode left, TreeNode right) {
        if (left == null)
            return right == null;
        if (right == null)
            return false;
        if (right.val != left.val)
            return false;
        return judgeSymmetric(left.left, right.right) && judgeSymmetric(left.right, right.left);
    }


    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (pRoot == null)
            return listAll;
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.addLast(null);//每层的分界线
        queue.addLast(pRoot);
        while (queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (node == null) {
                Iterator<TreeNode> iter;
                if (leftToRight) {
                    iter = queue.iterator();
                } else {
                    iter = queue.descendingIterator();
                }
                leftToRight = !leftToRight;
                while (iter.hasNext()) {
                    TreeNode tempNode = iter.next();
                    list.add(tempNode.val);
                }
                listAll.add(new ArrayList<Integer>(list));
                list.clear();
                queue.addLast(null);//添加分界线
                continue;
            }
            if (node.left != null)
                queue.addLast(node.left);
            if (node.right != null)
                queue.addLast(node.right);
        }
        return listAll;
    }

    public static ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (pRoot == null)
            return listAll;
        queue.addLast(null);
        queue.addLast(pRoot);
        while (queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (node == null) {
                Iterator<TreeNode> iter;
                iter = queue.iterator();
                while (iter.hasNext()) {
                    TreeNode nodeTemp = iter.next();
                    list.add(nodeTemp.val);
                }
                listAll.add(new ArrayList<Integer>(list));
                list.clear();
                queue.addLast(null);
                continue;
            }
            if (node.left != null)
                queue.addLast(node.left);
            if (node.right != null)
                queue.addLast(node.right);
        }
        return listAll;
    }//非递归

    public static ArrayList<ArrayList<Integer>> lists = new ArrayList<>();//定义全局变量
    public static ArrayList<Integer> monentList = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        int depthTree = height(pRoot);
        for (int i = 1; i <= depthTree; i++) {
            traversalByLevel(pRoot, i);
            lists.add(new ArrayList<Integer>(monentList));
            monentList.clear();
        }
        return lists;
    }

    public static void traversalByLevel(TreeNode root, int level) {
        if (root == null || level < 1)
            return;
        if (level == 1)
            monentList.add(root.val);
        else {
            traversalByLevel(root.left, level - 1);
            traversalByLevel(root.right, level - 1);
        }
    }

    public static int height(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left) + 1, height(root.right) + 1);
    }

    public static String Serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null)
                buffer.append("#,");
            else {
                buffer.append(String.valueOf(node.val) + ",");
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        int i = buffer.length() - 1;
        while (buffer.charAt(i) == ',' || buffer.charAt(i) == '#')
            --i;
        return (buffer.substring(0, i + 1)).toString();
    }//序列化二叉树

    private static int index = -1;

    public static TreeNode Deserialize(String str) {
        ++index;
        if (str.length() == 0)
            return null;
        TreeNode node = null;
        str.trim();
        String[] arrStr = str.split(",");
        if (index >= arrStr.length)
            return null;
        if (!arrStr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(arrStr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }//反序列化二叉树

    public static ArrayList<TreeNode> treeNodeList = new ArrayList<>();

    public static TreeNode KthNode(TreeNode pRoot, int k) {
        if (k == 0)
            return null;
        traversalMidTree(pRoot);
        if (treeNodeList.size() >= k)
            return treeNodeList.get(k - 1);
        else
            return null;
    }

    public static void traversalMidTree(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            traversalMidTree(root.left);
        treeNodeList.add(root);
        if (root.right != null)
            traversalMidTree(root.right);
    }

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> windows = new LinkedList<>();
        if (num.length == 0 || size == 0 || num.length < size)
            return list;
        int i = 0;
        while (i < num.length) {
            if (windows.size() < size) {
                windows.add(num[i]);
                ++i;
            } else {
                int maxElement = Collections.max(windows);
                list.add(maxElement);
                windows.removeFirst();
                windows.add(num[i]);
                ++i;
            }
        }
        list.add(Collections.max(windows));
        return list;
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length < rows * cols || matrix.length == 0 || matrix.length < str.length)
            return false;
        boolean[] judgePath = new boolean[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findPathFrom(matrix, rows, cols, i, j, 0, str, judgePath))
                    return true;
            }
        }
        return false;
    }

    public static boolean findPathFrom(char[] matrix, int rows, int cols, int r, int c, int index, char[] str, boolean[] judgePath) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r * cols + c] != str[index] || judgePath[r * cols + c])
            return false;
        if (index == str.length - 1)
            return true;
        judgePath[r * cols + c] = true;
        if (findPathFrom(matrix, rows, cols, r - 1, c, index + 1, str, judgePath)
                || findPathFrom(matrix, rows, cols, r + 1, c, index + 1, str, judgePath)
                || findPathFrom(matrix, rows, cols, r, c - 1, index + 1, str, judgePath)
                || findPathFrom(matrix, rows, cols, r, c + 1, index + 1, str, judgePath))
            return true;
        judgePath[r * cols + c] = false;
        return false;
    }

    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0)
            return 0;
        boolean[] findPath = new boolean[rows * cols];
        return countSquare(threshold, rows, cols, 0, 0, findPath);
    }

    public static int countSquare(int threshold, int rows, int cols, int r, int c, boolean[] findPath) {
        int count = 0;
        if (checkSquare(threshold, rows, cols, r, c, findPath)) {
            findPath[r*cols+c]=true;
            count = 1 + countSquare(threshold, rows, cols, r - 1, c, findPath)
                    + countSquare(threshold, rows, cols, r + 1, c, findPath)
                    + countSquare(threshold, rows, cols, r, c - 1, findPath)
                    + countSquare(threshold, rows, cols, r, c + 1, findPath);
        }
        return count;
    }

    public static boolean checkSquare(int threshold, int rows, int cols, int r, int c, boolean[] findPath) {
        if (r >= 0 && r < rows && c >= 0 && c < cols && !findPath[r * cols + c] && (getNum(r) + getNum(c)) <= threshold)
            return true;
        return false;
    }

    public static int getNum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
