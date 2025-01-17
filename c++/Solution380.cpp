//RandomizedSet(), insert(), remove(), getRandom(): O(1) O(n), n為儲存的set數量
class RandomizedSet {//Solution380
public:
    vector<int> values; // 插入的val
    unordered_map<int, int> m; //<val, 在value的位置>
    RandomizedSet() {}

    bool insert(int val) {
        if (m.count(val)) return false;

        values.push_back(val);
        m[val] = values.size() - 1;
        return true;
    }

    bool remove(int val) {
        if (!m.count(val)) return false;

        int i = m[val]; // values的index
        int last = values.back(); // 用最後一個取代目標值的位置
        values[i] = last;
        values.pop_back();
        m[last] = i;
        m.erase(val); // 記得val也要移除
        return true;
    }

    int getRandom() {
        int r = rand() % values.size(); // 範圍[0, n), 0 + (rand() % (n - 0))
        return values[r];
    }
};
/* 
要在O(1)內getRandom隨機取出值, 就必須要在連續記憶體取模
用hash map實際上也不一定會O(1), 因為底下實現還有鍊錶, 不能保證必為O(1)
*/