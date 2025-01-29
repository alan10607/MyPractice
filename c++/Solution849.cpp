// O(n) O(1), n = seats.size()
class Solution849 {
public:
    int maxDistToClosest(vector<int>& seats) {
        vector<int> people;
        int last = -1;
        int res = 0;
        int n = seats.size();
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 0) continue;

            if (last == -1) {
                res = max(res, i); // 靠牆情況, 左側沒人
            } else {
                res = max(res, (i - last) / 2);
            }
            last = i;
        }

        // seats.size()返回 size_t會導致 no matching function for call to 'max', 先用int n取值
        res = max(res, (n - 1) - last); // 靠牆情況, 右側沒人

        return res;
    }
};
/*
靠牆情況:
(0,4]會坐在0, 距離=4
        0   1   2   3   4   5
seats=  *               1


[4,5)會坐在5, 距離=1
        0   1   2   3   4   5
seats=                  1   *


偶數情況:
[0,4]會坐在2, 最近距離=2
        0   1   2   3   4   5
seats=  1       *       1


奇數情況:
[0,5]會坐在2, 最近距離=2
        0   1   2   3   4   5
seats=  1       *           1

*/