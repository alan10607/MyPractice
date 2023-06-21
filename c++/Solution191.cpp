//Bit O(n) O(1), n = 32
class Solution191 {
public:
    int hammingWeight(uint32_t n) {
        int res = 0;
        while(n != 0){
            res += (n & 1);
            n >>= 1;//已確定是unsigned int
        }
        return res;
    }
};