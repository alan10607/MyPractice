package leetCode.java;

import java.util.*;

//BFS O(n * m^2) O(n * m^2), n = wordList.length, m = wordList[0].length()
//n個word, 每個建立m個pattern, 每個pattern需要substr O(n), 總共 n * m * m
class Solution127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> edges = new HashMap<>(); // <*ot, <hot, cot, ...>>
        for (String word : wordList) {
            for (int i = 0; i < word.length(); ++i) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                edges.putIfAbsent(key, new ArrayList<>());
                edges.get(key).add(word);
            }
        }

        // BFS
        int res = 0;
        Set<String> visited = new HashSet<>(); // 記得要有visited
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        while (!q.isEmpty()) {
            ++res;

            for (int k = q.size(); k > 0; --k) {
                String word = q.poll();

                visited.add(word);
                if (word.equals(endWord)) {
                    return res;
                }

                for (int i = 0; i < word.length(); ++i) {
                    String key = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                    if (edges.containsKey(key)) {
                        for (String next : edges.get(key)) {
                            if (!visited.contains(next)) {
                                q.offer(next);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
/* beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]

edges用來記錄如何到下一個node, 建立edges:
edges={d*g=[dog], c*g=[cog], ho*=[hot], *og=[dog, log, cog], h*t=[hot], 
    lo*=[lot, log], l*t=[lot], l*g=[log], do*=[dot, dog], *ot=[hot, dot, lot], 
    d*t=[dot], co*=[cog]}

while依序BFS, queue變化:
q=[hit]
q=[hot]
q=[dot, lot]
q=[lot, dog, log]
q=[log, log, cog, cog]

"hit" -> "hot" -> "dot" -> "dog" -> cog", res = 5 


*/