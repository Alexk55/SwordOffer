import java.math.BigInteger;
import java.util.*;

/**
 * Created by Alexk on 2017/5/11.
 */
public class MainThird {
    /*public static void main(String[] args) {
        String str = "googlae";
        System.out.println(FirstNotRepeatingChar(str));

    }*/

    public static int GetUglyNumber_Solution(int index) {
        if (index < 7)
            return index;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int x1 = 0, x2 = 0, x3 = 0;
        int i;
        for (i = 1; i < index; ++i) {
            list.add(Math.min(list.get(x1) * 2, Math.min(list.get(x2) * 3, list.get(x3) * 5)));
            if (list.get(x1) * 2 == list.get(i)) x1++;
            if (list.get(x2) * 3 == list.get(i)) x2++;
            if (list.get(x3) * 5 == list.get(i)) x3++;
        }
        return list.get(i - 1);
    }//丑数

    public static int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0)
            return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int time = map.get(str.charAt(i));
                map.remove(str.charAt(i));
                map.put(str.charAt(i), ++time);
            } else
                map.put(str.charAt(i), 1);
        }
        for (int i = 0; i < str.length(); i++)
            if (map.get(str.charAt(i)) == 1)
                return i;
        return -1;
    }//三十三题

    private static int InverseParisCore(int[] array, int low, int high) {
        int count = 0;
        int mid = (low + high) / 2;
        if (low < high) {
            count += InverseParisCore(array, low, mid);
            count += InverseParisCore(array, mid + 1, high);
            count += merge(array, low, mid, high);
        }
        if (count > 1000000007)
            count %= 1000000007;
        return count;
    }

    private static int merge(int[] array, int low, int mid, int high) {
        int count = 0;
        int[] temp = new int[high - low + 1];
        int i = temp.length - 1;
        int one = mid;
        int second = high;
        while (one >= low && second > mid) {
            if (array[one] > array[second]) {
                count += second - mid;
                if (count > 1000000007)
                    count %= 1000000007;
                temp[i--] = array[one--];
            } else
                temp[i--] = array[second--];
        }
        while (one >= low)
            temp[i--] = array[one--];
        while ((second > mid))
            temp[i--] = array[second--];
        second = high;
        for (int j = temp.length - 1; j >= 0; j--)
            array[second--] = temp[j];
        return count % 1000000007;
    }

    public static int InversePairs(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        return InverseParisCore(array, 0, array.length - 1);
    }//逆序对

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) ;
        {
            p1 = (p1 != null ? p1.next : pHead2);
            p2 = (p2 != null ? p2.next : pHead1);
        }
        return p1;
    }//两个链表求第一个公共节点

    public static int GetNumberOfK(int[] array, int k) {
        int first = FirstK(array, k, 0, array.length - 1);
        int last = LastK(array, k, 0, array.length - 1);
        if (last != -1 && first != -1)
            return last - first + 1;
        else
            return 0;
    }//二分查找

    private static int FirstK(int[] array, int k, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) >> 1;
        int temp = array[mid];
        if (temp > k)
            return FirstK(array, k, start, mid - 1);
        else if (temp < k)
            return FirstK(array, k, mid + 1, end);
        else if ((mid - 1) >= 0 && array[mid - 1] == k)
            return FirstK(array, k, start, mid - 1);
        else
            return mid;
    }//二分查找递归

    private static int LastK(int[] array, int k, int start, int end) {
        int mid = (start + end) >> 1;
        while (start <= end) {
            if (array[mid] > k)
                end = mid - 1;
            else if (array[mid] < k)
                start = mid + 1;
            else if ((mid + 1) < array.length && array[mid + 1] == k)
                start = mid + 1;
            else
                return mid;
            mid = (start + end) >> 1;
        }
        return -1;
    }//二分查找非递归

    public static int TreeDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null)
            return 0;
        queue.add(root);
        int count = 0, nextcount = 1, depth = 0;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            count++;
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
            if (count == nextcount) {
                depth++;
                nextcount = queue.size();
                count = 0;
            }
        }
        return depth;
    }//非递归层序遍历

    private static boolean IsBalanced = true;

    public static boolean IsBinarySeach_Solution(TreeNode root) {
        boolean right, left;
        if (root == null)
            return false;
        if (root.left != null) {
            if (root.left.val > root.val)
                return false;
            else
                left = IsBinarySeach_Solution(root.left);
        } else
            return true;
        if (root.right != null) {
            if (root.right.val < root.val)
                return false;
            else
                right = IsBinarySeach_Solution(root.right);
        } else
            return true;
        return left && right;
    }//判断是否为二叉搜索树

    public static int IsBalancedSecach_Solution(TreeNode root) {
        if (root == null)
            return 0;
        int left = IsBalancedSecach_Solution(root.left);
        int right = IsBalancedSecach_Solution(root.right);
        if (Math.abs(left - right) > 1)
            IsBalanced = false;
        return right > left ? right + 1 : left + 1;
    }//寻找公共祖先节点比较平衡因子是否大于1

    public static boolean IsBalanced_Solution(TreeNode root) {
        IsBalancedSecach_Solution(root);
        return IsBalanced && IsBinarySeach_Solution(root);
    }

    public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            temp ^= array[i];
        }
        int count = 0;
        while (temp != 0) {
            temp = temp >> 1;
            count++;
        }
        temp = (int) Math.pow(2, count - 1);
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & temp) == 0)
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }
    }

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
        int middle = sum / 2 + 1;
        int small = 1, big = 2, curSum = 3;
        while (small < middle) {
            while (curSum < sum) {
                ++big;
                curSum += big;
            }
            if (curSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = small; i <= big; i++)
                    list.add(i);
                listAll.add(list);
                ++big;
                curSum += big;
            } else {
                curSum -= small;
                small++;
            }
        }
        return listAll;
    }//连续s数的和

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array.length == 0 || array == null)
            return list;
        int small = 0;
        int big = array.length - 1;
        int curSum = array[small] + array[big];
        while (small < big && curSum != sum) {
            if (curSum > sum) {
                curSum -= array[big];
                curSum += array[--big];
            } else {
                curSum -= array[small];
                curSum += array[++small];
            }
        }
        if (curSum == sum) {
            list.add(array[small]);
            list.add(array[big]);
        }
        return list;
    }

    public String LeftRotateString(String str, int n) {
        if (str == null)
            return "";
        if (n == 0)
            return str;
        if (n > str.length())
            n = n % str.length();
        StringBuffer stringBuffer = new StringBuffer(str.substring(n - 1));
        stringBuffer.append(str.substring(0, n));
        return stringBuffer.toString();
    }

    public static String ReverseSentence(String str) {
        if (str.length() == 0 || str == null)
            return "";
        if (str.trim().equals(""))
            return str;
        String[] arrStr = str.split(" ");
        StringBuffer stringBuffer = new StringBuffer(arrStr[arrStr.length - 1]);
        if (arrStr.length > 1) {
            for (int i = arrStr.length - 2; i >= 0; --i)
                stringBuffer.append(" " + arrStr[i]);
        }
        return stringBuffer.toString();
    }

    public static boolean isContinuous(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        if (numbers.length <= 0 || numbers.length > 5)
            return false;
        int min = 14;
        int max = -1;
        int count = 0;
        for (int i = 0; i < 5; i++) {
            int num = numbers[i];
            if (num == 0) {
                count++;
                continue;
            } else {
                if (!set.contains(num)) {
                    set.add(num);
                    if (min > num)
                        min = num;
                    if (max < num)
                        max = num;
                } else
                    return false;
            }
        }
        if (count > 4)
            return false;
        if ((max - min) < 5)
            return true;
        else
            return false;
    }

    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0)
            return -1;
        int result = 0;
        for (int i = 2; i <= n; ++i) {
            result = (result + m) % i;
        }
        return result;
    }//约瑟夫问题

    public int Sum_Solution(int n) {
        int result = n;
        boolean ans = (n > 0) && ((result += Sum_Solution(--n)) > 0);
        return result;
    }

    public static int Add(int num1, int num2) {
        BigInteger a = new BigInteger(String.valueOf(num1));
        BigInteger b = new BigInteger(String.valueOf(num2));
        BigInteger sum = a.add(b);
        String str = sum.toString();
        int result = Integer.valueOf(str);
        return result;
    }//没做出来

    public static int StrToInt(String str) {
        if (str.length() == 0)
            return 0;
        int mark = 0;
        if (str.length() > 1) {
            if (str.charAt(0) == '+')
                str = str.substring(1);
            if ((int) str.charAt(0) == '-') {
                str = str.substring(1);
                mark = 1;
            }
        }
        int result = 0, temp;
        for (int i = 0; i < str.length(); i++) {
            temp = (int) (str.charAt(i) - '0');
            if (temp < 0 || temp > 9) {
                result = 0;
                break;
            } else {
                result = 10 * result + temp;
            }
        }
        if (mark == 1)
            return -result;
        return result;
    }

    public boolean duplicate(int numbers[], int length, int[] duplication) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (!set.contains(numbers[i]))
                set.add(numbers[i]);
            else {
                duplication[0] = numbers[i];
                return true;
            }
        }
        duplication[0] = -1;
        return false;
    }

    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        Arrays.fill(B, 1);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (j != i) {
                    B[i] *= A[j];
                } else
                    continue;
            }

        }
        return B;
    }

    public static boolean match(char[] str, char[] pattern) {
        return new String(str).matches(new String(pattern));
    }//字符串匹配

    public static boolean isNumeric(char[] str) {
        String newStr = String.valueOf(str);
        return newStr.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }
}
