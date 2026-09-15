package leetCode.java;

import java.util.*;

//NestedIterator(): O(n) O(n), n為所有數字個數, next(): O(1) O(1), hasNext(): O(1) O(1)
public class NestedIterator implements Iterator<Integer> { // Solution341
    Deque<NestedInteger> dq;

    public Solution341(List<NestedInteger> nestedList) {
        this.dq = new ArrayDeque<>();
        for (NestedInteger ni : nestedList) {
            this.dq.offerLast(ni);
        }
    }

    @Override
    public Integer next() { // 題目的pseudocode已經表明會先hasNext()再next(), 可以直接假設first是integer
        return dq.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() { // 直接在這裡展開, lazy evaluation比較好
        while (!dq.isEmpty() && !dq.peek().isInteger()) {
            List<NestedInteger> subList = dq.pollFirst().getList();
            for (int i = subList.size() - 1; i >= 0; --i) {
                dq.offerFirst(subList.get(i)); // 從頭加入
            }
        }

        return !dq.isEmpty();
    }
}