//BFS O(V) O(V), V是所有數字可能, 本題固定為1000
class Solution752 {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> visited;
        unordered_set<string> deads(deadends.begin(), deadends.end());
        int res = 0;
        queue<string> q;
        q.push("0000");
        while (!q.empty()) {
            for (int i = q.size(); i > 0; --i) {
                string node = q.front(); q.pop();
                if (deads.count(node)) continue;
                if (visited.count(node)) continue;
                if (node == target) return res;

                visited.insert(node);

                for (int j = 0; j < 4; ++j) { // 朝其他8個node移動看看
                    q.push(plusOne(j, node));
                    q.push(minusOne(j, node));
                }
            }

            ++res;
        }
        return -1;
    }

    string plusOne(int i, string num) {
        num[i] = (num[i] == '9') ? '0' : (num[i] + 1);
        return num;
    }

    string minusOne(int i, string num) {
        num[i] = (num[i] == '0') ? '9' : (num[i] - 1);
        return num;
    }
};
/* 每個密碼是一個node, 這個node連上其他8個node, 代表可以轉動到的密碼
ex: 0000可以連上1000, 9000, 0100, 0900, 0010, 0090, 0001, 0009

這樣題目就可以透過BFS的思路來解
*/


//BFS O(V) O(V), V是所有數字可能, 本題固定為1000
class Solution752_2 {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> q1, q2;
        unordered_set<string> visited;
        unordered_set<string> deads(deadends.begin(), deadends.end());
        int res = 0;

        q1.insert("0000");
        q2.insert(target); // 雙向BFS, 從0000與target同時擴散
        while (!q1.empty() || !q2.empty()) {
            unordered_set<string> tmp; // 用來記錄下一個q1
            for (string node : q1) {
                if (deads.count(node)) continue;
                if (visited.count(node)) continue;
                if (q2.count(node)) return res;
                
                visited.insert(node);


                for (int j = 0; j < 4; ++j) {
                    tmp.insert(plusOne(j, node));
                    tmp.insert(minusOne(j, node));
                }
            }

            ++res;

            q1 = q2; // 在這裡交換, 下一次換q2擴散
            q2 = tmp;
        }

        return -1;
    }

    string plusOne(int i, string num) {
        num[i] = (num[i] == '9') ? '0' : (num[i] + 1);
        return num;
    }

    string minusOne(int i, string num) {
        num[i] = (num[i] == '0') ? '9' : (num[i] - 1);
        return num;
    }
};