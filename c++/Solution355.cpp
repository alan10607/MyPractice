//Heap Twitter(), postTweet(), follow(), unfollow(): O(1) O(F + T), getNewsFeed(): O(f * t * log(ft)) O(1)
//F = foll.size(), T = tweet.size(), f表示該追蹤人的被追人, t表示被追人的文章數, getNewsFeed()的priority_queue固定為10故空間為O(1)
class Twitter {//Solution355
public:
    unordered_map<int, unordered_set<int>> friends; // <追蹤者, 被追蹤者, ...>
    unordered_map<int, vector<pair<int, int>>> tweets; // <發文人, <<發文時間, 文章>, ...>>
    int time = 0;

    Twitter() {}

    void postTweet(int userId, int tweetId) {
        tweets[userId].push_back({time++, tweetId});
        follow(userId, userId); // 發文當下要追蹤自己
    }

    vector<int> getNewsFeed(int userId) {
        // 依照時間由小到大, 超過10就從最小的pop
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        for (int followee : friends[userId]) {
            for (pair<int, int> p : tweets[followee]) {
                pq.push(p);
                if (pq.size() > 10) {
                    pq.pop();
                }
            }
        }

        vector<int> res(pq.size());
        for (int i = res.size() - 1; i >= 0; --i) {
            res[i] = pq.top().second;
            pq.pop();
        }
        return res;
    }

    void follow(int followerId, int followeeId) {
        friends[followerId].insert(followeeId);
    }

    void unfollow(int followerId, int followeeId) {
        friends[followerId].erase(followeeId);
    }
};