//Binary Search O(nlogm) O(1), n = piles.size(), m = max(piles)
class Solution875 {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int l = 1, r = 0;//最小值設為1, 至少每次吃一個
        for(int pile : piles) r = max(r, pile);//最大值為每次都全吃完

        int res = r;//預設全吃完
        while(l < r){
            int mid = (l + r) / 2;
            int hour = calHour(mid, piles);
            if(hour <= h){//吃太快, 包含等於時
                r = mid;
                res = min(res, mid);
            }else{//吃太慢
                l = mid + 1;
            }
        }
        return res;
    }

    int calHour(int eat, vector<int>& piles){
        int hour = 0;
        for(int pile : piles)
            hour += pile / eat + (pile % eat == 0 ? 0 : 1);

        return hour;
    }
};