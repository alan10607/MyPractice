//Greedy O(n) O(1)
class Solution32 {
public:
    int longestValidParentheses(string s) {
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); ++i) {
            s[i] == '(' ? ++left : ++right;
            if (left == right) {
                res = max(res, right * 2); // left=right, 相同數量*2
            } else if (left < right) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            s[i] == '(' ? ++left : ++right;
            if (left == right) {
                res = max(res, right * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
};
/* 不用空間的解法, 透過是貪心算法計算兩種括號的數量, left, right分別代表左右括號, i
代表index

ex:
0   1   2   3   4   5   6
(   )   (   (   )   (   )
由左往右-->
i=0, left=1, right=0
i=1, left=1, right=1, l=r成對, max+len=2
i=2, left=2, right=1
i=3, left=3, right=1
i=4, left=3, right=2
i=5, left=4, right=2
i=6, left=4, right=3

由右往左<--
i=6, left=0, right=1
i=5, left=1, right=1, l=r成對, max+len=2
i=4, left=1, right=2
i=3, left=2, right=2, l=r成對, max+len=4
i=2, left=3, right=2, l>r不合理要歸零, left=right=0
i=1, left=0, rigth=1
i=0, left=1, right=1, l=r成對, max+len=2
得res=4

可見需要兩次左右來訪,
左往右解決右括號較多的情況, ex: ())
右往左解決左括號較多的情況, ex: (()
*/


//Stack O(n) O(n)
class Solution32_2 {
public:
    int longestValidParentheses(string s) {
        int res = 0;
        stack<int> st;
        st.push(-1);//用來當作最左, 方便計算
        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == ')' && st.top() >= 0 && s[st.top()] == '(') { //判斷是否成對, 需注意st.top()=-1時要跳過
                st.pop();
                res = max(res, i - st.top());
            } else {
                st.push(i);
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

//Stack O(n) O(n)
class Solution32_3 {
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