package leetCode.java;

import java.util.*;

//Stack O(n) O(1)
class Solution735 {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> dq = new LinkedList<>();
        for(int ast : asteroids){
            boolean exist = true;
            while(exist && !dq.isEmpty() && dq.peek() > 0 && ast < 0){
                int sum = dq.peek() + ast;
                if(sum <= 0) dq.poll();
                if(sum >= 0) exist = false;
            }

            if(exist) dq.push(ast);
        }

        int[] res = new int[dq.size()];
        for(int i = dq.size() - 1; i>=0; i--)
            res[i] = dq.poll();

        return res;
    }
}