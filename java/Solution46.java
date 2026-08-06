package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n), 時間複雜度要進行(n!)次backtracking, 複製selected要O(n)
class Solution46 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtracking(new HashSet<>(), new ArrayList<>(), nums);
        return res;    
    }

    public void backtracking(Set<Integer> visited, List<Integer> selected, int[] nums) {
        if (selected.size() == nums.length) {
            res.add(new ArrayList<>(selected));
            return;
        }

        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }

            visited.add(num);
            selected.add(num);
            backtracking(visited, selected, nums);
            selected.remove(selected.size() - 1);
            visited.remove(num);
        }
    }
}

/*
                        []
        1               2               3
    12      13      21      23      31      32
    123     132     213     231     312     321 

*/