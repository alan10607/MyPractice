//O(n) O(n)
class Solution1 {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> memo;
        for(int i=0; i<nums.size(); ++i){
            if(memo.count(target - nums[i])){
                return {memo[target - nums[i]], i};
            }else{
                memo[nums[i]] = i;
            }
        }
        return {};
    }
};

/* 常用std 方法

# unordered_map
    unordered_map.insert({1, "One"})
    unordered_map.emplace(2, "Two"))
    unordered_map.erase(1)
    unordered_map[2] = "New Two"

    unordered_map[2]
    unordered_map.at(1)
    unordered_map.find(3)
    unordered_map.count(3)

# unordered_set
    unordered_set.insert(10)
    unordered_set.emplace(20)
    unordered_set.erase(10)

    unordered_set.find(20)
    unordered_set.count(10)
    unordered_set.size()

# vector
    vector.push_back(1)
    vector.insert(vector.begin(), 0)
    vector.erase(myVector.begin() + 1)
    vector.pop_back()

    vector[1]
    vector.at(1)
    vector.front()
    vector.back()
    vector.empty()
    vector.size()

# stack
    stack.push(1)
    stach.pop(1)

    stack.top()
    stack.empty()
    stack.size()

# queue
    queue.push(1)
    queue.pop()

    queue.front()
    queue.back()
    queue.empty()
    queue.size()

# deque
    deque.push_back(1)
    deque.push_front(0)
    deque.emplace(deque.begin() + 1, 5)
    deque.pop_front()
    deque.pop_back()

    deque.front()
    deque.back()
    deque.size()
    deque.empty()

# priority_queue
    priority_queue.push(10)
    priority_queue.pop()

    priority_queue.top()
    priority_queue.empty()
    priority_queue.size()
*/