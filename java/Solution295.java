package leetCode.java;

import java.util.*;

//Heap MedianFinder(), findMedian(): O(1) O(n), addNum(): O(logn) O(n)
class MedianFinder {//Solution295
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    public MedianFinder() {
    }
    
    public void addNum(int num) {
        // 先丟到min, 然後從min再選一個到max, 混合
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        if (minHeap.size() < maxHeap.size()) { // 保持minHeap較多
            minHeap.offer(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.peek(); // 保持minHeap較多
        }
    }
}

/*

      min heap      max heap
        ____           ___    
      ________          _
    ____________        


*/