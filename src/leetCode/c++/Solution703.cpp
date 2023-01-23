//Heap KthLargest(): O(nlogk) O(k) add(): O(logk) O(k), n = nums.size()
class KthLargest {//Solution703
public:
    priority_queue<int, vector<int>, greater<int>> pq;//小到大
    int k;

    KthLargest(int k, vector<int>& nums) {
        this->k = k;
        for(int num : nums)
            add(num);
    }

    int add(int val) {
        pq.push(val);
        if(pq.size() > k) pq.pop();
        return pq.top();
    }
};