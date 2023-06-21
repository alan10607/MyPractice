//Stack O(n) O(n)
class Solution84 {
public:
    int largestRectangleArea(vector<int>& heights) {
        stack<int> st;//<位置>
        heights.push_back(0);//迴圈結束前用來解決剩餘柱子
        int res;
        for(int i=0; i<heights.size(); ++i){
            while(!st.empty() && heights[st.top()] > heights[i]){
                int posi = st.top(); st.pop();
                int area = heights[posi] * (st.empty() ? i : i - st.top() - 1);//i - 下一個較矮位置 - 1
                res = max(res, area);
            }
            st.push(i);
        }
        return res;
    }
};
/* 保持遞增數列, heights = [1,5,2,4]
            
    *
    *       *
    *       *
    *   *   *
*   *   *   *
s={0}
s={1,0}
遇到2 s.pop() area=h[1]*(2-1-1)=5*1=5
s={2,0}
s={3,2,0}
遇到4 s.pop() area=h[3]*(4-2-1)=4*1=4
s={2,0}
s.pop() area=h[2]*(4-0-1)=2*3=6
s={0}
s.pop() area=h[0]*(4)=1*4=4
s={4}

res=6
*/