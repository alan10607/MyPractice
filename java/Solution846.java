package leetCode.java;

import java.util.*;

//Greedy O(nlogn) O(n) 時間複雜度為排序所需
class Solution846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        // 整理牌的數量
        Map<Integer, Integer> counts = new HashMap<>(); // <卡片, 數量>
        for (int num : hand) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // 開始群組
        Arrays.sort(hand);
        for (int num : hand) {
            if (counts.get(num) == 0) {
                continue; // 卡片已經用完, 跳過
            }

            for (int i = 0; i < groupSize; ++i) {
                int target = num + i;
                if (!counts.containsKey(target) || counts.get(target) == 0) {
                    return false;
                }
                counts.put(target, counts.get(target) - 1);
            }
        }
        return true;
    }
}