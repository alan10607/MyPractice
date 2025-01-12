// Stack O(n + m) O(n + m), n = nums1.size(), m = nums2.size()
class Solution496 {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> memo; // <num, num在nums2中, 右邊第一個比自己大的數>
        stack<int> st;
        for (int i = nums2.size() - 1; i >= 0; --i) {
            while (!st.empty() && st.top() < nums2[i]) {
                st.pop();
            }
            memo[nums2[i]] = st.empty() ? -1 : st.top();
            st.push(nums2[i]);
        }

        vector<int> res;
        for (int num : nums1) {
            res.push_back(memo[num]);
        }
        return res;
    }
};