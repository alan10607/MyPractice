//BFS O(n * m * n) O(n * m * n), n = wordList.size(), m = wordList[0].length()
class Solution127 {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_map<string, vector<string>> edges;//<*ot, <hot, cot, ...>>
        for(auto word : wordList){
            for(int i=0; i<word.length(); ++i){
                string k = word.substr(0, i) + "*" + word.substr(i + 1, word.length() - i - 1);
                edges[k].push_back(word);
            }
        }

        unordered_set<string> visited;//記得要有visited
        queue<string> q;
        q.push(beginWord);
        int res = 0;
        while(!q.empty()){
            ++res;
            for(int i=q.size(); i>0; --i){
                auto word = q.front(); q.pop();
                if(visited.count(word)) continue;
                visited.insert(word);

                if(word == endWord) return res;
                
                for(int i=0; i<word.length(); ++i){
                    string k = word.substr(0, i) + "*" + word.substr(i + 1, word.length() - i - 1);
                    if(edges.count(k)){
                        for(string child : edges[k])
                            q.push(child);
                    }
                }
            }
        }
        return 0;
    }
};
/* edges:
*ot
h*t -> hot
ho*
*/