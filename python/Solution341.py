# Solution341 NestedIterator(): O(n) O(n), next(): O(1) O(1), hasNext(): O(n) O(1), n為所有數字個數
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.dq = collections.deque(nestedList)
    
    def next(self) -> int:
        # 題目的pseudocode已經表明會先hasNext()再next()
        return self.dq.popleft().getInteger()
        
    
    def hasNext(self) -> bool:
        # 直接在這裡展開, lazy evaluation比較好
        while self.dq and not self.dq[0].isInteger():
            firstNested = self.dq.popleft().getList()
            for i in range(len(firstNested) - 1, -1, -1):
                self.dq.appendleft(firstNested[i])
        
        return bool(self.dq)   
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())