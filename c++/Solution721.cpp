//Union-Find O(nlogn) O(n), n = 所有種類email的數量
class Solution721 {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        //一位user可以有多個email, 但一個email只會屬於一位user
        unordered_map<string, string> parents; //<email, email parent>
        unordered_map<string, string> users; //<email, user>
        for (vector<string> account : accounts) {
            string user = account[0];
            for (int i = 1; i < account.size(); ++i) {
                string email = account[i];
                parents[email] = email; // 先設自己為父節點
                users[email] = user; // 另外記錄這個節點email屬於誰
            }
        }

        // union所有email, 這樣有關連的email都會自動到同一組
        for (vector<string> account : accounts) {
            string a = find(account[1], parents);
            for (int i = 2; i < account.size(); ++i) {
                string b = find(account[i], parents);
                parents[b] = a; // 不可以parent[a] = b, 因為a只查一次
            }
        }

        unordered_map<string, set<string>> merged; // <parent, <email, ...>> 透過set 排序與去重複
        for (vector<string> account : accounts) {
            for (int i = 1; i < account.size(); ++i) {
                string parent = find(account[i], parents);
                merged[parent].insert(account[i]);
            }
        }

        vector<vector<string>> res;
        for (auto p : merged) {
            vector<string> account;
            account.push_back(users[p.first]); // username
            account.insert(account.end(), p.second.begin(), p.second.end()); // emails
            res.push_back(account);
        }
        return res;
    }

    string find(string node, unordered_map<string, string>& parents) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node], parents);
    }
};