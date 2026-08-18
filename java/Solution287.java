package leetCode.java;

//Fast & Slow Pointer Floyd Cycle Detection Algorithm O(n) O(1)
class Solution287 {
    public int findDuplicate(int[] nums) {
        // Without modifying nums and using only O(1) space
        int fast = 0, slow = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (fast == slow) {
                break;
            }
        }

        int start = 0;
        while (start != slow) {
            start = nums[start];
            slow = nums[slow];
        }
        return start;
    }
}

/* 
題目說所有數字都在區間[1,n], 但長度是n + 1
即所有num皆<nums.length, 所以可以用環概念

ex: nums = [3,1,3,4,2]

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