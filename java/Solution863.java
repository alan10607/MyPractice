package leetCode.java;

import java.util.*;

//DFS O(V) O(H)
class Solution863 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Set<TreeNode> visited = new HashSet<>();
        findParent(root, parent);
        dfs(target, parent, k, visited);
        return res;
    }

    public void findParent(TreeNode root, Map<TreeNode, TreeNode> parent){
        if(root == null) return;
        if(root.left != null) parent.put(root.left, root);
        if(root.right != null) parent.put(root.right, root);
        findParent(root.left, parent);
        findParent(root.right, parent);
    }

    public void dfs(TreeNode target, Map<TreeNode, TreeNode> parent, int k, Set<TreeNode> visited){
        if(target == null || visited.contains(target)) return;
        visited.add(target);

        if(k == 0){
            res.add(target.val);
            return;
        }

        if(parent.containsKey(target))
            dfs(parent.get(target), parent, k - 1, visited);

        dfs(target.left, parent, k - 1, visited);
        dfs(target.right, parent, k - 1, visited);
    }
}