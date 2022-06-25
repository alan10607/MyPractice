package leetCode.javaCode;

import java.util.*;

/**
 *  Heap / Priority Queue
 */
public class NeetCode150_Heap {

    //Time Complexity: 初始化: O(n logk), add(): (logk), Space Complexity: O(k), n = nums.length
    //Heap
    class Solution703 {
        class KthLargest {
            public PriorityQueue<Integer> pq;
            public int max;

            public KthLargest(int k, int[] nums) {
                //not the kth distinct element.
                pq = new PriorityQueue<>();
                max = k;
                for(int num : nums)
                    add(num);
            }

            public int add(int val) {
                pq.offer(val);//O(logn)
                if(pq.size() > max)
                    pq.poll();

                return pq.peek();
            }
            /*
            KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
            kthLargest.add(3);   // return 4 [4, 5, 8]
            kthLargest.add(5);   // return 5 [5, 5, 8]
            kthLargest.add(10);  // return 5 [5, 8, 10]
            kthLargest.add(9);   // return 8 [8, 9, 10]
            kthLargest.add(4);   // return 8 [8, 9, 10]
            */
        }
    }

    //Time Complexity: 初始化: O(n logn), Space Complexity: O(n), n = stones.length
    //Heap
    class Solution1046 {
        public int lastStoneWeight(int[] stones) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);//大到小
            for(int stone : stones)
                pq.offer(stone);//O(logn)

            while(pq.size() > 1){
                //the heaviest two stones have weights x and y with x <= y
                int y = pq.poll();
                int x = pq.poll();

                if(y - x > 0)
                    pq.offer(y - x);
            }
            return pq.isEmpty() ? 0 : pq.peek();
        }
    }

    //Time Complexity: 初始化: O(T + z)), Space Complexity: O(z), T = tasks.length, z為tasks的種類數, 本題最多為26
    //Heap
    class Solution973 {
        public int[][] kClosest(int[][] points, int k) {
            PriorityQueue<int[]> heap = new PriorityQueue<>((m1, m2) -> m1[0] - m2[0]);//[length, x, y]

            //double Math.pow(a, b)
            //double Math.sqrt(a, b)
            //放到heap中
            for(int point[] : points)
                heap.offer(new int[]{point[0] * point[0] + point[1] * point[1], point[0], point[1]});

            //再拿出來
            int[][] res = new int[k][2];
            for(int i=0; i<k; i++){
                int[] temp = heap.poll();
                res[i][0] = temp[1];
                res[i][1] = temp[2];
            }
            return res;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(logn)
    //Heap
    class Solution215 {
        public int findKthLargest(int[] nums, int k) {
            //透過quicksort, 但只排一邊
            return quickSelect(nums, 0, nums.length - 1, k - 1);
        }

        public int quickSelect(int[] nums, int start, int end, int k){
            int pivot = nums[start];//左基準點
            int l = start + 1;
            int r = end;
            while(l <= r){
                //等於的也要往前, 否則會卡在同一個位子上
                if(nums[l] >= pivot){
                    l++;
                }else if(nums[r] <= pivot){
                    r--;
                }else{
                    swap(nums, l , r);
                }
            }

            swap(nums, start, r);//交換基準點

            if(k == r){
                return nums[r];
            }else if(k < r){
                return quickSelect(nums, start, r - 1, k);
            }else{//k > r
                return quickSelect(nums, r + 1, end, k);
            }
        }

        public void swap(int[] nums, int a, int b){
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
        /* 從大排到小, l遇到比pivot大跳過, r遇到比pivot小跳過
        find k = 2, left pivot交換r
        3 2 1 5 6 4
        p l       r

        3 4 1 5 6 2
        p   l   r

        3 4 6 5 1 2
        p     r l   -> swap(p, r)

        5 4 6 3 1 2
        ----- - ---

        5 4 6
        p l r

        5 6 4
        p r l -> swap(p, r)

        6 5 4
        - - -
        return 5


        find k = 2, right pivot交換l
        3 2 1 5 6 4
        l       r p

        6 2 1 5 3 4
          l   r   p

        6 5 1 2 3 4
          r l     p -> swap(p, l)

        6 5 4 2 3 1
        --- - -----

        6 5
        r p -> swap(p, l)
          l

        6 5
        - -
        return 5
        */
    }

    //Time Complexity: 初始化: O(T + z)), Space Complexity: O(z), T = tasks.length, z為tasks的種類數, 本題最多為26
    //Heap
    class Solution621 {
        public int leastInterval(char[] tasks, int n) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);//由大排到小
            Map<Character, Integer> counts = new HashMap<>();
            for(char task : tasks)
                counts.put(task, counts.getOrDefault(task, 0) + 1);

            for(Map.Entry entry : counts.entrySet())
                pq.offer((Integer) entry.getValue());

            int period = 1 + n;//加上n個CD
            int res = 0;
            while(!pq.isEmpty()){
                //開始執行第一條, 由多的開始
                List<Integer> temp = new ArrayList<>();
                int time = 0;
                for(int i=0; i<period && !pq.isEmpty(); i++){
                    time++;
                    int remain = pq.poll() - 1;
                    if(remain > 0)
                        temp.add(remain);
                }

                //加回主queue
                for(int remain : temp)
                    pq.offer(remain);

                //如果pq已空代表是最後一行, 否則加入整個period
                res += pq.isEmpty() ? time : period;

            }
            return res;
        }
        /*
        tasks = ["A","A","A","B","B","C"], n = 2

        - - -
        A B C
        A B x
        A
        res = 7
        */
    }

    //Time Complexity: 初始化, postTweet(), follow(), unfollow(): O(1), getNewsFeed(): O()
    //Space Complexity: O(z), T = tasks.length, z為tasks的種類數, 本題最多為26
    //Heap + HashMap
    class Solution355 {
        class Twitter {
            Map<Integer, Set<Integer>> followMap;//<追蹤人, [被追蹤人, ...]>
            Map<Integer, List<int[]>> postMap;//<發文人, [[序, 文章], ...]>
            int count = 0;//序

            public Twitter() {
                followMap = new HashMap<>();
                postMap = new HashMap<>();
            }

            public void postTweet(int userId, int tweetId) {
                if (!followMap.containsKey(userId))
                    followMap.put(userId, new HashSet<Integer>());

                if (!postMap.containsKey(userId))
                    postMap.put(userId, new ArrayList<int[]>());

                followMap.get(userId).add(userId);//追蹤自己
                postMap.get(userId).add(new int[]{count, tweetId});//記錄發文
                count++;
            }

            public List<Integer> getNewsFeed(int userId) {
                List<Integer> res = new ArrayList<>();
                if (!followMap.containsKey(userId)) return res;

                //[時間, 文章], 依時間大排到小
                PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i2[0] - i1[0]);

                //把所有文章放到pq, user -> followees -> posts
                Set<Integer> followees = followMap.get(userId);
                for (Integer followee : followees) {
                    if (postMap.containsKey(followee)) {
                        List<int[]> posts = postMap.get(followee);
                        for (int[] post : posts) {
                            pq.offer(post);
                            //由於PriorityQueue沒有pollLast, 這裡省略poll改到轉成List時限制10個
                        }
                    }
                }

                //pq to List
                //Retrieves the 10 most recent tweet IDs
                for (int i = 0; i < 10 && !pq.isEmpty(); i++)
                    res.add(pq.poll()[1]);

                return res;
            }

            public void follow(int followerId, int followeeId) {
                if (!followMap.containsKey(followerId))
                    followMap.put(followerId, new HashSet<Integer>());

                followMap.get(followerId).add(followeeId);
            }

            public void unfollow(int followerId, int followeeId) {
                //透過Set找到移除誰
                if (followMap.containsKey(followerId))
                    followMap.get(followerId).remove(followeeId);
            }
        }
    }

}