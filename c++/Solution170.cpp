//add(), find(): O(1) O(n), n = cnt.size()
class TwoSum {//Solution170
public:
    unordered_map<int, int> cnt; // <數字, 出現次數>
    
    void add(int number) {
        ++cnt[number];
    }
    
    bool find(int value) {
        for(auto c : cnt) {
            int num1 = c.first;
            int num2 = value - c.first;
            if(   (num1 != num2 && cnt.count(num2)) // 一對數字不同, 確認是否存在另一對
               || (num1 == num2 && c.second > 1)) { // 一對數字相同, 確認是否存在兩個以上
                return true;
            }
        }
        return false;
    }
};