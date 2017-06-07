/**
 * Created by Alexk on 2017/4/29.
 */
public class AuxiliaryMothod {
    public static int minArrIntElement(int[] arr)
    {
        int min=arr[0];
        for (int i = 1; i <arr.length ; i++) {
           if(arr[i]<min)
               min=arr[i];
        }
        return min;
    }
    public static boolean doesTheSameTree(TreeNode root1,TreeNode root2)
    {
        if(root2==null)
            return true;
        if(root1==null)
            return false;
        if(root1.val!=root2.val)
            return false;
        else
        return doesTheSameTree(root1.left,root2.left)&&doesTheSameTree(root1.right,root2.right);
    }
}
