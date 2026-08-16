package leetCode.java;

import java.util.*;

//DetectSquares(), add(): O(1) O(n) count(): O(n) O(n)
class DetectSquares {//Solution2013
    Map<Integer, Integer> counts = new HashMap<>(); // <xy位置hash, 次數>

    public DetectSquares() {
        
    }
    
    public void add(int[] point) {
        // 0 <= x, y <= 1000, 直接位移放在一起
        int x = point[0], y = point[1];
        int hash = hash(x, y);
        counts.put(hash, counts.getOrDefault(hash, 0) + 1);
    }
    
    public int count(int[] point) {
        int res = 0;
        int x = point[0], y = point[1];
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int x0 = entry.getKey() >> 10;
            int y0 = entry.getKey() & ((1 << 10) - 1); // 2^10-1, 有10個1
            if (x != x0 && y != y0 && Math.abs(x - x0) == Math.abs(y - y0)) { // 對角點存在, 且不是自己
                int hash1 = hash(x, y0); // 另外兩點若不存在就default*0,
                int hash2 = hash(x0, y);
                res += counts.getOrDefault(hash1, 0) * counts.getOrDefault(hash2, 0) * entry.getValue();
            }
        }
        return res;
    }

    public int hash(int x, int y) {
        return (x << 10) | y;
    }
}
/*
	hash2
	(x0, y)		(x, y)


	(x0, y0)	(x, y0)
				hash1
*/