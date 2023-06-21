//Intervals O(n) O(n)
class Solution57 {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        //intervals is sorted in ascending order
        vector<vector<int>> merge;
        bool alreadyMerge = false;
        int start = newInterval[0], end = newInterval[1];
        for(vector<int> interval : intervals){
            if(interval[1] < newInterval[0]){
                merge.push_back(interval);
            }else if(newInterval[1] < interval[0]){
                if(!alreadyMerge){
                    merge.push_back({start, end});
                    alreadyMerge = true;
                }

                merge.push_back(interval);
            }else{
                start = min(start, interval[0]);
                end = max(end, interval[1]);
            }
        }

        if(!alreadyMerge)
            merge.push_back({start, end});//記得是放入計算後的{start, end}

        return merge;
    }
};