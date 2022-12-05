//Binary Search LR Pointer O(logn + k) O(1), n = arr.size()
class Solution658 {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        //先找到最近點, 再擴張
        int r = lower_bound(arr.begin(), arr.end(), x) - arr.begin();//取上界
        int l = r - 1;//arr[l] < x <= arr[r]
        for(int i=0; i<k; ++i) {
            if(l < 0){
                ++r;
            }else if(r >= arr.size()){
                --l;
            }else if(x - arr[l] <= arr[r] - x){//index小的優先, 包含==
                --l;
            }else{
                ++r;
            }
        }
        return vector<int>(arr.begin() + l + 1, arr.begin() + r);//取的範圍是在l到r之間
    }
};
/* 要截取的範圍是在l到r之間
x=3, k=4
    1   2   3   4   5
        l   r

    1   2   3   4   5
        l       r

    1   2   3   4   5
    l           r

    1   2   3   4   5
    l               r

    1   2   3   4   5
l                   r


    1   2   4   5
        l   r

    1   2   4   5
    l       r

    1   2   4   5
    l           r

    1   2   4   5
l               r

    1   2   4   5
l                   r

*/