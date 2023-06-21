package leetCode.java;

import java.util.*;

//Topological Sort + PriorityQueue O(V + E) O(V + E), V最大為26
class Solution269 {//lintcode892
    public String alienOrder(String[] words) {
        //1 edges & counts
        Map<Character, List<Character>> edges = new HashMap<>();//<c1, c2>
        Map<Character, Integer> counts = new HashMap<>();//<ch, 次數>

        //要先找出所有字母避免漏掉, ["zy", "zx"] => "yxz" (不是"zyx"因為PriorityQueue)
        for(String word : words){
            for(char ch : word.toCharArray()){
                if(!counts.containsKey(ch))
                    counts.put(ch, 0);
            }
        }

        for(int i=1; i<words.length; i++){//O(E)
            String w1 = words[i - 1];
            String w2 = words[i];
            //The dictionary is invalid, if string a is prefix of string b and b is appear before a
            //題目設定不接受w2是w1的前綴, ["abc","ab"] => return ""
            if(w1.startsWith(w2)) return "";

            for(int j=0; j < w1.length(); j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2){
                    if(!edges.containsKey(c1)) edges.put(c1, new ArrayList<Character>());
                    edges.get(c1).add(c2);
                    counts.put(c2, counts.get(c2) + 1);
                    break;
                }
            }
        }

        //2 find start
        //In normal lexicographical order
        PriorityQueue<Character> queue = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : counts.entrySet()){
            if(entry.getValue() == 0)
                queue.offer(entry.getKey());
        }

        //3 run
        StringBuffer res = new StringBuffer();
        while(!queue.isEmpty()){//O(V)
            char node = queue.poll();
            res.append(node);

            if(edges.containsKey(node)){
                List<Character> children = edges.get(node);
                for(char child : children){
                    counts.put(child, counts.get(child) - 1);
                    if(counts.get(child) == 0)
                        queue.offer(child);
                }
            }
        }

        //要查看是否所有node都已經遍歷(成環就不會遍歷), If the order is invalid, return an empty string
        return res.length() == counts.size() ?  res.toString() : "";
    }
}