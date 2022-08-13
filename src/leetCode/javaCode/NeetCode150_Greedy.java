package leetCode.javaCode;

import java.util.*;

/**
 *  Greedy
 */
public class NeetCode150_Greedy {

    //BFS
    class Solution45 {
        //Time Complexity: O(n), Space Complexity: O(1), n = nums.length
        public int jump(int[] nums) {
            int rightMost = 0;//目前能走到的最右邊, 先設為0
            int temp = 0;//用來記錄到rightMost之前的最大可能
            int res = 0;

            for(int i=0; i<nums.length - 1; i++){
                temp = Math.max(temp, i + nums[i]);

                if(i == rightMost){
                    res++;
                    rightMost = temp;//更新下一次BFS的範圍
                    if(rightMost >= nums.length - 1)
                        break;
                }
            }
            return res;
        }

        //Time Complexity: O(n^2), Space Complexity: O(n), n = nums.length
        public int jump2(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, n);//預設為n, 不可能需要到n步
            dp[0] = 0;
            for(int i=1; i <= n - 1; i++){
                for(int j = i - 1; j>=0; j--){
                    if(j + nums[j] >= i)
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
            return dp[n - 1] == n ? -1 : dp[n - 1];
        }
    }
    /* nums = [2,3,1,1,4]
    用BFS來想:

                n[0]
        n[1]            n[2]
    n[2] n[3] n[4]      n[3]

    */

    //Time Complexity: O(n), Space Complexity: O(1)
    //Greedy
    class Solution134 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            //必要條件為sum(gas) >= sum(cost)
            int sum = 0;
            for(int i=0; i<gas.length; i++)
                sum += (gas[i] - cost[i]);

            if(sum < 0) return -1;

            int start = 0;//從第一個點開始嘗試
            int tank = 0;//代表目前汽油
            //之所以可以跳到下一個直接計算, 因為已知sum(gas) >= sum(cost)
            //加總到底之後還是tank>0, 就可以保證start之前的部分, 就算加總上來也一定>0(即回到原點)
            for(int i=0; i<gas.length; i++){
                tank += (gas[i] - cost[i]);
                if(tank < 0){//<0代表無法到達該點
                    start = i + 1;//直接嘗試下一個點
                    tank = 0;//重新計算
                }
            }
            return start;
        }
    }
    /*
    gas  = [ 1, 2, 3, 4, 5]
    cost = [ 3, 4, 5, 1, 2]
    diff = [-2,-2,-2, 3, 3]
                      ^
    */

    //Time Complexity: O(n logn), Space Complexity: O(n)
    //Greedy
    class Solution846 {
        public boolean isNStraightHand(int[] hand, int groupSize) {
            Arrays.sort(hand);//排列成順序
            Map<Integer, Integer> counts = new TreeMap<>();
            for(int num : hand)
                counts.put(num, counts.getOrDefault(num, 0) + 1);

            //有重複的牌也會重複跑到
            for(int i=0; i<hand.length; i++){
                int num = hand[i];
                if(counts.get(num) == 0)
                    continue;

                //開始丟牌
                for(int j=num; j < num + groupSize; j++){
                    //已經沒順的牌了
                    if(!counts.containsKey(j) || counts.get(j) == 0)
                        return false;

                    counts.put(j, counts.get(j) - 1);
                }
            }
            return true;
        }
    }
    /*
    hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
    sort =>
    [1,2,2,3,3,4,6,7,8]
    [2,3,4,6,7,8]
    [6,7,8]
    []

    num count
    1   1
    2   2
    3   2
    4   1
    5   0
    6   1
    7   1
    8   1
    */

    //Time Complexity: O(n), Space Complexity: O(1)
    //Greedy
    class Solution1899 {
        public boolean mergeTriplets(int[][] triplets, int[] target) {
            //直接找所有小於等於目標的值
            int[] res = new int[3];
            for(int[] triplet : triplets){
                if(triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]){
                    res[0] = Math.max(res[0], triplet[0]);
                    res[1] = Math.max(res[1], triplet[1]);
                    res[2] = Math.max(res[2], triplet[2]);
                }
            }

            for(int i=0; i<3; i++){
                if(res[i] != target[i])
                    return false;
            }

            return true;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(Z), Z = 字符集 = 26
    //Greedy
    class Solution763 {
        public List<Integer> partitionLabels(String s) {
            //分割最多可能的字段, 且每個分割要存在最多重複字母
            Map<Character, Integer> lastIndex = new HashMap<>();
            for(int i=0; i<s.length(); i++)
                lastIndex.put(s.charAt(i), i);

            List<Integer> res = new ArrayList<>();
            int l = 0;
            int r = 0;
            for(int i=0; i<s.length(); i++){
                r = Math.max(r, lastIndex.get(s.charAt(i)));
                if(i == r){//走到這一段的最後了
                    res.add(r - l + 1);
                    l = r + 1;
                }
            }
            return res;
        }
    }
    /*
    ababcbacadefegdehijhklij
        ----   - -  |   --
     -----    -----| ------
    |-------||-----   -----|
    */

    //Time Complexity: O(n), Space Complexity: O(1)
    //Greedy
    class Solution678 {
        public boolean checkValidString(String s) {
            int leftMax = 0;//最多可能有多少'('
            int leftMin = 0;//最少可能有多少'('
            for(int i=0; i<s.length(); i++){
                char ch = s.charAt(i);
                if(ch == '('){
                    leftMax++;
                    leftMin++;
                }else if(ch == ')'){
                    leftMax--;
                    leftMin--;
                }else{//ch == '*'
                    leftMax++;
                    leftMin--;
                }

                if(leftMax < 0) return false;//不允許')'多於'('
                if(leftMin < 0) leftMin = 0;//多餘的')'轉成空白
            }

            return leftMin == 0;//若不為0, 則代表有多餘的'('未匹配
        }
    }

}