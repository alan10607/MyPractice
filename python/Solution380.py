#Solution380 RandomizedSet(), insert(), remove(), getRandom(): O(1) O(n), n為儲存的set數量
class RandomizedSet:

    def __init__(self):
        self.values = []
        self.valueToIndex= {}

    def insert(self, val: int) -> bool:
        if val in self.valueToIndex:
            return False

        self.valueToIndex[val] = len(self.values) # 記錄下在list中的位子
        self.values.append(val)
        return True

    def remove(self, val: int) -> bool:
        if not val in self.valueToIndex:
            return False
        
        last = self.values[-1] 
        index = self.valueToIndex[val]
        self.values[index] = last # 直接用最後一個取代val
        self.values.pop()
        self.valueToIndex[last] = index # 移除val並更新last
        self.valueToIndex.pop(val)
        return True
        
    def getRandom(self) -> int:
        return random.choice(self.values)
        


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()