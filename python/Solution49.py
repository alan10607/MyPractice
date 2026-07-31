# O(nk) O(nZ), n = strs.size(), k為最大的str長度, Z = 26
class Solution49:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        memo = defaultdict(list)
        for s in strs:
            count = [0] * 26
            for c in s:
                count[ord(c) - ord('a')] += 1

            memo[tuple(count)].append(s) # 透過tuple建立hash key
        
        return list(memo.values())