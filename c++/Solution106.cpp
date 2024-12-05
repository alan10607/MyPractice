//Backtracking O(V) O(V)
class Solution106 {
public:
    unordered_map<int, int> in_map; //<val, 位置index>, 建立map減少重複找查
    int post_index; // postorder index
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        post_index = postorder.size() - 1;
        for (int i = 0; i < inorder.size(); ++i) {
            in_map[inorder[i]] = i;
        }
        return build(inorder, postorder, 0, inorder.size() - 1);
    }

    TreeNode* build(vector<int>& inorder, vector<int>& postorder, int in_start, int in_end) {
        if (in_start > in_end) return nullptr;

        int val = postorder[post_index--]; // postorder, 從最後開始往前退
        TreeNode* node = new TreeNode(val);
        int in_index = in_map[val];
        node->right = build(inorder, postorder, in_index + 1, in_end); // 改成先從right進去以符合postorder順序
        node->left = build(inorder, postorder, in_start, in_index - 1);
        return node;
    }
};
/*
先來看兩者差異:
inorder   中序 [...left...][root][...right...]
postorder 後序 [...left...][...right...][root]

           [l]  [    r    ]   _
postorder=  9   15  7    20   3

           [l]  _   [    r     ]
inorder  =  9   3   15   20   7

inorder可以知道應該往哪邊build tree
postorder如果倒過來, 也可以像Solution105那樣, 改成先從right
side進入backtracking, 也可以保證這些val的建立順序, dfs的走訪剛好是postorder
*/