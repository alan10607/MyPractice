package leetCode.java;

import java.util.*;

//Heap MedianFinder(), findMedian(): O(1) O(n), addNum(): O(logn) O(n)
class MedianFinder {//Solution295
    PriorityQueue<Integer> smallHeap;
    PriorityQueue<Integer> bigHeap;

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a, b) -> b - a);//大到小
        bigHeap = new PriorityQueue<>((a, b) -> a - b);//小到大
    }

    public void addNum(int num) {
        if(smallHeap.isEmpty() || num < smallHeap.peek()){//比基準直小
            smallHeap.offer(num);
        }else{
            bigHeap.offer(num);
        }

        if(smallHeap.size() > bigHeap.size() + 1){
            bigHeap.offer(smallHeap.poll());
        }else if(smallHeap.size() < bigHeap.size()){
            smallHeap.offer(bigHeap.poll());
        }
    }

    public double findMedian() {
        if(smallHeap.size() > bigHeap.size()){//奇數
            return smallHeap.peek();
        }else{//偶數
            return (smallHeap.peek() + bigHeap.peek()) / 2.0;
        }
    }
}