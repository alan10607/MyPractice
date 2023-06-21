//Interval DP Binary Search O(nlogn) O(n), n = job.size(), upper_bound()需要O(logn)
class Solution1235 {
public:
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        vector<vector<int>> jobs;///<<start, end, profit>, ...>
        for(int i=0; i<startTime.size(); ++i)
            jobs.push_back({startTime[i], endTime[i], profit[i]});

        sort(jobs.begin(), jobs.end(), [](vector<int> a, vector<int> b){
                return a[1] < b[1];//從小到大, 排列結束時間
            });//O(nlogn)

        map<int, int> dp = {{0, 0}};//<endTime, 累積profit>, 防止prev(upper_bound)時越界
        for(auto job : jobs){
            int lastProfit = prev(dp.upper_bound(job[0]))->second;//找出上一個結束的利潤
            int sum = lastProfit + job[2];//累積這次後
            if(dp.rbegin()->second < sum)
                dp[job[1]] = sum;//如果比最後的dp大, 就放入這次
        }

        return dp.rbegin()->second;
    }
};