package leetCode.java;

import java.util.*;

//Heap O(nlogk) O(n), n = words.size()
class Solution692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for(String word : words)
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);

        //次數小到大, 字母大到小
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey())
                        : a.getValue() - b.getValue());//<<word, 次數>, ...>

        for(Map.Entry<String, Integer> entry : cnt.entrySet()){
            pq.offer(entry);
            if(pq.size() > k)
                pq.poll();
        }

        String[] res = new String[pq.size()];
        for(int i = pq.size() - 1; i>=0; i--){
            res[i] = pq.poll().getKey();
        }
        return Arrays.asList(res);
    }
}