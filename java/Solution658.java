package leetCode.java;

import java.util.*;

//Binary Search LR Pointer O(logn + k) O(1), n = arr.size()
class Solution658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int r = binarySearch(arr, x);
        int l = r - 1;//arr[l] < x <= arr[r]
        for(int i=0; i<k; i++){
            if(l < 0){
                r++;
            }else if(r >= arr.length){
                l--;
            }else if(x - arr[l] <= arr[r] - x){
                l--;
            }else{
                r++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i = l + 1; i < r; i++){
            res.add(arr[i]);
        }
        return res;
    }

    public int binarySearch(int[] arr, int x){
        int l = 0, r = arr.length - 1;
        while(l < r){
            int mid = (l + r) / 2;
            if(arr[mid] == x){
                return mid;
            }else if(arr[mid] < x){
                l = mid + 1;
            }else{//x < arr[mid]
                r = mid;
            }
        }
        return r;//取上界(>=x的最小值)
    }
}