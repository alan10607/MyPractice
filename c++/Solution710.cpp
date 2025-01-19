// Solution(): O(m) O(m), pick(): O(1) O(m), m = blacklist.size()
class Solution710 {
public:
    unordered_map<int, int> black_to_white; // <黑名單的數字, [size, n)的白名單數字>
    int size;

    Solution(int n, vector<int>& blacklist) {
        size = n - blacklist.size();

        unordered_set<int> blacks(blacklist.begin(), blacklist.end()); // 用來判斷有是否是黑名單
        int white = size; // 從size開始有可能是白名單, 要讓黑名單map到
        for(int black : blacklist){
            if(black >= size) { // [size, n)的黑名單就不用處理了
                continue;
            }

            while(blacks.count(white)){
                ++white;
            }

            black_to_white[black] = white;
            ++white;
        }
    }
    
    int pick() {
        int r = rand() % size;
        return black_to_white.count(r) ? black_to_white[r] : r;
    }
};
/* 
test case會有Solution(1000000000, [640145908])這種case
要用黑名單處理而避免用白名單

ex: n=5, blacklist=[2,4]
可知白名單長度 size = n - blacklist.size() = 5 - 2 = 3

           不可用
可用        =====
=========
0   1   2   3   4

1. 可用中的2應該移除
2. 不可用中的3應該從2map到

blacklist移除4, 因為本來就不可用
當rand取到2時, 要map到3



*/