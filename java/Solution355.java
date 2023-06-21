package leetCode.java;

import java.util.*;

//Heap Twitter(), postTweet(), follow(), unfollow(): O(1) O(F + P), getNewsFeed(): O(f * p * log(fp)) O(fp)
//F = foll.size(), P = post.size(), f表示該追蹤人的被追人, p表示被追人的文章數
class Twitter {//Solution355
    public int time;
    public Map<Integer, Set<Integer>> foll;//<追蹤人, <被追人, ...>>
    public Map<Integer, List<int[]>> post;//<發文人, <[時間, 文章], ...>>

    public Twitter() {
        time = 0;
        foll = new HashMap<>();
        post = new HashMap<>();

    }

    public void postTweet(int userId, int tweetId) {
        if(!post.containsKey(userId))
            post.put(userId, new ArrayList<int[]>());

        post.get(userId).add(new int[]{time++, tweetId});

        //如果還沒追蹤自己, 預設要追蹤自己
        follow(userId, userId);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);//<時間, 文章>
        if(foll.containsKey(userId)){
            for(int followeeId : foll.get(userId)){
                if(post.containsKey(followeeId)){
                    for(int[] tweet : post.get(followeeId)){
                        pq.offer(tweet);
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        //retrieves the 10 most recent tweet
        for(int i=0; i < 10 && !pq.isEmpty(); i++)
            res.add(pq.poll()[1]);

        return res;
    }

    public void follow(int followerId, int followeeId) {
        if(!foll.containsKey(followerId))
            foll.put(followerId, new HashSet<Integer>());

        foll.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if(foll.containsKey(followerId))
            foll.get(followerId).remove(followeeId);//return boolean
    }
}