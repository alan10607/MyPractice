//Stack O(nlogn) O(n) 時間複雜度為排序所需
class Solution853 {
public:
    int carFleet(int target, vector<int>& position, vector<int>& speed) {
        map<int, double> m;//<位置, 到達終點的所需時間>
        for(int i=0; i<position.size(); ++i)
            m[position[i]] = (double) (target - position[i]) / speed[i];//記得將任一轉為double

        int res = 0;
        double cur = 0;//目前位置的速度
        for(auto it=m.rbegin(); it!=m.rend(); ++it){//從右邊開始(大到小)
        cout<<it->first<<it->second<<endl;
            if(it->second > cur){//後面的車若時間較多, 則追不上
                ++res;
                cur = it->second;
            }
        }
        return res;
    }
};
/*
target = 12, time = (target - position) / speed
position =  [ 0, 3, 5, 8, 10]
speed =     [ 1, 3, 1, 4,  2]
time =      [12, 3, 7, 1,  1]

-------------------------
  12     3,7        1,1
-------------------------

*/