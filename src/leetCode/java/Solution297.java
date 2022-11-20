package leetCode.java;

import java.util.*;

//DFS serialize(), deserialize(): O(V) O(V)
class Codec {//Solution297
    public String serialize(TreeNode root) {
        //Pre-order前序遍歷, root, left, right
        return seDFS(root);
    }

    public String seDFS(TreeNode root){
        if(root == null) return "N";
        return root.val + "," + seDFS(root.left) + "," + seDFS(root.right);
    }

    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
        return deDFS(queue);
    }

    public TreeNode deDFS(Queue<String> queue){
        String str = queue.poll();
        if("N".equals(str)) return null;

        int num = Integer.parseInt(str);
        TreeNode root = new TreeNode(num);
        root.left = deDFS(queue);
        root.right = deDFS(queue);
        return root;
    }
}
/*
        1
    2       3
  4   5   N   6
 N N N N     N N

Pre-order:
1,2,4,N,N,5,N,N,3,N,6,N,N
*/