package leetCode.java;

import java.util.*;

//Stack FreqStack(), push(), pop(): O(1) O(n)
class FreqStack {//Solution895
    public Map<Integer, Integer> cnt = new HashMap<>();//<數字, 數量>
    public Map<Integer, Deque<Integer>> freqSt = new HashMap<>();//<數量, <有此數量的數字, ...>>
    public int maxCnt;

    public FreqStack() {
    }

    public void push(int val) {
        int valCnt = cnt.getOrDefault(val, 0) + 1;
        cnt.put(val, valCnt);

        if(!freqSt.containsKey(valCnt)) freqSt.put(valCnt, new LinkedList<>());
        freqSt.get(valCnt).push(val);

        maxCnt = Math.max(maxCnt, valCnt);
    }

    public int pop() {
        int res = freqSt.get(maxCnt).poll();
        cnt.put(res, cnt.get(res) - 1);

        if(freqSt.get(maxCnt).isEmpty())
            maxCnt--;

        return res;
    }
}