//Stack O(n) O(n)
class Solution32 {
public:
    int longestValidParentheses(string s) {
        stack<int> st;//<'('的位置
        st.push(-1);//用來當作最底端
        int res = 0;
        for(int i=0; i<s.length(); ++i){
            if(s[i] == '('){
                st.push(i);
            }else{//s[i] == ')'
                st.pop();
                if(st.empty()){
                    st.push(i);//繼續累積, 但不可能存在兩個以上')'在最左
                }else{
                    res = max(res, i - st.top());//匹配到
                }
            }
        }
        return res;
    }
};
/*
    (   )   (   (   (   )   )
-1
    0---1  len=1-(-1)=2
            2
                3-----------6  len=6-2=4
                    4---5  len=5-3=2

*/