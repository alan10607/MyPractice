package leetCode.java;

//DFS O(n) O(logn), n = nums.size()
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(0, nums.length - 1, nums);
    }

    public TreeNode build(int start, int end, int[] nums){
        if(start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(start, mid - 1, nums);
        root.right = build(mid + 1, end, nums);
        return root;
    }
}