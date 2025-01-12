// Stack O(n) O(n)
class Solution503 {
public:
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);
        stack<int> st;
        for (int i = n * 2 - 1; i >= 0; --i) {
            int j = i % n; // 透過對array length取模來兩次循環
            while (!st.empty() && st.top() <= nums[j]) {
                st.pop();
            }
            res[j] = st.empty() ? -1 : st.top();
            st.push(nums[j]);
        }
        return res;
    }
};