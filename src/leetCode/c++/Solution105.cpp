//Backtracking O(V) O(V)
class Solution105 {
public:
    int i = 0;//preorder index

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        //建立map減少重複找查
        unordered_map<int, int> inMap;//<val, inorder位置>
        for(int i=0; i<inorder.size(); ++i)
            inMap[inorder[i]] = i;

        return build(0, inorder.size() - 1, preorder, inMap);
    }

    TreeNode* build(int iStart, int iEnd, vector<int>& preorder, unordered_map<int, int>& inMap){
        if(iStart > iEnd) return nullptr;

        int val = preorder[i++];
        int iMid = inMap[val];

        TreeNode* node = new TreeNode(val);
        node->left = build(iStart, iMid - 1, preorder, inMap);
        node->right = build(iMid + 1, iEnd, preorder, inMap);
        return node;
    }
};