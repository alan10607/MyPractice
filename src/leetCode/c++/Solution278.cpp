//Binary search, O(logn) O(1)
class Solution278 {
public:
    int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(isBadVersion(mid)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    //The API isBadVersion is defined for you
    bool isBadVersion(int version){
        return true;
    }
};
/*
    version -->
T   T   F   F   F
要找的是F最靠左, 保留右指標(也可以return l, 因為結束時l == r)
*/