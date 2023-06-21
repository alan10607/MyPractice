//Sieve of Eratosthenes O(n * loglogn) O(n)
class Solution204 {
public:
    int countPrimes(int n) {
        vector<bool> isPrime(n, true);//0,1不使用
        int res = 0;
        for(int i=2; i<n; ++i){//最小質數2開始
            if(!isPrime[i]) continue;

            ++res;
            for(int j=2; i * j < n; ++j){//最小倍數2開始, ex: 6,9,...
                isPrime[i * j] = false;
            }
        }

        return res;
    }
};