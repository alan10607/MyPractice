package leetCode.java;

//O(mn) O(1), m = strs.length, n = strs[0].length()
// MapSum(), insert(): O(n) O(n), sum(): O(1) O(n), n=m.size()即insert的數量
class MapSum {//Solution677
    Map<String, Integer> m = new HashMap<>();
    Map<String, Integer> prefixSums = new HashMap<>();

    public MapSum() {
    }
    
    public void insert(String key, int val) {
        int oldVal = m.getOrDefault(key, 0);
        int delta = val - oldVal;

        for (int i = 1; i <= key.length(); ++i) {
            String prefix = key.substring(0, i);
            prefixSums.put(prefix, prefixSums.getOrDefault(prefix, 0) + delta);
        }
        m.put(key, val);
    }
    
    public int sum(String prefix) {
        if (!prefixSums.containsKey(prefix)) {
            return 0;
        }
        return prefixSums.get(prefix);
    }
}