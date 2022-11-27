//Union-Find O(nlogn) O(n), n = 所有種類email的數量
class Solution721 {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        //一位user可以有多個email, 但一個email只會屬於一位user
        unordered_map<string, string> parent;//<child, parent>
        unordered_map<string, string> user;//<email, user>

        for(auto account : accounts){
            for(int i=1; i<account.size(); ++i){
                parent[account[i]] = account[i];//先設自己為父節點
                user[account[i]] = account[0];//另外記錄這個節點email屬於誰
            }
        }

        //Union Find所有email
        for(auto account : accounts){
            string a = find(account[1], parent);
            for(int i=2; i<account.size(); ++i){
                string b = find(account[i], parent);
                parent[b] = a;//不可以parent[a] = b,因為a只查一次
            }
        }

        //透過set濾掉重複email, emails in sorted order, 用set
        unordered_map<string, set<string>> merge;//<parent. <child1, child2, ...>>
        for(auto account : accounts){
            for(int i=1; i<account.size(); ++i){
                string root = find(account[i], parent);
                merge[root].insert(account[i]);
            }
        }

        //找出user組合答案
        vector<vector<string>> res;
        for(auto it : merge){
            string key = user[it.first];
            vector<string> line(it.second.begin(), it.second.end());
            line.insert(line.begin(), key);
            res.push_back(line);
        }
        return res;
    }

    string find(string node, unordered_map<string, string>& parent){
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node], parent);
    }
};