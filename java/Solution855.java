package leetCode.java;

//Interval ExamRoom: O(1) O(1), seat(), leave(): O(logn) O(n), n為座位數
class ExamRoom { //Solution855
    TreeSet<int[]> intervals; // <[start, end]>
    Map<Integer, int[]> starts; // <start, [start, end]> 找左界
    Map<Integer, int[]> ends;   // <end, [start, end]>   找右界
    int n;

    public ExamRoom(int n) {
        this.starts = new HashMap<>();
        this.ends = new HashMap<>();
        this.n = n;

        this.intervals = new TreeSet<>((a, b) -> {
            int distA = getDist(a), distB = getDist(b);
            return (distA == distB) ? (a[0] - b[0]) : distB - distA;
        }); // 間隔大到小, 相同間距時start index小到小
        intervals.add(new int[]{-1, n}); // 代表區間(-1, n) 可被使用
    }

    public int getDist(int[] interval) {
        if (interval[0] == -1) { // 坐第一個位置
            return interval[1];
        } else if (interval[1] == n) { // 坐最後一個位置
            return n - 1 - interval[0];
        } else {
            return (interval[1] - interval[0]) / 2;
        }
    }

    public void addInterval(int[] interval) {
        intervals.add(interval);
        starts.put(interval[0], interval);
        ends.put(interval[1], interval);
    }

    public void removeInterval(int[] interval) {
        intervals.remove(interval); // TreeSet可以找到同一個, 但pq不行
        starts.remove(interval[0]);
        ends.remove(interval[1]);
    }
    
    public int seat() {
        int[] interval = intervals.first();
        int p = -1;

        if (interval[0] == -1) {
            p = 0;
        } else if (interval[1] == n) {
            p = n - 1;
        } else {
            p = (interval[0] + interval[1]) / 2;
        }

        removeInterval(interval);
        addInterval(new int[]{interval[0], p});
        addInterval(new int[]{p, interval[1]});
        return p;
    }
    
    public void leave(int p) {
        // 合併
        int[] right = starts.get(p);
        int[] left = ends.get(p);
        removeInterval(right);
        removeInterval(left);
        addInterval(new int[]{left[0], right[1]});
    }
}
/*
直接存入interval, 並以 間隔大到小, 相同間距時start index小到小

ex: n = 10


    0   1   2   3   4   5   6   7   8   9
                                            init, intervals=[-1,10]
    *                                       seat(), intervals=[0,10],[-1,0]
    *                                   *   seat(), intervals=[0,9],[-1,0],[9,10]
    *               *                   *   seat(), intervals=[0,4],[4,9],[-1,0],[9,10]
    *       *       *                   *   seat(), intervals=[4,9],[0,2],[2,4],[-1,0],[9,10]
    *       *                           *   leave(4), intervals=[2,9],[0,2],[-1,0],[9,10]
    *       *           *               *   seat(), intervals=[2,5],[5,9],[0,2],[-1,0],[9,10]
*/