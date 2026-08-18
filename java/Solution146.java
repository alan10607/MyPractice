package leetCode.java;

import java.util.*;

//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146
    class Node {
        int key;
        int val;
        Node pre;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> m = new HashMap<>(); // <key, Node>
    int capacity = 0;
    Node lru; // Least recently used
    Node mru; // Most recently used

    public LRUCache(int capacity) {
        this.m = new HashMap<>();
        this.capacity = capacity;
        this.lru = new Node(-1, -1);
        this.mru = new Node(-1, -1);
        this.lru.next = this.mru;
        this.mru.pre = this.lru;
    }

    public int get(int key) {
        if (!m.containsKey(key)) {
            return -1;
        }

        // 更新位置
        Node node = m.get(key);
        remove(node);
        insertMru(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            remove(m.get(key)); // 已經有的話要刪掉後新增
        }
        Node newNode = new Node(key, value);
        insertMru(newNode);
        m.put(key, newNode);

        if (m.size() > capacity) { // 超過上限刪掉最舊
            Node oldest = this.lru.next;
            m.remove(oldest.key);
            remove(oldest);
        }
    }

    private void remove(Node node) {
        Node p = node.pre;
        Node n = node.next;
        p.next = n;
        n.pre = p;
    }

    private void insertMru(Node node) {
        Node p = this.mru.pre;
        p.next = node;
        node.pre = p;
        node.next = this.mru;
        this.mru.pre = node;
    }
}

/*
最少使用                                最常使用
        	  nodeMap<key, Node>
                |          |
                |          |
                |          |
         next   v   next   v   next
    lru  --->   2   --->   1   --->  mru
         <---       <---       <---
         pre        pre        pre


*/


//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache_2 {//Solution146
    // 可以透過java LinkedHashMap內建方法完成
    // 參數分別是(initialCapacity初始容量, loadFactor達到比例時可能擴容, accessOrder是否按照存取順序排列)
    LinkedHashMap<Integer, Integer> m = new LinkedHashMap<>(16, 0.75f, true); // <key, value>
    int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!m.containsKey(key)) {
            return -1;
        }
        return m.get(key);
    }
    
    public void put(int key, int value) {
        m.put(key, value);
        if (m.size() > capacity) {
            // 第一個就是 LRU
            // accessOrder = true -> 按照 最近存取順序 排列
            // 最不常使用(最久沒被使用)的在前面, 最近使用的在後面
            Integer lruKey = m.entrySet().iterator().next().getKey();
            m.remove(lruKey);
        }
    }
}