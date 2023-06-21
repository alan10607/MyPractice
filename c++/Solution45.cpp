//Greedy O(n) O(1)
class Solution45 {
public:
    int jump(vector<int>& nums) {
        int right = 0, last = 0, res = 0;
        for(int i=0; i<nums.size() - 1; ++i){//只需要到n-2, 因為n-1即到達不用再跳了
            right = max(right, i + nums[i]);
            if(i == last){
                res++;
                last = right;//last表上層BFS的最大值
                if(last >= nums.size() - 1) break;
            }
        }
        return res;
    }
};