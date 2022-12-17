//Bit O(logn) O(1), n每次都除二即logn
class Solution190 {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t reverse = 0;
        for(int i=0; i < 32 && n != 0; ++i){
            reverse |= ((n & 1) << (31 - i));
            n >>= 1;
        }
        return reverse;
    }
};