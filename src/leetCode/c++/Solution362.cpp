//HitCounter(), hit(): O(1) O(n), getHits(): O(m), O(n), n為儲存的timestamp數量, m為5分鐘以前的timestamp數量
class HitCounter {//Solution362
public:
    queue<int> time;

    HitCounter() {
    }

    void hit(int timestamp) {
        time.push(timestamp);
    }

    int getHits(int timestamp) {
        while(!time.empty() && time.front() + 300 <= timestamp)
            time.pop();//out 5 mins

        return time.size();
    }
};