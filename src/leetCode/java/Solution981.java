package leetCode.java;

import java.util.*;

//Binary Search TimeMap(), set(): O(1) O(n), get(): O(logn) O(n)
class TimeMap {//Solution981
    Map<String, List<Object[]>> store;//<key, <[時間, value], ...>>

    public TimeMap() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!store.containsKey(key))
            store.put(key, new ArrayList<>());

        store.get(key).add(new Object[]{timestamp, value});
    }

    public String get(String key, int timestamp) {
        if(!store.containsKey(key)) return "";

        List<Object[]> list = store.get(key);
        int l = 0;
        int r = list.size() - 1;
        String res = "";
        while(l <= r){
            int mid = (l + r) / 2;
            int time = (int) list.get(mid)[0];
            if(time == timestamp){
                return (String) list.get(mid)[1];
            }else if(time < timestamp){
                res = (String) list.get(l)[1];//記錄最近的左指標紀錄
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return res;
    }
}