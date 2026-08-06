package leetCode.java;

import java.util.*;

//Binary Search TimeMap(), set(): O(1) O(n), get(): O(logn) O(n)
class TimeMap {//Solution981
    Map<String, List<Object[]>> m = new HashMap<>();

    public TimeMap() {
    }
    
    public void set(String key, String value, int timestamp) {
        m.putIfAbsent(key, new ArrayList<>());
        m.get(key).add(new Object[]{value, timestamp});
    }
    
    public String get(String key, int timestamp) {
        // 題目說 All the timestamps of set() are strictly increasing
        if (!m.containsKey(key)) {
            return "";
        }
        
        // 找最大的target, 滿足 target <= timestamp, 也就是upper_bound - 1
        List<Object[]> vals = m.get(key);
        int l = 0, r = vals.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            int t = (int) vals.get(mid)[1];
            if (t <= timestamp) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (l == 0) { // upper_bound在0, 沒有小於timestamp的答案
            return "";
        }
        return (String) vals.get(l - 1)[0];
    }
}


//Binary Search TimeMap(), set(): O(1) O(n), get(): O(logn) O(n)
class TimeMap_2 {//Solution981
    Map<String, List<Object[]>> m = new HashMap<>(); // <key, <[時間, value], ...>>

    public TimeMap() {
    }
    
    public void set(String key, String value, int timestamp) {
        m.putIfAbsent(key, new ArrayList<>());
        m.get(key).add(new Object[]{value, timestamp});
    }
    
    public String get(String key, int timestamp) {
        // 題目說 All the timestamps of set() are strictly increasing
        if (!m.containsKey(key)) {
            return "";
        }
        
        // 找最大的target, 滿足 target <= timestamp, 也就是upper_bound - 1
        // 也可以用 while (l <= r), r = vals.size() - 1的寫法
        List<Object[]> vals = m.get(key);
        int l = 0, r = vals.size() - 1;
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int t = (int) vals.get(mid)[1];
            if (t <= timestamp) { // 記錄最近的左指標紀錄
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res == -1 ? "" : (String) vals.get(res)[0];
    }
}


//Binary Search TimeMap(), set(): O(1) O(n), get(): O(logn) O(n)
class TimeMap_3 {//Solution981
    // Returns a value with timestamp_prev <= timestamp
    // If there are multiple values returns associated with the largest timestamp_prev
    // -> 這題是在找upper bound的前一個值 (或稱 floor value)
    Map<String, TreeMap<Integer, String>> m = new HashMap<>();

    public TimeMap() {    
    }
    
    public void set(String key, String value, int timestamp) {
        m.putIfAbsent(key, new TreeMap<>());
        m.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!m.containsKey(key)) {
            return "";
        }
        Integer floorTime = m.get(key).floorKey(timestamp);
        if (floorTime == null) {
            return "";
        }

        return m.get(key).get(floorTime);
    }
}