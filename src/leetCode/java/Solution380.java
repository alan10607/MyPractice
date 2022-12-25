package leetCode.java;

import java.util.*;

//RandomizedSet(), insert(), remove(), getRandom(): O(1) O(n), n為儲存的set數量
class RandomizedSet {//Solution380
    List<Integer> nums;
    Map<Integer, Integer> m;//<值, nums中的位置>

    public RandomizedSet() {
        nums = new ArrayList<>();
        m = new HashMap<>();
    }

    public boolean insert(int val) {
        if(m.containsKey(val)) return false;

        nums.add(val);
        m.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if(!m.containsKey(val)) return false;

        int last = nums.get(nums.size() - 1);
        nums.set(m.get(val), last);
        nums.remove(nums.size() - 1);
        m.put(last, m.get(val));
        m.remove(val);
        return true;
    }

    public int getRandom() {
        int r = (int) (Math.random() * nums.size());
        return nums.get(r);
    }
}