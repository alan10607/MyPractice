package leetCode.java;

import java.util.*;

//RandomizedSet(), insert(), remove(), getRandom(): O(1) O(n), n為儲存的set數量
class RandomizedSet {//Solution380
    List<Integer> values = new ArrayList<>(); // <val>
    Map<Integer, Integer> valueToIndex = new HashMap<>(); // <val, val在vals的位置>

    public RandomizedSet() {
    }
    
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        }
        values.add(val);
        valueToIndex.put(val, values.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }
        int last = values.get(values.size() - 1);
        int index = valueToIndex.get(val);
        values.set(index, last); // 用最後一個替換現在這個的位置
        values.remove(values.size() - 1);
        valueToIndex.put(last, index); // 這裡也要更新
        valueToIndex.remove(val);
        return true;
    }
    
    public int getRandom() {
        int index = (int) (Math.random() * values.size());
        return values.get(index);
    }
}