package leetCode.java;

//Fast & Slow pointer Floyd Cycle Detection Algorithm O(n) O(1)
class Solution287 {
    public int findDuplicate(int[] nums) {
        //must solve without modifying the array nums and uses only constant extra space
        int fast = 0;
        int slow = 0;
        do{//至少先做一次避免一開始就出去
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while(fast != slow);

        int start = 0;
        while(start != slow){
            start = nums[start];
            slow = nums[slow];
        }
        return start;
    }
}
/* nums = [3,1,3,4,2]

index 	num
0		3
1		1
2		3
3		4
4		2

第一次while:
0 -> 3 -> 4 -> 2
     ^---------|

     vs   vf
0 -> 3 -> 4 -> 2
     ^---------|

     vf   vs
0 -> 3 -> 4 -> 2
     ^---------|

               vs=f
0 -> 3 -> 4 -> 2
     ^---------|

第二次while:
va             vb
0 -> 3 -> 4 -> 2
     ^---------|

     va=b
0 -> 3 -> 4 -> 2
     ^---------|

=> return 3
*/