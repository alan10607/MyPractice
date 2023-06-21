package leetCode.java;

import java.util.*;

//Union-Find O(nlogn) O(n), n = 所有種類email的數量
class Solution721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();//<email, parent>
        Map<String, String> user = new HashMap<>();//<email, user>
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                parent.put(account.get(i), account.get(i));
                user.put(account.get(i), account.get(0));
            }
        }

        //Union-Find email
        for(List<String> account : accounts){
            String a = find(account.get(1), parent);
            for(int i=2; i<account.size(); i++){
                String b = find(account.get(i), parent);
                parent.put(b, a);
            }
        }

        //過濾掉重複email
        Map<String, Set<String>> merge = new HashMap<>();//<email's parent, <email1, email2, ...>>
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                String p = find(account.get(i), parent);
                if(!merge.containsKey(p))
                    merge.put(p, new TreeSet<String>());//emails in sorted order

                merge.get(p).add(account.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String, Set<String>> entry : merge.entrySet()){
            List<String> line = new ArrayList<>();
            line.add(user.get(entry.getKey()));//放入user
            for(String email : entry.getValue()){
                line.add(email);
            }
            res.add(line);
        }

        return res;
    }

    public String find(String node, Map<String, String> parent){
        if(node.equals(parent.get(node))) return node;
        String p = find(parent.get(node), parent);
        parent.put(node, p);
        return p;
    }
}