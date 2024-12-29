//NestedIterator(): O(n) O(n), n為所有數字個數, next(): O(1) O(1), hasNext(): O(1) O(1)
class NestedIterator {//Solution341
public:
    queue<int> q;

    NestedIterator(vector<NestedInteger>& nestedList) {
        faltten(nestedList);
    }

    void faltten(vector<NestedInteger>& nestedInteger) {
        for (NestedInteger ni : nestedInteger) {
            if (ni.isInteger()) {
                q.push(ni.getInteger());
            } else {
                faltten(ni.getList());
            }
        }
    }

    int next() { // 題目是直接用next查現在
        int cur = q.front();
        q.pop();
        return cur;
    }

    bool hasNext() {
        return !q.empty();
    }
};
/* 題目有給幾個方法使用
bool isInteger() const;
int getInteger() const;
const vector<NestedInteger> &getList() const;
*/