//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LRUCache {//Solution146
public:
    list<pair<int, int>> cache;//<k, v>
    unordered_map<int, list<pair<int, int>>::iterator> m;//<key, node<k, v>>
    int cap;

    LRUCache(int capacity) {
        cap = capacity;
    }

    int get(int key) {
        if(!m.count(key)) return -1;

        cache.splice(cache.begin(), cache, m[key]);//splice(插入位置, 被傳輸的list, 被傳輸的list的iterator)
        return m[key]->second;
    }

    void put(int key, int value) {
        if(m.count(key)) {//這題有機會TLE, 改成這樣AC機率高點
            m[key]->second = value;//更新value
            cache.splice(cache.begin(), cache, m[key]);//移到front
        }else{
            cache.push_front({key, value});
            m[key] = cache.begin();//要給iterator, 給begin()不是front()
            if(m.size() > cap){
                m.erase(cache.back().first);
                cache.pop_back();
            }
        }
    }
};