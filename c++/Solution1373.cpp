//DFS(BST) O(V) O(H)
class Solution1373 {
public:
    int res = 0;
    
    int maxSumBST(TreeNode* root) {
        dfs(root);
        return res;
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return { INT_MAX, INT_MIN, 0}; // {最小值, 最大值, 總和}

        vector<int> left_info = dfs(root->left);
        vector<int> right_info = dfs(root->right);
        if(root->val <= left_info[1] || right_info[0] <= root->val) { // 必須要是: 左側最大 < val < 右側最小
            return { INT_MIN, INT_MAX, -1}; // 任一children不是BST, 就回傳一個不能容忍的界限給parents
        }

        int sum = root->val + left_info[2] + right_info[2];
        res = max(res, sum);

        return { min(root->val, left_info[0]), max(root->val,right_info[1]), sum}; // 加入考慮這個node, 更新極值
    }
};
/* 可以透過後序收集資料, vector表示:
{BST的最小值, BST的最大值, 該BST包含自己與children的總和}
{INT_MAX, INT_MIN, 0} 表示可以允許給所有parent
{INT_MIN, INT_MAX, -1} 表示沒有任何parent可以接受, 即它的parent一定不是BST

ex:
                                1
                                not BST

                4                               3
                not BST                         {2,5,20}

        2               4               2               5
        {2,2,2}         {4,4,4}         {2,2,2}         {4,6,15}

                                                    4       6
                                                    {4,4,4} {6,6,6}


*/