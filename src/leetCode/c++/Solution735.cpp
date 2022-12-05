//Stack O(n) O(1)
class Solution735 {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> res;//用vector模擬stack
        for(int ast : asteroids){
            bool exist = true;
            while(exist && !res.empty() && res.back() > 0 && ast < 0){
                int sum = res.back() + ast;
                if(sum <= 0) res.pop_back();
                if(sum >= 0) exist = false;
            }

            if(exist) res.push_back(ast);
        }
        return res;
    }
};