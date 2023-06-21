//Heap O(nlogn) O(n), n = stones.size()
class Solution1046 {
public:
    int lastStoneWeight(vector<int>& stones) {
        priority_queue<int, vector<int>, less<int>> pq;//大排到小
        for(int stone : stones)
            pq.push(stone);

        while(pq.size() > 1){
            int a = pq.top(); pq.pop();
            int b = pq.top(); pq.pop();
            if(a > b)
                pq.push(a - b);
        }
        return pq.empty() ? 0 : pq.top();
    }
};