//O(nlogn) O(n), n = hand.size(), 時間複雜度為排序所需
class Solution846 {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        unordered_map<int, int> cnt;
        for(int num : hand) ++cnt[num];

        sort(hand.begin(), hand.end());
        for(int num : hand){
            if(cnt[num] == 0) continue;

            for(int i=0; i<groupSize; ++i){
                if(cnt[num + i]-- == 0) return false;
            }
        }
        return true;
    }
};