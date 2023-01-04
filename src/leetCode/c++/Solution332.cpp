//Hierholzer's Algorithm O(ElogE) O(E), 時間複雜度為演算法本身O(E)乘上PriorityQueue所需O(logE)
class Solution332 {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        //return the itinerary lexical order, 透過pq, 文字從小到大
        unordered_map<string, priority_queue<string, vector<string>, greater<string>>> edges;//<起點, <目的地1, ...>>
        for(auto ticket : tickets)
            edges[ticket[0]].push(ticket[1]);

        vector<string> res;
        dfs("JFK", edges, res);
        reverse(res.begin(), res.end());//矯正逆序入列
        return res;
    }

    void dfs(string node, unordered_map<string, priority_queue<string, vector<string>, greater<string>>>& edges, vector<string>& res){
        while(edges.count(node) && !edges[node].empty()){//回到此層後若還有會繼續while, 遍歷所有可能
            string child = edges[node].top();
            edges[node].pop();
            dfs(child, edges, res);
        }

        res.push_back(node);
    }
};