//Binary Search Solution(): O(n) O(n), pickIndex(): O(logn) O(n)
class Solution528 {
public:
    vector<int> sums; // 累計數字

    Solution(vector<int>& w) {
        for (int weight : w) {
            int last = sums.empty() ? 0 : sums.back();
            sums.push_back(last + weight);
        }
    }

    int pickIndex() { // 題目要求回傳位置
        // 0<=r<sums.back(), ubber_bound不會超過sums.back()
        int r = rand() % sums.back();

        // 減去begin()是為了取得位置, upper_bound()返回迭代器, 可以用*upper_bound(...)查看value
        return upper_bound(sums.begin(), sums.end(), r) - sums.begin(); 
    }
};
/* 類似prefix sum的做法,
加總sums[i]-sum[i-1]可得到num[i], 也就是i的出現機率

index       0     1     2     3
nums=       1     2     3     4

sums= 0 <-- 1 <-- 3 <-- 6 <-- 10
         0     12    345   6789

透過 rand()%10 抽 0~9
抽到0   -> 選擇index 0
抽到1~2 -> 選擇index 1
抽到3~5 -> 選擇index 2
抽到6~9 -> 選擇index 3

返回upper bound的數的index
*/