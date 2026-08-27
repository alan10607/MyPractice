//BFS O(n * m^2) O(n * m^2), n = wordList.length, m = wordList[0].length()
//n個word, 每個建立m個pattern, 每個pattern需要substr O(n), 總共 n * m * m
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
            for(int k=q.size(); k>0; --k){
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