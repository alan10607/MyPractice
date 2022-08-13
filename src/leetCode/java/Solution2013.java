package leetCode.java;

import java.util.*;

//DetectSquares(), add(): O(1) O(n) count(): O(n) O(n)
class DetectSquares {//Solution2013
    public Map<Integer, Integer> counts;//<xy位置, 次數>

    public DetectSquares() {
        counts = new HashMap<>();
    }

    public void add(int[] point) {
        int hash = hash(point[0], point[1]);
        counts.put(hash, counts.getOrDefault(hash, 0) + 1);
    }

    public int count(int[] point) {
        int res = 0;
        int x = point[0];
        int y = point[1];
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()){
            int hash = entry.getKey();
            int x0 = hash >>> 10;
            int y0 = hash & 1023;//2^10-1, 有10個1
            //判斷對角線, 重疊的點不能算
            if(Math.abs(x - x0) == Math.abs(y - y0) && x != x0 && y != y0){
                int hash1 = hash(x, y0);
                int hash2 = hash(x0, y);
                if(counts.containsKey(hash1) && counts.containsKey(hash2))
                    res += counts.get(hash) * counts.get(hash1) * counts.get(hash2);
            }
        }
        return res;
    }

    public int hash(int x, int y){
        //0 <= x, y <= 1000
        return (x << 10) | y;
    }
}
/*
	hash2
	(x0, y)		(x, y)


	(x0, y0)	(x, y0)
				hash1
*/