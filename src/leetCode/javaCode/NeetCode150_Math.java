package leetCode.javaCode;

import java.util.*;

/**
 *  Intervals
 *  Math & Geometry
 *  Bit Manipulation
 */
public class NeetCode150_Math {

    //Time Complexity: O(n logn + q logq), Space Complexity: O(n + q), n = intervals.length, q = queries.length, n, p都要排序
    //Intervals + Heap
    class Solution1581 {
        public int[] minInterval(int[][] intervals, int[] queries) {
            //都排序好然後依序模擬
            int[][] qIndex = new int[queries.length][2];//[[數值, index], ...]
            for(int i=0; i<queries.length; i++){
                //紀錄原本queries的位置, 之後排序就會不一樣了
                qIndex[i][0] = queries[i];
                qIndex[i][1] = i;
            }

            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//按照左界排列
            Arrays.sort(qIndex, (a, b) -> a[0] - b[0]);//按照長度排列
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);//<[長度, end], ...>
            int[] res = new int[queries.length];
            int p = 0;//第幾個intervals
            for(int i=0; i<qIndex.length; i++){
                //範圍內的interval加入heap
                while(p < intervals.length && intervals[p][0] <= qIndex[i][0]){
                    int len = intervals[p][1] - intervals[p][0] + 1;
                    heap.offer(new int[]{len, intervals[p][1]});
                    p++;
                }

                //範圍外的interval移出heap
                while(!heap.isEmpty() && heap.peek()[1] < qIndex[i][0])
                    heap.poll();

                int index = qIndex[i][1];//答案轉回到原本順序
                res[index] = heap.isEmpty() ? -1 : heap.peek()[0];//記得考慮沒有匹配到interval的情況
            }

            return res;
        }
    }
    /* intervals = [[1,8],[2,3],[2,5],[20,25]], queries = [2,5,19,22]

    queries=2, heap=[[2,3],[4,5],[8,8]], res=2
    queries=5, heap=[[4,5],[8,8]], res=4
    queries=19, heap=[], res=-1
    queries=22, heap=[[6,25]], res=6
    */

    //Time Complexity: O(logn), Space Complexity: O(1)
    //Fast & Slow Pointer
    class Solution202 {
        public boolean isHappy(int n) {
            //快慢指針
            int fast = n;
            int slow = n;
            while(true){
                fast = getNext(getNext(fast));
                slow = getNext(slow);
                if(fast == 1) break;
                if(fast == slow) return false;
            }
            return true;
        }

        public int getNext(int n){
            int powSum = 0;
            while(n > 0){
                int last = n % 10;
                powSum += last * last;
                n /= 10;
            }
            return powSum;
        }

        //Time Complexity: O(logn), Space Complexity: O(logn)
        public boolean isHappy2(int n) {
            //用HashSet
            Set<Integer> memo = new HashSet<>();//空間O(n)
            while(n != 1){
                int powSum = 0;

                while(n > 0){
                    int last = n % 10;
                    powSum += last * last;
                    n /= 10;
                }

                if(memo.contains(powSum)) return false;//重複

                memo.add(powSum);
                n = powSum;
            }
            return true;
        }
    }
    /* 個位數也會繼續
    失敗的例子:
    2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20
         ^------------------------------------------|
    */

    //Time Complexity: O(n), Space Complexity: O(1)
    //Simulation
    class Solution66 {
        public int[] plusOne(int[] digits) {
            int remain = 0;
            for(int i = digits.length - 1; i>=0; i--){
                if(digits[i] < 9){
                    digits[i]++;
                    return digits;
                }else{
                    digits[i] = 0;//進位
                }
            }

            //到這裡都沒return, 代表多1位
            int[] newDig = new int[digits.length + 1];
            newDig[0] = 1;
            for(int i=0; i<digits.length; i++)
                newDig[i + 1] = digits[i];

            return newDig;
        }
    }

    //Time Complexity: O(logn), Space Complexity: O(logn)
    //Simulation
    class Solution50 {
        public double myPow(double x, int n) {
            if(x == 1) return 1;
            //n為整數
            if(n > 1){
                //要考慮奇偶
                return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2); //O(logn)
            }else if(n == 1){
                return x;
            }else if(n == 0){
                return 1;
            }else{// n < 1
                //要考慮奇偶, 直接在這裡/2, 否則n=-2^31時直接-n會出界, 會-n=n(還是-2^31)
                return n % 2 == 0 ? myPow(1 / (x * x), -(n / 2)) : (1 / x) * myPow(1 / (x * x), - (n / 2));
            }
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(m + n)
    //Simulation
    class Solution43 {
        public String multiply(String num1, String num2) {
            //must not use any built-in BigInteger library or convert the inputs to integer directly
            if("0".equals(num1) || "0".equals(num2)) return "0";

            int[] product = new int[num1.length() + num2.length()];
            for(int i = num1.length() - 1; i>=0; i--){
                for(int j = num2.length() - 1; j>=0; j--){
                    int a = num1.charAt(i) - '0';//不能直接轉成數字, 但可以利用char
                    int b = num2.charAt(j) - '0';
                    int digit = a * b + product[i + j + 1];//最大兩位數
                    //剛好會是i + j + 1, ex: 99*99=9801, (num1.len + num2.len - 1) = (i + j + 1)
                    product[i + j + 1] = digit % 10;//尾數
                    product[i + j] += digit / 10;//進位
                }
            }

            //組回String
            StringBuffer res = new StringBuffer();
            boolean isStart = false;
            for(int i=0; i<product.length; i++){
                if(!isStart && product[i] != 0)//去掉開頭的0
                    isStart = true;

                if(isStart)
                    res.append(product[i]);
            }
            return res.toString();
        }
    }

    //Time Complexity: DetectSquares() & add(): O(1), count(): O(n), Space Complexity: O(1)
    //Simulation
    class Solution2013 {
        class DetectSquares {
            //An axis-aligned square(軸對齊正方形) is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis
            Map<Integer, Integer> counts;//<x y, 數量>, 記錄該點的出現次數
            //若不使hash,則建構成Map<Integer, Map<Integer, Integer>> counts, <x, <y, 數量>>

            public DetectSquares() {
                counts = new HashMap<Integer, Integer>();
            }

            public void add(int[] point) {
                int hash = hash(point[0], point[1]);
                counts.put(hash, counts.getOrDefault(hash, 0) + 1);
            }

            public int count(int[] point) {
                //先查看有沒有對角點, 再透過兩個點找出剩餘兩點
                int res = 0;
                int px = point[0];
                int py = point[1];
                for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                    int hash = entry.getKey();
                    int x = hash >>> 10;
                    int y = hash & 1023;//1023 = 1111111111
                    //查看是否有對角點
                    if (Math.abs(px - x) == Math.abs(py - y) && px != x && py != y) {
                        //查看是否有另外兩點
                        int hash1 = hash(px, y);
                        int hash2 = hash(x, py);
                        //出現次數為另外三個點的出現次數相乘
                        if (counts.containsKey(hash1) && counts.containsKey(hash2))
                            res += counts.get(hash) * counts.get(hash1) * counts.get(hash2);
                    }
                }
                return res;
            }

            private int hash(int x, int y) {
                return (x << 10) | y;//0 <= x, y <= 1000, 2^10 = 1024
            }
        }
    }
    /*
        (x, Py)		(Px, Py)



        (x, y)		(Px, y)
     */

    //Time Complexity: O(n), Space Complexity: O(1)
    //Binary
    class Solution136 {
        public int singleNumber(int[] nums) {
            //solution with a linear runtime complexity and use only constant extra space
            int xor = 0;
            for(int num : nums)
                xor ^= num;//相同的數xor為0

            return xor;
        }
    }

    // Time Complexity: O(n), Space Complexity: O(1), n = log(|x|) = x的十進位位數
    //Simulation
    class Solution7 {
        public int reverse(int x) {
            //if outside the signed 32-bit int range [-2^31, 2^31 - 1], then return 0
            //not allow to store 64-bit integers
            int res = 0;
            while(x != 0){
                int last = x % 10;//-123 % 10 = -3
                x /= 10;

                //判斷邊界, 超過或在邊界上的情況下, res*10+last都會出界
                //2^31-1=2147483647, 如果res = 214748364, 則必須last<7
                if(     res > Integer.MAX_VALUE / 10
                        || (res == Integer.MAX_VALUE / 10 && last >= Integer.MAX_VALUE % 10)
                        ||  res < Integer.MIN_VALUE / 10
                        || (res == Integer.MIN_VALUE / 10 && last <= Integer.MIN_VALUE % 10)){
                    return 0;
                }
                res = res * 10 + last;
            }
            return res;
        }
    }

}