//DFS(BST) O(H + k) O(H), H為高度, 時間複雜度供需要先下H, 再上k
class Solution230 {
public:
    int kthSmallest(TreeNode* root, int k) {
        stack<TreeNode*> st;
        while(root || !st.empty()){
            while(root){
                st.push(root);
                root = root->left;
            }

            root = st.top(); st.pop();
            if(--k == 0) return root->val;

            root = root->right;
        }
        return -1;
    }
};