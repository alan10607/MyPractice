class MapSum {//Solution677
public:
    unordered_map<string, int> m; // <key, val>
    MapSum() {}

    void insert(string key, int val) {
        m[key] = val; // insert重複的key將會覆蓋舊的
    }

    int sum(string prefix) {
        int res = 0;
        for (auto p : m) {
            if (p.first.substr(0, prefix.length()) == prefix) {
                res += p.second;
            }
        }
        return res;
    }
};