# O(n) O(n)
class Solution1:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        memo = {}
        for i, num in enumerate(nums):
            if target - num in memo :
                return [i, memo[target - num]]
            else:
                memo[num] = i

        return []
