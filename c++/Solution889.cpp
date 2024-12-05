//Backtracking O(V) O(V)
class Solution889 {
public:
    int pre_index = 0, post_index = 0;
    TreeNode* constructFromPrePost(vector<int>& pre, vector<int>& post) {
        TreeNode* node = new TreeNode(pre[pre_index]);
        ++pre_index;

        if (node->val != post[post_index]) {
            node->left = constructFromPrePost(pre, post);
        }
        if (node->val != post[post_index]) {
            node->right = constructFromPrePost(pre, post);
        }

        ++post_index;
        return node; // 此時node->val == post[post_index]
    }
};
/*
先來看兩者差異:
preorder  前序 [root][...left...][...right...]
postorder 後序 [...left...][...right...][root]

沒有inorder中序=>無法確定還原成哪個, 因此題目要求可以回傳任一種tree
ex:  preorder = [1,2,3], postorder = [3,2,1], 可以長得是
    1         1
  2     or      2
3                 3


preorder與postorder邏輯相反,
pre[pre_index] == post[post_index]時, 就代表是是末端node,
此時返回此node, 反之若無相遇, 則依序左到右遍歷
*/


class Solution889_2 {
public:
    unordered_map<int, int> post_map;
    TreeNode* constructFromPrePost(vector<int>& pre, vector<int>& post) {
        int n = pre.size();
        for (int i = 0; i < n; ++i) {
            post_map[post[i]] = i;
        }
        return build(pre, 0, n - 1, post, 0, n - 1);
    }

    TreeNode* build(vector<int>& pre, int pre_start, int pre_end,
                    vector<int>& post, int post_start, int post_end) {
        if (pre_start > pre_end || post_start > post_end) return nullptr;

        TreeNode* node = new TreeNode(pre[pre_start]);
        if (pre_start == pre_end) return node; 

        int i = post_map[pre[pre_start + 1]]; // preorder的下一個是left node, 可以查到postorder中的left起始點
        int len = i - post_start + 1; // left side的長度

        node->left = build(pre, pre_start + 1, pre_start + len, 
                           post, post_start, i);
        node->right = build(pre, pre_start + len + 1, pre_end, 
                            post, i + 1, post_end - 1);
        return node;
    }
};
/* ex: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1] _表示root

            _________________________
preorder=   1   2   4   5   3   6   7
                ^
                pre_start+1

            _________________________
postorder=  4   5   2   6   7   3   1
                    ^
                    i
可見left side len = 3, pre=[1][245][367], post=[452][673][1]
先來看build([245]):

                _________
preorder=   1   2   4   5   3   6   7
                    ^
                    pre_start+1

            _________                
postorder=  4   5   2   6   7   3   1
            ^
            i
可見left side len = 1, pre=[2][4][5], post=[4][5][2], 都剩下len=1, 會直接return node
現在來看build([367]):
                                
                            _________
preorder=   1   2   4   5   3   6   7
                                ^
                                pre_start+1

                        _________
postorder=  4   5   2   6   7   3   1
                        ^
                        i
可見left side len = 1, pre=[3][6][7], post=[6][7][3], 都剩下len=1, 會直接return node

最後跳出所有stack, 得到解
*/
