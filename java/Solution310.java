package leetCode.java;

import java.util.*;

//BFS O(V) O(V)
class Solution310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            List<Integer> zero = new ArrayList<>();
            zero.add(0);
            return zero;
        }

        List<Set<Integer>> adjs = new ArrayList<>();
        for(int i=0; i<n; i++)
            adjs.add(new HashSet<>());

        for(int[] e : edges){
            adjs.get(e[0]).add(e[1]);
            adjs.get(e[1]).add(e[0]);
        }

        //找出最外側
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<adjs.size(); i++){
            if(adjs.get(i).size() == 1)
                q.offer(i);
        }

        //開始剝洋蔥
        int unvisitCnt = n;
        while(unvisitCnt > 2){
            unvisitCnt -= q.size();
            for(int i=q.size(); i>0; i--){
                int node = q.poll();
                Set<Integer> children = adjs.get(node);
                for(int child : children){
                    adjs.get(child).remove(node);//到下一個點回頭刪除自己
                    if(adjs.get(child).size() == 1)//一條邊, 此時是tree
                        q.offer(child);
                }
            }
        }

        return new ArrayList<Integer>(q);
    }
}