//Interval O(m + n) O(1), m = firstList.size(), n = secondList.size();
class Solution986 {
public:
    vector<vector<int>> intervalIntersection(vector<vector<int>>& firstList, vector<vector<int>>& secondList) {
        int i = 0, j = 0;
        vector<vector<int>> res;
        while(i < firstList.size() && j < secondList.size()){
            int start = max(firstList[i][0], secondList[j][0]), end = min(firstList[i][1], secondList[j][1]);
            if(start <= end) res.push_back({start, end});//符合條件才加入

            firstList[i][1] < secondList[j][1] ? ++i : ++j;//以end做比較
        }
        return res;
    }
};
/* 應該先跳過end較小的

-----------
  -----  -----

*/