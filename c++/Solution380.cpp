//RandomizedSet(), insert(), remove(), getRandom(): O(1) O(n), n為儲存的set數量
class RandomizedSet {//Solution380
public:
    vector<int> nums;
    unordered_map<int, int> m;//<值, nums中的位置>

    RandomizedSet() {
    }

    bool insert(int val) {
        if(m.count(val)) return false;

        nums.push_back(val);
        m[val] = nums.size() - 1;
        return true;
    }

    bool remove(int val) {
        if(!m.count(val)) return false;

        int last = nums.back();
        nums[m[val]] = last;//用最後一個取代目標值的位置
        nums.pop_back();
        m[last] = m[val];
        m.erase(val);//map也交換後移除
        return true;
    }

    int getRandom() {
        int r = rand() % nums.size();
        return nums[r];
    }
};