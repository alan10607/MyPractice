//Heap Twitter(), postTweet(), follow(), unfollow(): O(1) O(F + T), getNewsFeed(): O(f * t * log(ft)) O(1)
//F = foll.size(), T = tweet.size(), f表示該追蹤人的被追人, t表示被追人的文章數, getNewsFeed()的priority_queue固定為10故空間為O(1)
class Twitter {//Solution355
public:
    unordered_map<int, unordered_set<int>> foll;//<追蹤人, 被追人>
    unordered_map<int, vector<pair<int, int>>> tweet;//<發文人, <<發文時間, 文章>, ...>>
    int time;

    Twitter() {
        time = 0;
    }

    void postTweet(int userId, int tweetId) {
        tweet[userId].push_back({time++, tweetId});//insert map
        follow(userId, userId);//發文當下要追蹤自己
    }

    vector<int> getNewsFeed(int userId) {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> top10;//<發文時間, 文章>, 依照時間小排到大
        for(auto followeeId : foll[userId]){
            for(auto it : tweet[followeeId]){
                top10.push(it);
                if(top10.size() > 10)
                    top10.pop();
            }
        }

        vector<int> res(top10.size());
        for(int i = res.size() - 1; i >= 0; --i){//reverse pq
            res[i] = top10.top().second;
            top10.pop();
        }
        return res;
    }

    void follow(int followerId, int followeeId) {
        foll[followerId].insert(followeeId);
    }

    void unfollow(int followerId, int followeeId) {
        foll[followerId].erase(followeeId);
    }
};