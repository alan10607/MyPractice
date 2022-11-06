package leetCode.java;

//Binary search, O(logn) O(1)
public class Solution278 extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(isBadVersion(mid)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return r;
    }
}

//The isBadVersion API is defined in the parent class VersionControl
class VersionControl{
    boolean isBadVersion(int version) {
        return true;
    }
}