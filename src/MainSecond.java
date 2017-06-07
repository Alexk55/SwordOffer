import java.util.*;

/**
 * Created by Alexk on 2017/5/3.
 */
public class MainSecond {
    /*public static void main(String[] args) {
        *//*String str = "abc";
        ArrayList<String> testList = new ArrayList<>();
        testList = Permutation(str);
        for (String testStr : testList) {
            System.out.print(testStr + " ");
        }*//*
        int[] arrInt = {};
        System.out.print(GetLeastNumbers_Solution(arrInt, 0));

    }*/

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        if (target > 0) {
            if (root.left != null)
                FindPath(root.left, target);
            if (root.right != null)
                FindPath(root.right, target);
        }
        list.remove(list.size() - 1);
        return listAll;//递归写法
    }//二十二题

    public static String getLNRS(String str) {
        if (str == null)
            return str;
        int maxLen = 0;
        int index = 0;
        int lastIndex = 0;
        int current = 1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put(str.charAt(0), 0);
        for (int i = 1; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == null) {
                map.put(str.charAt(i), i);
                current++;
            } else {
                if (lastIndex <= map.get(str.charAt(i))) {
                    current = i - map.get(str.charAt(i));
                    lastIndex = map.get(str.charAt(i)) + 1;
                    //map.remove(str.charAt(i));
                    map.put(str.charAt(i), i);
                } else {
                    current++;
                    //map.remove(str.charAt(i));
                    map.put(str.charAt(i), i);
                }
            }

            if (current > maxLen) {
                maxLen = current;
                index = i + 1 - maxLen;
            }
        }

        return str.substring(index, index + maxLen);
    }//最长字符串的子串

    public static RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode mark = pHead;
        while (mark != null) {
            RandomListNode newNode = new RandomListNode(mark.label);
            newNode.next = mark.next;
            mark.next = newNode;
            mark = mark.next.next;
        }
        mark = pHead;
        while (mark != null)//遍历第二遍把随机节点连上
        {
            if (mark.random != null)
                mark.next.random = mark.random.next;
            mark = mark.next.next;
        }
        mark = pHead;
        RandomListNode newMark = pHead.next;
        RandomListNode newHead = newMark;
        while (newMark != null)//第三遍把复制节点拆开//-------重要：原来的链表也要正确
        {
            mark.next = mark.next.next;
            if (mark.next != null)//必须要有，否则抛空指针
                newMark.next = newMark.next.next;
            mark = mark.next;
            newMark = newMark.next;
        }
        return newHead;
    }//二十三题

    private TreeNode leftNode = null;//定义一个节点指向最左边节点
    private TreeNode rightNode = null;//定义一个节点指向最右边节点

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        if (pRootOfTree.left != null)
            Convert(pRootOfTree.left);
        //递归找到最左边的节点，然后开始一个一个节点往双向链表里边加入
        if (rightNode == null) {
            leftNode = rightNode = pRootOfTree;
        } else {
            rightNode.right = pRootOfTree;
            pRootOfTree.left = rightNode;
            rightNode = pRootOfTree;
        }
        if (pRootOfTree.right != null)
            Convert(pRootOfTree.right);
        return leftNode;
    }//第二十四题

    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> strSet = new HashSet<>();
        if (str == null || str.length() == 0 || str.length() > 9)
            return list;
        else {
            PermutationHelp(str.toCharArray(), 0, strSet);
            for (String setStr : strSet)
                list.add(setStr);
            Collections.sort(list);
            return list;
        }
    }//全排列

    private static void PermutationHelp(char[] arrChar, int i, HashSet<String> strSet) {
        if (i == arrChar.length - 1)
            strSet.add(String.valueOf(arrChar));
        else {
            for (int j = i; j < arrChar.length; j++) {
                HashSet<Character> set = new HashSet<>();
                if (j == i || !set.contains(arrChar[j])) {
                    set.add(arrChar[j]);
                    charSwap(arrChar, i, j);
                    PermutationHelp(arrChar, i + 1, strSet);
                    charSwap(arrChar, j, i);
                }
            }
        }
    }

    private static void charSwap(char[] arrChar, int i, int j) {
        char temp = arrChar[i];
        arrChar[i] = arrChar[j];
        arrChar[j] = temp;
    }

    private static void intSwap(int[] arrInt, int i, int j) {
        arrInt[i] += arrInt[j];
        arrInt[j] = arrInt[i] - arrInt[j];
        arrInt[i] -= arrInt[j];
    }

    private static int partation(int[] array, int low, int high) {
        //Random random =new Random();
        int provtkey = array[low];
        while (low < high) {
            if (low < high && array[high] >= provtkey)
                high--;
            array[low] = array[high];
            if (low < high && array[low] <= provtkey)
                low++;
            array[high] = array[low];
        }
        array[low] = provtkey;
        return low;
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        /*if(array==null||array.length==0)
            return 0;
        int mid = array.length>>1;
        int high =array.length-1;
        int provit =partation(array,0,array.length-1);
        while(provit!=mid)
        {
            if(provit<mid)
                provit=partation(array,provit+1,high);
            if(provit>mid)
                provit=partation(array,0,provit-1);
        }
        int result = array[provit];
        int count=0;
        for (int i = 0; i <array.length ; i++) {
            if(array[i]==result)
                count++;
        }
        if(count>mid)
            return result;
        else return 0;*/
        if (array == null || array.length == 0)
            return 0;
        int count = 1;
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == result)
                count++;
            else {
                count--;
                if (count == 0) {
                    result = array[i];
                    count++;
                }
            }
        }
        int other = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result)
                other++;
        }
        if (other > (array.length / 2))
            return result;
        else return 0;
    }//二十六题基于快排

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length < k||input.length==0)
            return list;
        int low = 0;
        int high = input.length - 1;
        int provit = partation(input, low, high);
        while (low < high && provit != (k - 1)) {
            if (provit < (k - 1)) {
                low = provit + 1;
                provit = partation(input, low, high);
            }
            if (provit > (k - 1)) {
                high = provit - 1;
                provit = partation(input, low, high);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }//ac

    public static int FindGreatestSumOfSubArray(int[] array) {
        /*int maxNum=0,summation=0,j=0;
        while(j<array.length&&array[j]<0)
            j++;
        if(j!=array.length) {
            for (int i = j; i < array.length; i++) {
                summation += array[i];
                if (maxNum < summation)
                    maxNum = summation;
                if (summation < 0)
                    summation = 0;
            }
        }
        else
        {
            j=0;
            maxNum=array[0];
            while(j<array.length){
                if(maxNum<array[j])
                    maxNum=array[j];
                j++;
            }
        }
        return maxNum;*/
        int[] d = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || d[i - 1] < 0)
                d[i] = array[i];
            else
                d[i] = d[i - 1] + array[i];
        }
        int max = d[0], j = 1;
        while (j < d.length) {
            if (max < d[j])
                max = d[j];
            j++;
        }
        return max;
    }//dp和贪心

    public static int NumberOf1Between1AndN_Solution(int n) {
        if(n<0)
            return 0;
        int high=1,low,tmp,cur,i=1;
        int total=0;
        while(high!=0)
        {
           high = n/(int)Math.pow(10,i);
           tmp = n%(int)Math.pow(10,i);
           cur = tmp/(int)Math.pow(10,i-1);
           low = tmp%(int)Math.pow(10,i-1);
           if(cur==1)
               total+= high*Math.pow(10,i-1)+low+1;
           else if(cur<1)
               total+= high*Math.pow(10,i-1);
           else
               total+=(high+1)*Math.pow(10,i-1);
           i++;
        }
        return total;
    }//统计一个数字里1出现的次数
    public static String PrintMinNumber(int [] numbers) {
        if(numbers.length==0||numbers==null)
            return "";
        if(numbers.length==1)
            return String.valueOf(numbers[0]);
        ArrayList<String> list= new ArrayList<>();
        String maxFirst = String.valueOf(numbers[0]);
        list.add(maxFirst);
        String temp;
        for (int i = 1; i <numbers.length ; i++) {
            temp =String.valueOf(numbers[i]);
            list.add(temp);
            if((maxFirst+temp).compareTo(temp+maxFirst)>0)
                maxFirst=temp;
        }
        StringBuffer stringBuffer=new StringBuffer(maxFirst);
        list.remove(maxFirst);
        while(list.size()>1)
        {
            maxFirst=list.get(0);
            for(String str:list)
            {
                if((maxFirst+str).compareTo(str+maxFirst)>0)
                    maxFirst=str;
            }
            stringBuffer.append(maxFirst);
            list.remove(maxFirst);
        }
        stringBuffer.append(list.get(0));
        return stringBuffer.toString();
    }
}

