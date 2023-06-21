package leetCode.java;

import java.util.*;

//DFS + Trie O(mn * 3L) O(kL), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
//k為words.length, 最多需要kL儲存Trie, 即節點數量
class Solution212 {
    public List<String> findWords(char[][] board, String[] words) {
        //1 建立Trie
        Trie trie = new Trie();
        for(String word : words)
            trie.insert(word);

        //2 DFS
        int m = board.length;
        int n = board[0].length;
        Set<String> res = new HashSet<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dfs(i, j, trie, board, res);
            }
        }
        return new ArrayList<String>(res);
    }

    public void dfs(int i, int j, Trie trie, char[][] board, Set<String> res){
        if(board[i][j] == '*') return;
        char ch = board[i][j];
        if(trie.children[ch - 'a'] == null) return;

        board[i][j] = '*';//表示visited

        //先查看下一個是否為endFlag
        Trie next = trie.children[ch - 'a'];
        if(next.endFlag)
            res.add(next.word);

        int m = board.length;
        int n = board[0].length;
        if(i + 1 <  m) dfs(i + 1, j, next, board, res);
        if(i - 1 >= 0) dfs(i - 1, j, next, board, res);
        if(j + 1 <  n) dfs(i, j + 1, next, board, res);
        if(j - 1 >= 0) dfs(i, j - 1, next, board, res);

        board[i][j] = ch;
    }

    class Trie{
        public Trie[] children;
        public boolean endFlag;
        public String word;
        public Trie(){
            children = new Trie[26];
            endFlag = false;
        }

        public void insert(String word){
            Trie root = this;
            for(int i=0; i<word.length(); i++){
                int index = word.charAt(i) - 'a';
                if(root.children[index] == null)
                    root.children[index] = new Trie();

                root = root.children[index];
            }
            root.endFlag = true;
            root.word = word;
        }
    }
}