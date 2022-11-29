class Node {
public:
    int k;
    int v;
    Node* next;
    Node* prev;

    Node(int key, int value) {
        k = key;
        v = value;
    }
};

//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146
public:
    unordered_map<int, Node*> m;//<key, Node's address>
    Node* lru;
    Node* mru;
    int cap;

    LRUCache(int capacity) {
        cap = capacity;
        lru = new Node(-1, -1);
        mru = new Node(-1, -1);
        mru->prev = lru;
        lru->next = mru;
    }
    
    int get(int key) {
        if(m.count(key) == 0) return -1;

        removeNode(m[key]);
        addNode(m[key]);
        return m[key]->v;
    }
    
    void put(int key, int value) {
        if(m.count(key)) removeNode(m[key]);

        Node* node = new Node(key, value);
        addNode(node);
        m[key] = node;
        if(m.size() > cap){
            Node* last = lru->next;
            removeNode(last);
            m.erase(last->k);
        }
    }

    void removeNode(Node* node){
        Node* p = node->prev;
        Node* n = node->next;
        p->next = n;
        n->prev = p;
    }

    void addNode(Node* node){
        Node* p = mru->prev;
        p->next = node;
        node->next = mru;
        mru->prev = node;
        node->prev = p;
    }
};
