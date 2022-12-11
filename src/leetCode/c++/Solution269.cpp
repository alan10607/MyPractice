//Topological Sort + PriorityQueue O(V + E) O(V + E), V最大為26
class Solution269 {//lintcode892
public:
    string alienOrder(vector<string>& words) {
        unordered_map<char, vector<char>> edges;//<node, <child1, ...>>
        unordered_map<char, int> cnt;
        for(string word : words){
            for(char ch : word){
                cnt[ch] = 0;//初始化有哪些字母
            }
        }

        for(int i=1; i<words.size(); ++i){
            string a = words[i - 1];
            string b = words[i];

            //The dictionary is invalid, if string a is prefix of string b and b is appear before a
            if(a.rfind(b) == 0) return "";

            for(int j=0; j < a.length() && j < b.length(); ++j){
                if(a[j] != b[j]){
                    edges[a[j]].push_back(b[j]);
                    ++cnt[b[j]];
                    break;
                }
            }
        }

        //字母小排到大, sorted in Human dictionary order
        priority_queue<char, vector<char>, greater<char>> q;
        for(auto it : cnt){
            if(it.second == 0)
                q.push(it.first);
        }

        string res = "";
        while(!q.empty()){
            char node = q.top(); q.pop();

            cout << node << endl;
            res.push_back(node);
            for(auto child : edges[node]){
                if(--cnt[child] == 0){
                    q.push(child);
                }
            }
        }

        return res.length() == cnt.size() ? res : "";
    }
};
/*
wrt
wrf
er
ett
rftt

w->e->r->t->f

*/