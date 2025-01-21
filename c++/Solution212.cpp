//DFS + Trie O(mn * 3L) O(kL), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
//k為words.length, 最多需要kL儲存Trie, 即節點數量
class Solution212 {
public:
    vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//這題若dirs宣告在dfs, 會TLE
    vector<string> res;
    
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        Trie* trie = new Trie();
        for(string& word : words)
            trie->addWord(word);

        for(int i=0; i<board.size(); ++i){
            for(int j=0; j<board[0].size(); ++j){
                dfs(i, j, trie, board);
            }
        }
        return res;
    }

    void dfs(int i, int j, Trie* trie, vector<vector<char>>& board){
        int m = board.size(), n = board[0].size();
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '*') return;
        int index = board[i][j] - 'a';
        if(!trie->children[index]) return;

        trie = trie->children[index];
        if(!trie->word.empty()){
            res.push_back(trie->word);
            trie->word.clear();
        }
        
        char temp = board[i][j];
        board[i][j] = '*';//'*'=visited
        for(auto dir : dirs)
            dfs(i + dir.first, j + dir.second, trie, board);

        board[i][j] = temp;
    }
};