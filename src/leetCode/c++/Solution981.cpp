//Binary Search TimeMap(), set(): O(1) O(n), get(): O(logn) O(n)
class TimeMap {//Solution981
public:
    unordered_map<string, map<int, string>> memo; 

    TimeMap() {        
    }
    
    void set(string key, string value, int timestamp) {
        //all the timestamps are strictly increasing
        memo[key].insert({timestamp, value});
    }
    
    string get(string key, int timestamp) {
        auto it = memo[key].upper_bound(timestamp);//auto=map<int, string>::iterator
        return it == memo[key].begin() ? "" : prev(it)->second;//往前找一個
    }
};
/*
map.upper_bound() 找出大於這個數值的最小值
map.lower_bound() 找出大於或等或等於這個數值的最小值

TreeMap.floorKey() 找出大於或等於這個數值的最小值
TreeMap.ceilingKey 找出小於或等於這個數值的最大值
*/