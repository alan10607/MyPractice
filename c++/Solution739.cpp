//Stack O(n) O(n)
class Solution739 {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        vector<int> res(temperatures.size());
        stack<int> st; // 放index, 保持遞減
        for (int i = temperatures.size() - 1; i >= 0; --i) {
            while (!st.empty() && temperatures[st.top()] < temperatures[i]) {
                st.pop();
            }
            res[i] = st.empty() ? 0 : (st.top() - i);
            st.push(i);
        }
        return res;
    }
};


class Solution739_2 {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        vector<int> res(temperatures.size());
        stack<int> st;
        for (int i = 0; i < temperatures.size(); ++i) {
            while (!st.empty() && temperatures[st.top()] < temperatures[i]) {
                res[st.top()] = i - st.top(); // 由前往後就變成要一直更新之前的位置
                st.pop();
            }
            st.push(i);
        }
        return res;
    }
};