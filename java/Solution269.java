package leetCode.java;

import java.util.*;

//Topological Sort O(V + E) O(V + E), V最大為26
class Solution269 {//lintcode892
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> edges = new HashMap<>(); // <node, <child1, ...>>
        Map<Character, Integer> counts = new HashMap<>();

        // 先找出所有字母, ex: ["abc", "abd"], c->d, 答案可以是 abcd, cdab, cadb任一種
        for (String word : words) {
            for (int i = 0; i < word.length(); ++i) {
                counts.putIfAbsent(word.charAt(i), 0);
            }
        }

        for (int i = 1; i < words.length; ++i) {
            String w1 = words[i - 1], w2 = words[i];
            for (int j = 0; j < w1.length(); ++j) {
                if (j == w2.length()) { // w2 is w1 prefix
                    return "";
                }
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) { // 答案要的方向是c1->c2
                    edges.putIfAbsent(c1, new HashSet<>());
                    if (!edges.get(c1).contains(c2)) { // 用set避免同字母順序被重複計算
                        edges.get(c1).add(c2);
                        counts.put(c2, counts.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> q = new ArrayDeque<>(); // 不用pq也行, 題目沒有要求答案要字母順序(return any of them)
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char node = q.poll();

            sb.append(node);

            if (edges.containsKey(node)) {
                for (char next : edges.get(node)) {
                    counts.put(next, counts.get(next) - 1);
                    if (counts.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        return (sb.length() == counts.size()) ? sb.toString() : "";
    }
}
/*
ex: words=["baa","abcd","abca","cab","cade"]

建立edges:
b->a
d->a
a->c
b->d

edges={a=[c], b=[a, d], d=[a]}

Kahn's Algorithm:

counts={a=2, b=0, c=1, d=1, e=0}, 初始q=[b,e] 
pop:b, counts={a=1, b=0, c=1, d=0, e=0}, counts[d]=0 加入q, q=[e,d] 
pop:e, counts={a=1, b=0, c=1, d=0, e=0}, q=[d] 
pop:d, counts={a=0, b=0, c=1, d=0, e=0}, counts[a]=0 加入a, q=[a]
pop:a, counts={a=0, b=0, c=0, d=0, e=0}, counts[c]=0 加入c, q=[c]
pop:c, counts={a=0, b=0, c=0, d=0, e=0}, q=[] 離開while

res="bedac"


*/


//Topological Sort PriorityQueue O(V + E) O(V + E), V最大為26
class Solution269_2 {//lintcode892
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