//Heap O(tlogt) O(Z), t = tasks.length, Z為tasks的種類
class Solution621 {
public:
    int leastInterval(vector<char>& tasks, int n) {
        unordered_map<char, int> cnt;//<task, 出現次數>
        for(auto task : tasks)
            ++cnt[task];

        priority_queue<int, vector<int>, less<int>> pq;//出現次數大排到小
        for(auto it : cnt)
            pq.push(it.second);//只放次數就夠了

        int period = n + 1;//記得循環是n+1
        int res = 0;
        while(!pq.empty()){
            vector<int> remain;
            int time = 0;
            while(time < period && !pq.empty()){
                ++time;
                int num = pq.top(); pq.pop();
                if(num - 1 > 0)
                    remain.push_back(num - 1);
            }

            res += remain.empty() ? time : period;

            for(int it : remain)
                pq.push(it);
        }

        return res;
    }
};
/* tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"]
A   B   C
A   D   E
A   F   G
A   x   x
A   x   x
A

res=16
*/

/*
大排到小(大根堆, 預設, 跟java相反)
priority_queue<int, vector<int>, less<int>>

auto comp = [](int& a, int& b){
        return a > b;
    };
priority_queue<int, vector<int>, decltype(comp)> pq(comp);


小排到大(小根堆)
priority_queue<int, vector<int>, greater<int>>

auto comp = [](int& a, int& b){
        return a < b;
    };
priority_queue<int, vector<int>, decltype(comp)> pq(comp);

*/