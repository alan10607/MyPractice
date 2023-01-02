//DetectSquares(), add(): O(1) O(n) count(): O(n) O(n)
class DetectSquares {//Solution2013
public:
    unordered_map<int, int> cnt;//<x,y's hash, 出現次數>
    
    DetectSquares() {
    }
    
    void add(vector<int> point) {
        ++cnt[hash(point[0], point[1])];//0 <= x, y <= 1000
    }
    
    int count(vector<int> point) {
        int x = point[0];
        int y = point[1];
        int res = 0;
        for(auto it : cnt){
            int x0 = it.first >> 10;
            int y0 = it.first & 1023;//2^10-1, 有10個1
            int count = it.second;
            if(x != x0 && y != y0 && abs(x - x0) == abs(y - y0) //不可為同一點
                && cnt.count(hash(x, y0)) && cnt.count(hash(x0, y))){
                res += cnt[hash(x0, y0)] * cnt[hash(x, y0)] * cnt[hash(x0, y)];
            }
        }
        return res;
    }

    int hash(int x, int y){
        return (x << 10) | y;
    }
};
/*
    x0,y        x,y


    x0,y0       x,y0

*/