package leetCode.java;

//Greedy O(n) O(1)
class Solution1899 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] merge = new int[3];
        for(int[] triplet : triplets){
            //選取所有符合條件的組
            if(triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]){
                merge[0] = Math.max(merge[0], triplet[0]);
                merge[1] = Math.max(merge[1], triplet[1]);
                merge[2] = Math.max(merge[2], triplet[2]);
            }
        }

        return merge[0] == target[0] && merge[1] == target[1] && merge[2] == target[2];
    }
}