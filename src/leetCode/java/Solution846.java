package leetCode.java;

import java.util.*;

//O(nlogn) O(n) 時間複雜度為排序所需
class Solution846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Arrays.sort(hand);//排序以方便模擬
        Map<Integer, Integer> counts = new HashMap<>();//<卡片, 數量>
        for(int num : hand)
            counts.put(num, counts.getOrDefault(num, 0) + 1);

        for(int num : hand){
            if(counts.get(num) == 0)
                continue;//首張已經用掉, 跳過

            for(int i=num; i < num + groupSize; i++){
                if(!counts.containsKey(i) || counts.get(i) == 0)
                    return false;

                counts.put(i, counts.get(i) - 1);
            }
        }
        return true;//不用判斷counts是否卡全歸零, 因為已用for檢查遍歷全部卡
    }
}