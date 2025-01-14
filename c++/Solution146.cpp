//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146
public:
    list<pair<int, int>> cache; // <k, v>, begin是最mru最新用的
    unordered_map<int, list<pair<int, int>>::iterator> m; // <key, node<k, v>>
    int cap;

    LRUCache(int capacity) { this->cap = capacity; }

    int get(int key) {
        if (!m.count(key))return -1;

        // erase再重新push_back也可以, 但splice比較快
        cache.splice(cache.begin(), cache, m[key]); //splice(插入位置, 被傳輸的list, 被傳輸的list的iterator)
        return m[key]->second; // 或(*m[key]).second
    }

    void put(int key, int value) {
        if (m.count(key)) { // 這題有機會TLE, 改成這樣AC機率高點
            m[key]->second = value;
            cache.splice(cache.begin(), cache, m[key]);
        } else {
            cache.push_front({key, value});
            m[key] = cache.begin(); // 要給iterator, 給begin()不是front()
            if (m.size() > cap) {
                m.erase(cache.back().first); // back()回value, 要用.
                cache.pop_back();
            }
        }
    }
};


//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146_2
public:
    struct Node {
        int k, v;
        Node* next;
        Node* pre;
        Node(int key, int value) : k(key), v(value), next(nullptr), pre(nullptr) {}
    };

    Node* lru;
    Node* mru;
    unordered_map<int, Node*> m;
    int cap;

    LRUCache(int capacity) {
        cap = capacity;
        lru = new Node(-1, -1);
        mru = new Node(-1, -1);
        mru->next = lru;
        lru->pre = mru;
    }

    int get(int key) {
        if (!m.count(key)) return -1;

        Node* node = m[key];
        remove(node);
        insertMru(node);
        return node->v;
    }

    void put(int key, int value) {
        if (m.count(key)) {
            remove(m[key]); // 已經有的話要刪掉後新增
        }

        Node* node = new Node(key, value);
        insertMru(node);
        m[key] = node;
        if (m.size() > cap) {
            Node* del = lru->pre;
            remove(del);
            m.erase(del->k);
            delete del; // 刪除內存
        }
    }

    void remove(Node* node) {
        Node* p = node->pre;
        Node* n = node->next;
        p->next = n;
        n->pre = p;
    }

    void insertMru(Node* node) {
        Node* n = mru->next;
        mru->next = node;
        node->next = n;
        n->pre = node;
        node->pre = mru;
    }
};
/*
最少使用            最常使用
    --->next
mru <-> node(1,3) <-> node(2,4) <-> lru
                                <---pre
        |               |
        |               |
        v               v
        nodeMap<k, node>

*/