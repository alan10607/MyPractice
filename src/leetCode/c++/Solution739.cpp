//Stack O(n) O(n)
class Solution739 {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        stack<int> st;//保持遞減氣溫位置
        int i = 0;
        vector<int> res(temperatures.size(), 0);
        for(int i=0; i<temperatures.size(); ++i){
            while(!st.empty() && temperatures[st.top()] < temperatures[i]){
                int posi = st.top(); st.pop();
                res[posi] = i - posi;
            }
            st.push(i);
        }
        return res;
    }
};