package leetCode.java;

import java.util.*;

//DFS serialize(), deserialize(): O(V) O(V)
class Codec {//Solution297

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //Pre-order前序遍歷, root, left, right
        if (root == null) {
            return "N";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        Deque<String> q = new ArrayDeque<>(Arrays.asList(vals));
        return dfs(q); 
    }

    public TreeNode dfs(Deque<String> q) {
        String val = q.poll();
        if ("N".equals(val)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = dfs(q);
        node.right = dfs(q);
        return node;
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