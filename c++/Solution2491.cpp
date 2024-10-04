//O(n) O(n)
class Solution2491 {
public:
    long long dividePlayers(vector<int>& skill) {
        int sum = accumulate(skill.begin(), skill.end(), 0);

        int group_size = skill.size() / 2;
        if (sum % group_size != 0)
            return -1; // not divisible

        int target = sum / group_size;
        int l = 0, r = skill.size() - 1;
        unordered_map<int, int> memo;
        long long res = 0;
        for (int s : skill) {
            if (memo.count(target - s)) {
                res += s * (target - s);
                --memo[target - s];
                if (memo[target - s] == 0) {
                    memo.erase(target - s);
                }
            } else {
                ++memo[s];
            }
        }

        return memo.empty() ? res : -1;
    }
};