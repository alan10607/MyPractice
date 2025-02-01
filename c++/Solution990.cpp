//Union-Find O(ElogE) O(V)
class Solution990 {
public:
    bool equationsPossible(vector<string>& equations) {
        unordered_map<char, char> parents;
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            parents[ch] = ch;
        }

        for (string eq : equations) { // 先把==的合併到一起
            if (eq[1] == '!') continue;
            char a = find(eq[0], parents);
            char b = find(eq[3], parents);
            parents[a] = b;
        }

        for (string eq : equations) { // 再查看!=是否成立
            if (eq[1] == '=') continue;
            char a = find(eq[0], parents);
            char b = find(eq[3], parents);
            if (a == b) return false;
        }

        return true;
    }

    char find(char node, unordered_map<char, char>& parents) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node], parents);
    }
};