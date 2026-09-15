package leetCode.java;

import java.util.*;

// Solution(): O(m) O(m), pick(): O(1) O(m), m = blacklist.size()
class Solution710 {
    Map<Integer, Integer> blackToWhite; // <black val, white val>
    int numSize;

    public Solution(int n, int[] blacklist) {
        this.blackToWhite = new HashMap<>();
        this.numSize = n - blacklist.length;

        // 建立set方便之後判斷是否是black
        Set<Integer> s = new HashSet<>();
        for (int black : blacklist) {
            s.add(black);
        }

        // 把黑名單用白名單替換掉
        int white = numSize; // 從size開始有可能是白名單, 要讓黑名單map到
        for (int black : blacklist) {
            if (black >= numSize) {// [size, n)的黑名單就不用處理了, 本來就不在裡面
                continue;
            }

            while (s.contains(white)) {
                ++white; // 跳過是black的
            }
            blackToWhite.put(black, white++);
        }
    }
    
    public int pick() {
        int num = (int) (Math.random() * numSize);
        return blackToWhite.containsKey(num) ? blackToWhite.get(num) : num;
    }
}
/*
test case會有Solution(1000000000, [640145908])這種case
要用黑名單處理建立map而避免用白名單

ex: n=7, blacklist=[2, 3, 5]

i                   0   1   2   3
num                 0   1   2   3   4   5   6
after rearrange     0   1   4   6


blackToWhite={2=4, 3=6}
*/