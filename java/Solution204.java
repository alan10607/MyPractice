package leetCode.java;

import java.util.*;

//Sieve of Eratosthenes O(n * loglogn) O(n)
class Solution204 {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int res = 0;
        for(int i = 2; i < n; ++i){
            if(!isPrime[i]) continue;

            ++res;
            for(int j = 2; i * j < n; ++j)
                isPrime[i * j] = false;
        }

        return res;
    }
}