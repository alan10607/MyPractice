//Greedy O(n) O(1)
class Solution1899 {
public:
    bool mergeTriplets(vector<vector<int>>& triplets, vector<int>& target) {
        vector<int> res(3);
        for(auto triplet : triplets){
            if(triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]){
                res[0] = max(res[0], triplet[0]);
                res[1] = max(res[1], triplet[1]);
                res[2] = max(res[2], triplet[2]);
            }
        }
        return res[0] == target[0] && res[1] == target[1] && res[2] == target[2];
    }
};