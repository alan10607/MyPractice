package leetCode.java;

import java.util.*;

//DFS + Trie O(mn * 3L) O(kL), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
//k為words.length, 最多需要kL儲存Trie, 即節點數量
class Solution212 {
    class Trie {
        Trie[] children;
        String word; // 同時代表trie end並記錄word

        Trie () {
            children = new Trie[26];
            word = null;
        }

        public void insert(String word) {
            Trie root = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (root.children[index] == null) {
                    root.children[index] = new Trie();
                }
                root = root.children[index];
            }
            root.word = word;
        }
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    List<String> res = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, i, j, trie);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, Trie trie) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '*') { // 出界或visited
            return;
        }
        int index = board[i][j] - 'a';
        if (trie.children[index] == null) { // 不存在trie單字
            return;
        }
        trie = trie.children[index];
        if (trie.word != null) {
            res.add(trie.word);
            trie.word = null; // 避免再被找到
        }

        for (int[] dir : dirs) {
            char temp = board[i][j];
            board[i][j] = '*'; // '*'=visited
            dfs(board, i + dir[0], j + dir[1], trie);
            board[i][j] = temp;
        }
    }
}