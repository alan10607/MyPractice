package leetCode.java;

import java.util.*;

//Heap Twitter(), postTweet(), follow(), unfollow(): O(1) O(F + P), getNewsFeed(): O(f)) O(fp)
//F = foll.size(), P = post.size(), f表示該追蹤人的被追人, p表示被追人的文章數
class Twitter {//Solution355
    Map<Integer, Set<Integer>> following = new HashMap<>(); //<追蹤人, <被追人, ...>>
    Map<Integer, List<int[]>> tweets = new HashMap<>(); // <發文人, <[文章, 時間], ...>>
    int time = 0;

    public Twitter() {
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(new int[]{tweetId, time++});
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> followeeIds = new ArrayList<>();
        followeeIds.add(userId); // 在這裡放入自己
        if (following.containsKey(userId)) {
            followeeIds.addAll(following.get(userId));
        }

        // K-way merge的方法, tweets的List<int[]>>裡面已經是順序排列, 所以找到每一個list的最後尾就可以
        // max heap, 依照時間由大到小, 超過10就從最小的poll
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]); // [文章, 時間, 發文人, index]
        for (int authorId : followeeIds) {
            if (!tweets.containsKey(authorId)) {
                continue;
            }

            List<int[]> authorTweets = tweets.get(authorId);
            int lastIndex = authorTweets.size() - 1;
            int[] tweet = authorTweets.get(lastIndex);
            pq.offer(new int[]{tweet[0], tweet[1], authorId, lastIndex}); // [文章, 時間, 發文人, index]
        }

        // 找出10個最新的
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty() && res.size() < 10) {
            int[] tweet = pq.poll();
            int tweetId = tweet[0];
            int authorId = tweet[2];
            int index = tweet[3];
            res.add(tweetId);
            if (index > 0) {
                int[] nextTweet = tweets.get(authorId).get(index - 1);
                pq.offer(new int[]{nextTweet[0], nextTweet[1], authorId, index - 1});
            }
        }
        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}