
//Binary Search O(log(min(m, n))) O(log(min(m, n)))
class Solution4 {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        if(nums1.size() > nums2.size()) return findMedianSortedArrays(nums2, nums1);//保證nums1是較短的

        int len = nums1.size() + nums2.size();
        int half = len / 2;//要找到前half個最小的
        int l = 0, r = nums1.size() - 1;// LR-pointer只需要在nums1上
        while(true) {
            int i = floor((l + r) / 2.0);
            int j = half - i - 2;//轉回array index, 需要還原兩次所以再減2
            int l1 = i >= 0 ? nums1[i] : INT_MIN;
            int r1 = i + 1 < nums1.size() ? nums1[i + 1] : INT_MAX;
            int l2 = j >= 0 ? nums2[j] : INT_MIN;
            int r2 = j + 1 < nums2.size() ? nums2[j + 1] : INT_MAX;

            if (l1 <= r2 && l2 <= r1) {
                return len % 2 == 1 ? min(r1, r2) : (max(l1, l2) + min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                r = i - 1;
            } else { //r1 > l2
                l = i + 1;
            }
        }

        return -1;
    }
};
/* 如果只對最小的數列作二分法, 也可以解出
ex: 假設nums1.size() < nums2.size()
透過對nums1二分法, 同時比較nums2進行LR-pointer位移

nums1=1239
nums2=123456789
總長=13, 中位數index=half=6, 
nums1中取一半(前2)個, nums2取前(6-2=4)個比較 
i=(l+r)/2=(0+3)/2=1
j=half-i-2=6-1-2=3


      li     r
nums1=12    39
nums2=1234  56789
         j
其中nums1[i]<nums2[j+1], 但是nums2[j]>nums1[i], j太大了, i太小了
所以將l左移讓i變大, 使l=i+1
i=(2+3)/2=2
j=6-2-3=2


        i
        lr   
nums1=1239
nums2=123456789
        j
此時nums1[i]<nums2[j+1] && nums2[j]<nums1[i+1], 已經抓到前最小的6個
得result=min(nums1[i+1], nums2[j+1])


*/


//Binary Search O(log(m + n)) O(log(m + n))
class Solution4_2 {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int len = nums1.size() + nums2.size();
        if (len % 2 == 1) {//i=nums1's index, j=nums2's index
            return findKth(nums1, nums2, 0, 0, len / 2 + 1);
        } else {
            return (findKth(nums1, nums2, 0, 0, len / 2) + findKth(nums1, nums2, 0, 0, len / 2 + 1)) / 2.0;
        }
    }

    int findKth(vector<int>& nums1, vector<int>& nums2, int i, int j, int k) {
        if(i >= nums1.size()) return nums2[j + k - 1];//k代表第幾個, 換成陣列時要-1表示index位置
        if(j >= nums2.size()) return nums1[i + k - 1];
        if(k == 1) return min(nums1[i], nums2[j]);

        int half = k / 2;
        int val1 = i + half - 1 < nums1.size() ? nums1[i + half - 1] : INT_MAX;
        int val2 = j + half - 1 < nums2.size() ? nums2[j + half - 1] : INT_MAX;
        if (val1 < val2) {
            return findKth(nums1, nums2, i + half, j, k - half);
        } else {
            return findKth(nums1, nums2, i, j + half, k - half);
        }
    } 
};

/*
假設總長度是=len
如果總長度是奇數個, 則中位數是第(len)/2+1個, 
如果總長度是偶數個, 則中位數是第(len)/2個與第(len)/2+1個, ex: len=8, 中位數是第4與第5個

假設中位數是第k個
可以透過二分法, 每次去掉nums1與unms2的前面k/2個

再考慮兩個邊際:
1. 如果某數列已經跑到底, 或是數列長度<k/2, 則直接捨棄, 看另一個數列就夠
2. 如果k=1, 則可以直接比較兩數列的第一個數

ex:
nums1=123456789
nums2=1349
k=7, half=3, 比較兩數列第3個數字
nums1[2]=3 < nums2[2]=4, 去掉nums1前3個


nums1=456789
nums2=1349
k=4, half=2, 比較兩數列第2個數字
nums1[1]=5 > nums2[1]=3, 去掉nums2前2個


nums1=456789
nums2=49
k=2, half=1, 比較兩數列第1個數字
nums1[0]=4 > nums2[0]=4, 去掉nums1前1個


nums1=56789
nums2=49
k=1, 最前面最小的數是4, 得中位數是4

*/