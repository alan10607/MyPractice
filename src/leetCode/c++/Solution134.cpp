//Greedy O(n) O(1)
class Solution134 {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int sum = 0;
        for(int i=0; i<gas.size(); ++i)
            sum += (gas[i] - cost[i]);

        if(sum < 0) return -1;//sum >= 則必有解

        int remain = 0, res = 0;
        for(int i=0; i<gas.size(); ++i){
            remain += (gas[i] - cost[i]);
            if(remain < 0){
                res = i + 1;//不會再回到0
                remain = 0;
            }
        }
        return res;
    }
};