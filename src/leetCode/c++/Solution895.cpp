//895 FreqStack(), push(), pop(): O(1) O(n)
class FreqStack {//Solution895
public:
    map<int, int> cnt;//<數字, 出現次數>
    map<int, stack<int>> freqSt;//<出現次數, <數字1, 數字2, ...>, ...>
    int cntMax = 0;

    FreqStack() {
    }

    void push(int val) {
        freqSt[++cnt[val]].push(val);
        cntMax = max(cntMax, cnt[val]);
    }

    int pop() {
        int res = freqSt[cntMax].top();//若取出stack, 記得要address of;
        freqSt[cntMax].pop();

        --cnt[res];//記得更新計數

        if(freqSt[cntMax].empty())
            --cntMax;

        return res;
    }
};
/*
5,7,5,7,4,5

feqSt={1,{5}}
feqSt={1,{7,5}}
feqSt={1,{7,5}},{2,{5}}
feqSt={1,{7,5}},{2,{7,5}}
feqSt={1,{4,7,5}},{2,{7,5}}
feqSt={1,{4,7,5}},{2,{7,5}},{3,{5}}

*/