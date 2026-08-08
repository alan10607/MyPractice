# Java Basic

## Array & String 初始化與轉換
```java
int[] arr = new int[n]; // 定長陣列
int[] arr2 = {1, 2, 3}; // 直接初始化
return new int[]{1, 2, 3, 4, 5}; // 回傳
Arrays.fill(arr, 0); // 填入預設值
Arrays.sort(arr); // 陣列排序

// String 與 char 轉換
String s = "hello";
s.equals(s2); // 相同
char[] chars = s.toCharArray(); // 字串轉字元陣列
String newStr = new String(chars); // 字元陣列轉字串
char c = s.charAt(0); // 取得特定字元
int len = s.length(); // 字串長度
s.substring(start, end); // 切割字串 [start, end)

Integer.parseInt("123"); // String → int
String.valueOf(123); // int → String 

str.toLowerCase(); // 全部小寫
str.toUpperCase(); // 全部大寫
Character.toLowerCase(ch); // 小寫 char
Character.toUpperCase(ch); // 大寫 char
Character.isDigit(ch); // 數字（Unicode 數字）
Character.isLetter(ch); // 字母（包含英文與非英文，例如日文、中文等文字系統的字母類字元）
Character.isLetterOrDigit(ch); // 數字 + 字母（英文與非英文）


// 無條件進位
double a = Math.ceil(8.1);  // 9.0
double b = Math.ceil(8.0);  // 8.0
double c = Math.ceil(-8.1); // -8.0

// 無條件捨去
double a = Math.floor(8.9);  // 8.0
double b = Math.floor(8.0);  // 8.0
double c = Math.floor(-8.1); // -9.0
```

## StringBuilder (高效處理字串拼接)
```Java
StringBuilder sb = new StringBuilder();
sb.append("abc"); // 拼接字串/字元/數字
sb.setLength(sb.length() - 1);  // 刪除最後一個字元（較常用）
sb.deleteCharAt(sb.length() - 1); // 刪除最後一個字元
sb.setLength(0); // 清空
sb.reverse(); // 反轉字串
String res = sb.toString(); // 轉回 String
```

## ArrayList (動態陣列，對應 C++ vector)
```Java
ArrayList<Integer> list = new ArrayList<>();
list.add(1); // push_back
list.get(0); // 取得元素
list.set(0, 10); // 修改元素
list.remove(list.size() - 1); // 移除尾端
list.size(); // 長度
list.isEmpty(); // 是否為空
list.clear(); // 清空
List.of(1, 2, 3, 4, 5); // 不可變
List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
```

## HashMap (雜湊表，對應 C++ unordered_map)
```Java
HashMap<Integer, Integer> map = new HashMap<>();
map.put(key, value); // 插入或更新
map.get(key); // 取得值，若無返回 null
map.getOrDefault(key, 0); // 若無該 key 則返回預設值 0 (非常常用)
map.put(key, map.getOrDefault(num, 0) + 1); // 計數
map.containsKey(key); // 檢查是否存在 key
map.remove(key); // 刪除 key
map.putIfAbsent(key, new ArrayList<>()); // 傳入現成物件，若 key 不存在則放入
map.computeIfAbsent(key, k -> new ArrayList<>()); // 傳入 Lambda，若 key 不存在時才執行並放入
map.keySet(); // 取得所有 key 的集合
map.values(); // 取得所有value
Map<String, Integer> map = new HashMap<>(Map.of(
    "A", 1,
    "B", 2,
    "C", 3
));
```

## TreeMap (紅黑樹，有序 Map，對應 C++ map)
```java
TreeMap<Integer, Integer> map = new TreeMap<>();

map.put(key, value); // 插入或更新 (自動依 key 排序)
map.get(key); // 取得值，若無返回 null
map.getOrDefault(key, 0); // 若無該 key 則返回預設值 0
map.put(key, map.getOrDefault(key, 0) + 1); // 計數
map.containsKey(key); // 檢查是否存在 key
map.remove(key); // 刪除 key
map.putIfAbsent(key, value); // 若 key 不存在則放入
map.computeIfAbsent(key, k -> new ArrayList<>()); // 若 key 不存在時執行 Lambda 並放入
map.keySet(); // 取得所有 key (已排序)
map.values(); // 取得所有 value
map.entrySet(); // 取得所有 key-value pair

map.firstKey(); // 取得最小 key
map.lastKey(); // 取得最大 key
map.firstEntry(); // 取得最小 key-value pair
map.lastEntry(); // 取得最大 key-value pair

map.floorKey(x);    // <= x 最大值
map.ceilingKey(x);  // >= x 最小值
map.lowerKey(x);    // < x 最大值
map.higherKey(x);   // > x 最小值

TreeMap<Integer, Integer> map = new TreeMap<>(); // 預設：小 -> 大
TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> a - b); // 小 -> 大

TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder()); // 大 -> 小
TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a); // 大 -> 小
```


## HashSet (雜湊集合，對應 C++ unordered_set)
```Java
HashSet<Integer> set = new HashSet<>();
set.add(1); // 插入
set.contains(1); // 檢查是否存在
set.remove(1); // 刪除
set.size(); // 大小
Set.of(1, 2, 3, 4, 5); // 不可變
Set<Integer> set = new HashSet<>(Set.of(1, 2, 3, 4, 5));
```

## Stack (堆疊，推薦使用 ArrayDeque)
```Java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); // 壓入元素
stack.pop(); // 彈出頂端元素
stack.peek(); // 取得頂端元素 (top)
stack.isEmpty(); // 是否為空


Stack (LIFO)

Top
 ↓
[3, 2, 1]

push(4) -> [4, 3, 2, 1]
pop()   -> [2, 1]      (回傳 3)
peek()  -> 3
```


## Queue (佇列，推薦使用 ArrayDeque)
```Java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1); // 加入隊尾 (push)
queue.poll(); // 移除隊首 (pop)
queue.peek(); // 取得隊首 (front)
queue.isEmpty(); // 是否為空


Queue (FIFO)

Front            Back
  ↓                ↓
[1, 2, 3]

offer(4) -> [1, 2, 3, 4]
poll()   -> [2, 3]      (回傳 1)
peek()   -> 1
```

## Deque (雙端佇列)
```Java
Deque<Integer> deque = new ArrayDeque<>();
deque.offerFirst(1); // 隊首加入
deque.offerLast(2); // 隊尾加入
deque.pollFirst(); // 隊首移除
deque.pollLast(); // 隊尾移除
deque.peekFirst(); // 取得隊首
deque.peekLast(); // 取得隊尾


Deque

Front            Back
  ↓                ↓
[1, 2, 3]

offerFirst(0) -> [0, 1, 2, 3]
offerLast(4)  -> [1, 2, 3, 4]
pollFirst()   -> [2, 3]      (回傳 1)
pollLast()    -> [1, 2]      (回傳 3)
peekFirst()   -> 1
peekLast()    -> 3
```

## PriorityQueue (優先佇列 / Heap)
```Java
// 預設為 Min Heap (小到大)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap (大到小)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

minHeap.offer(10); // 插入
minHeap.poll(); // 彈出極值
minHeap.peek(); // 取得極值
minHeap.size(); // 大小
```

## Math 與 常用工具
```Java
Math.max(a, b);
Math.min(a, b);
Math.abs(a);
Math.pow(2, 3); // 2 的 3 次方 (返回 double)
Math.sqrt(x); // 開根號

Integer.MAX_VALUE; // int 最大值 (常用於初始化最小值)
Integer.MIN_VALUE; // int 最小值 (常用於初始化最大值)
Long.MAX_VALUE;

Integer.parseInt("123"); // string 轉 int
String.valueOf(123); // int 轉 string
Character.isDigit('1'); // 檢查是否為數字字元
Character.isLetter('a'); // 檢查是否為英文字母
Character.toLowerCase('A'); // 轉小寫
```

## 排序 (Sort)
```Java
int[] arr = {3, 1, 4};
Arrays.sort(arr); // 陣列由小到大排序

ArrayList<Integer> list = new ArrayList<>();
Collections.sort(list); // 集合由小到大排序
Collections.sort(list, Collections.reverseOrder()); // 集合由大到小排序
```

## 二分搜尋模板 (Binary Search)
```Java
// 1. 標準尋找目標值
int search(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid - 1;
    }
    return -1;
}

// 2. 尋找第一個不小於目標值的位置 (lower_bound)
int lowerBound(int[] nums, int target) {
    int l = 0, r = nums.length;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return r;
}
```

===========================below old
## HashMap
```java
HashMap<Integer, String> hashMap = new HashMap<>();
hashMap.put(1, "One");
hashMap.put(2, "Two");
hashMap.remove(1);
hashMap.put(2, "New Two"); // 覆蓋

hashMap.get(2);
hashMap.getOrDefault(1, null); // 類似 at()，若無返回預設值
hashMap.containsKey(3); // 對應 find != end() 或 count(3) > 0
```

## HashSet
```java
HashSet<Integer> hashSet = new HashSet<>();
hashSet.add(10);
hashSet.remove(10);

hashSet.contains(20);
hashSet.size();
```

## ArrayList
```java
ArrayList<Integer> arrayList = new ArrayList<>();
arrayList.add(1); // push_back
arrayList.add(0, 0); // insert at begin
arrayList.remove(1); // erase at index 1
arrayList.remove(arrayList.size() - 1); // pop_back

arrayList.get(1);
arrayList.get(arrayList.size() - 1); // back()
arrayList.isEmpty();
arrayList.size();
```

## LinkedList (對應 list)
```java
LinkedList<Integer> linkedList = new LinkedList<>();
linkedList.addLast(1); // push_back
linkedList.addFirst(0); // push_front
linkedList.add(1, 5); // 類似 emplace(next(begin()), 5)
linkedList.pollFirst(); // pop_front
linkedList.pollLast(); // pop_back

linkedList.peekFirst(); // front()
linkedList.peekLast(); // back()
linkedList.size();
linkedList.isEmpty();

Iterator<Integer> it = linkedList.iterator(); // 獲取迭代器
System.out.println(it.next()); // 得到值並移動迭代器
```

// ## Stack (使用 ArrayDeque 實作)
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); // 壓入元素
stack.pop();   // 彈出頂端元素
stack.peek();  // 取得頂端元素
stack.isEmpty();
stack.size();


// ## Queue (使用 ArrayDeque 實作)
import java.util.Queue;
import java.util.ArrayDeque;

Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1); // 加入隊尾 (push)
queue.poll();   // 移除隊首 (pop)
queue.peek();   // 取得隊首 (front)
queue.isEmpty();
queue.size();


// ## Deque (雙端佇列)
import java.util.Deque;
import java.util.ArrayDeque;

Deque<Integer> deque = new ArrayDeque<>();
deque.offerLast(1);   // 隊尾加入 (push_back)
deque.offerFirst(0);  // 隊首加入 (push_front)
deque.pollFirst();    // 隊首移除 (pop_front)
deque.pollLast();     // 隊尾移除 (pop_back)
deque.peekFirst();    // 取得隊首 (front)
deque.peekLast();     // 取得隊尾 (back)
deque.size();
deque.isEmpty();


## PriorityQueue
```java
//預設為 Min Heap (小到大)，與 C++ 預設 Max Heap 相反！
PriorityQueue<Integer> minPq = new PriorityQueue<>(); 

// 若要 Max Heap (大到小)，需傳入 reverseOrder
PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

// 自訂 Comparator
PriorityQueue<Integer> customPq = new PriorityQueue<>((a, b) -> a - b); // min heap
PriorityQueue<Integer> customPq = new PriorityQueue<>((a, b) -> b - a); // max heap

minPq.offer(10); // push
minPq.poll(); // pop

minPq.peek(); // top()
minPq.isEmpty();
minPq.size();
```






// ==========================================
// 常用方法對照
// ==========================================
```java
System.out.println("log");
String.format("%d, %d", num1, num2); // format to string
String.valueOf(new char[n]).replace('\0', '.'); // 初始化 string(n, '.')
String.valueOf(123); // to_string
Integer.parseInt("123"); // stoi
Math.max(a, b);
Math.min(a, b);
(int)(Math.random() * (b - a)) + a; // 獲得 [a, b) 隨機整數

// Java 的 Collections 二分查找 (需先排序)
import java.util.Collections;
// 類似 lower_bound / upper_bound 可透過自訂寫法或套用現成邏輯（見下方二分法）


// ==========================================
// 有關排序 (Sort)
// ==========================================
import java.util.Arrays;

// 小到大 (default)
Collections.sort(listVec);
Collections.sort(listVec, (a, b) -> a - b);
listVec.sort((a, b) -> a.compareTo(b));

// 大到小
Collections.sort(listVec, Collections.reverseOrder());
Collections.sort(listVec, (a, b) -> b - a);


// ==========================================
// StringBuilder / StringTokenizer (對應 stringstream)
// ==========================================
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// 對應 ostringstream
StringBuilder oss = new StringBuilder();
String name = "Alice";
int age = 22;
oss.append("Name: ").append(name).append(", Age: ").append(age);
String str = oss.toString();
System.out.println(str);

// 對應 istringstream
StringTokenizer iss = new StringTokenizer(str, " ");
// 或是用 regex split 處理，這裡以 StringTokenizer 示範
while (iss.hasMoreTokens()) {
    String token = iss.nextToken();
    // 依序取出處理
}


// ==========================================
// 二分法小結 (Binary Search) Java 版本
// ==========================================

// 1. 找某目標 (Target)
class BinarySearch {
    
    // 左閉右開 [l, r)
    public int search1(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return -1;
    }

    // 左閉右閉 [l, r]
    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }


    // 2. 找第一個不小於某目標的 (lower_bound)
    public int lowerBound1(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return r;
    }

    public int lowerBound2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target <= nums[mid]) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }


    // 3. 找第一個大於某目標的 (upper_bound)
    public int upperBound1(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return r;
    }

    public int upperBound2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}