package leetCode.java;

import java.util.*;

//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146
    class Node{
        public Node next;
        public Node pre;
        public int k;
        public int v;
        public Node(int key, int val){
            k = key;
            v = val;
        }
    }

    public Node lru;//Least recently used
    public Node mru;//Most recently used
    public Map<Integer, Node> nodeMap;
    public int cap;

    public LRUCache(int capacity) {
        lru = new Node(-1, -1);
        mru = new Node(-1, -1);
        nodeMap = new HashMap<>();
        cap = capacity;

        lru.next = mru;
        mru.pre = lru;
    }

    public int get(int key) {
        if(!nodeMap.containsKey(key)) return -1;

        //更新位置
        Node node = nodeMap.get(key);
        remove(node);
        insertNew(node);
        return node.v;
    }

    public void put(int key, int value) {
        if(nodeMap.containsKey(key))
            remove(nodeMap.get(key));//已經有的話要刪掉後新增

        Node node = new Node(key, value);
        insertNew(node);
        nodeMap.put(key, node);

        if(nodeMap.size() > cap){
            Node del = lru.next;
            remove(del);//刪掉超過大小
            nodeMap.remove(del.k);
        }
    }

    public void insertNew(Node node){
        Node p = mru.pre;
        p.next = node;
        node.next = mru;
        mru.pre = node;
        node.pre = p;
    }

    public void remove(Node node){
        Node p = node.pre;
        Node n = node.next;
        p.next = n;
        n.pre = p;
    }
}
/*
最少使用            最常使用
lru <-> 2 <-> 1 <-> mru
        |     |
        v     v
		nodeMap<k, v>

*/