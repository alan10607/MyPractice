//DFS O(V) O(H)
class Solution437 {
public:
    int pathSum(TreeNode* root, int targetSum) {
        unordered_map<long, int> sumCnt = {{0, 1}};//至少會有空
        return dfs(0, sumCnt, root, targetSum);
    }

    int dfs(long sum, unordered_map<long, int> sumCnt, TreeNode* root, int target){
        if(!root) return 0;

        sum += root->val;
        long long res = sumCnt[sum - target];//查看之前有無(目前加總-target)

        ++sumCnt[sum];
        res += dfs(sum, sumCnt, root->left, target);
        res += dfs(sum, sumCnt, root->right, target);
        --sumCnt[sum];

        return res;
    }
};
/* 查看之前是否曾經存在總合為(目前加總-target)
之前總和 = 目前總和 - target
則表示存在 target = 目前總和 - 之前總和

ex.
1   2   1   -1  3 target = 3

map sum-target  res
0,1
1,1 1-3=-2      m[-2]=0
3,1 3-3=0       m[0]=1
4,1 4-3=1       m[1]=1
3,2 3-3=0       m[0]=1
6,1 6-3=2       m[3]=2

res = 5
*/