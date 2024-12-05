//Backtracking O(V) O(V)
class Solution105 {
public:
    unordered_map<int, int> in_map; //<val, 位置index>, 建立map減少重複找查
    int pre_index = 0; // preorder index

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for (int i = 0; i < inorder.size(); ++i) {
            in_map[inorder[i]] = i;
        }

        return build(preorder, inorder, 0, inorder.size() - 1);
    }

    TreeNode* build(vector<int>& preorder, vector<int>& inorder, int in_start, int in_end) {
        if (in_start > in_end) return nullptr;

        int val = preorder[pre_index++]; // pre_index剛好是dfs順序, 記得+1
        TreeNode* node = new TreeNode(val);
        int in_index = in_map[val];
        node->left = build(preorder, inorder, in_start, in_index - 1);
        node->right = build(preorder, inorder, in_index + 1, in_end);
        return node;
    }
};
/*
先來看兩者差異:
preorder  前序 [root][...left...][...right...]
inorder   中序 [...left...][root][...right...]

            _  [l]  [    r     ]
preorder =  3   9   20   15   7

           [l]  _   [    r     ]
inorder  =  9   3   15   20   7

inorder可以知道應該往哪邊build tree
preorder可以保證這些val的建立順序, dfs的走訪剛好是preorder
*/