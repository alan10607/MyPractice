package leetCode.java;

import java.util.*;

//BFS O(n * m * n) O(n * m * n), n = wordList.length, m = wordList[0].length()
class Solution127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //1 edges
        Map<String, List<String>> edges = new HashMap<>();//<*bc, <abc, bbc, ...>>
        for(String word : wordList){
            for(int i=0; i<word.length(); i++){
                String star = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                if(!edges.containsKey(star))
                    edges.put(star, new ArrayList<String>());

                edges.get(star).add(word);
            }
        }

        //2 BFS
        Set<String> visited = new HashSet<>();//記得要有visited
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;//原地踏步也要回1
        while(!queue.isEmpty()){//O(n)
            int size = queue.size();
            for(int i=0; i<size; i++){
                String word = queue.poll();
                if(visited.contains(word))
                    continue;

                visited.add(word);
                if(word.equals(endWord))
                    return level;

                for(int j=0; j<word.length(); j++){//O(m)
                    String star = word.substring(0, j) + "*" + word.substring(j + 1, word.length());
                    if(edges.containsKey(star)){
                        for(String child : edges.get(star)){//O(n)
                            queue.offer(child);
                        }
                    }
                }
            }
            level++;
        }

        return 0;
    }
}
/* beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
edges用來記錄如何到下一個node:
*ot -> hot
h*t
ho*
v      v
O(m)   O(n)
空間複雜度為HashMap共n個每個m*n
*/