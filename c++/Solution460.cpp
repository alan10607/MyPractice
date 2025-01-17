//LinkedList LRUCache(), get(), put(): O(1) O(n)
class LFUCache {
public:
    unordered_map<int, list<vector<int>>> lists; // <cnt, <node<key, value, cnt>>>
    unordered_map<int, list<vector<int>>::iterator> m; // <key, node<key, value, cnt>>
    int cap;
    int min_cnt;

    LFUCache(int capacity) {
        cap = capacity;
        min_cnt = 1;
    }

    int get(int key) {
        cout << "get" << key << endl;
        if (!m.count(key)) return -1;

        auto it = m[key];
        int cnt = (*it)[2];
        list<vector<int>>& from = lists[cnt]; // 記得要拿reference
        list<vector<int>>& to = lists[cnt + 1];
        to.splice(to.begin(), from, it); // 移動到下一個的頭部
        ++(*it)[2];

        if (cnt == min_cnt && from.empty()) {
            ++min_cnt; // 如果最小的那一個list是空, min_cnt就跳到下一個
        }

        return (*it)[1];
    }

    void put(int key, int value) {
        if (get(key) != -1) { // 若存在, 就增加cnt(已經在get時做了), 並更新數字
            auto it = m[key];
            (*it)[1] = value; 
        } else {
            if (cap == m.size()) { // 先刪除最舊的再加入
                vector<int> del = lists[min_cnt].back();
                m.erase(del[0]);
                lists[min_cnt].pop_back();
            }

            lists[1].push_front({key, value, 1});
            m[key] = lists[1].begin();
            min_cnt = 1;
        }
    }
};
/* lists之照出現次數當作key, value是list, 從頭放入node, 最舊的在後面
node=[key,value,count]
ex: 假設cap=2
lists:
1 : [[1,1,1]]
2 : [[2,2,2]]

get(1)後, 把1的node放到lists[2]
lists:
1 : []
2 : [[1,1,2], [2,2,2]]

put(3, 3), 題目要求移除最舊的再加入, list[2].pop_back()
lists:
1 : [[3,3,1]]
2 : [[1,1,2]]

*/