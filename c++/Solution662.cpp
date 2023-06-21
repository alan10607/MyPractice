//BFS O(V) O(V)
class Solution662 {
public:
    int widthOfBinaryTree(TreeNode* root) {
        //本題在C++用int會overflow, 但題目設定距離在32-bit之內,
        //透過unsigned overflow特性, 就算前大後小相減的距離仍然會是在32-bit內
        queue<pair<TreeNode*, unsigned int>> q;//<<node, 編號>, ...>
        q.push({root, 1});

        int res = 0;
        while(!q.empty()){
            unsigned int l = q.front().second, r = l;
            for(int i=q.size(); i>0; --i){
                auto p = q.front(); q.pop();
                TreeNode* node = p.first;
                r = p.second;
                if(node->left) q.push({node->left, r * 2});
                if(node->right) q.push({node->right, r * 2 + 1});
            }
            res = max(res, (int) (r - l + 1));
        }
        return res;
    }
};
/* 給每個位子編號
                1
        2               3
    4       5       6       n
   8 9    10 11   12 13  n*2 n*2+1
*/