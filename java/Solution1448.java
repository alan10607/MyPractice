package leetCode.java;

//DFS O(V) O(H)
class Solution1448 {
    int res = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE); // 透過遍歷思維
        return res;
    }

    public void dfs(TreeNode root, int maxVal) {
        if (root == null) {
            return;
        }
        if (root.val >= maxVal) { // 等於也算
            ++res;
            maxVal = root.val;
        }
        dfs(root.left, maxVal);
        dfs(root.right, maxVal);
    }
}



//DFS O(V) O(H)
class Solution1448_2 {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE); // 透過分解問題
    }

    public int dfs(TreeNode root, int maxVal) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        if (root.val >= maxVal) {{ // 等於也算
            ++count;
            maxVal = root.val;
        }
        count += dfs(root.left, maxVal);
        count += dfs(root.right, maxVal);
        return count;
    }
}