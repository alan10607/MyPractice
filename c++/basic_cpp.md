# C++ Basic

常用std 方法



## unordered_map
```cpp
unordered_map.insert({1, "One"})
unordered_map.emplace(2, "Two"))
unordered_map.erase(1)
unordered_map[2] = "New Two"

unordered_map[2]
unordered_map.at(1)
unordered_map.find(3)
unordered_map.count(3)
```

## unordered_set
```cpp
unordered_set.insert(10)
unordered_set.emplace(20)
unordered_set.erase(10)

unordered_set.find(20)
unordered_set.count(10)
unordered_set.size()
```

## vector
```cpp
vector.push_back(1)
vector.insert(vector.begin(), 0)
vector.erase(myVector.begin() + 1)
vector.pop_back()

vector[1]
vector.at(1)
vector.front()
vector.back()
vector.empty()
vector.size()
```

## stack
```cpp
stack.push(1)
stach.pop(1)

stack.top()
stack.empty()
stack.size()
```

## queue
```cpp
queue.push(1)
queue.pop()

queue.front()
queue.back()
queue.empty()
queue.size()
```

## deque
```cpp
deque.push_back(1)
deque.push_front(0)
deque.emplace(deque.begin() + 1, 5)
deque.emplace(next(deque.begin()), 5)
deque.pop_front()
deque.pop_back()

deque.front()
deque.back()
deque.size()
deque.empty()
```

## priority_queue
```cpp
priority_queue.push(10)
priority_queue.pop()

priority_queue.top()
priority_queue.empty()
priority_queue.size()
```

## list
```cpp
list.push_back(1)
list.push_front(0)
list.emplace(next(list.begin()), 5) // 要用next(iterator)或prev(iterator)不能直接+1
list.pop_front()
list.pop_back()

list.front()
list.back()
list.size()
list.empty()
list<int>::iterator it = list.begin(); // 獲取迭代器
cout << *it; // 得到值

list.splice(list.begin(), list2); // (插入位置, 被傳送的list)
list.splice(list.begin(), list2, list2.begin()); // (插入位置, 被傳送的list, 被傳送的list的it)
list.splice(list.begin(), list2, next(list2.begin()), list2.end()); // (插入位置, 被傳送的list, 被傳送的list的it起始, 被傳送的list的it結束)
```

## pair
```cpp
pair<int, int> p = {1,2}
p.first
p.second
```


## 常用方法
```cpp
cout << "log" << endl; // out print
format("{}, {}", num1, nums2); // format to string
string(n, '.'); // 初始化string
to_string(123); // int to string
stoi("123"); // string to int
max(a, b); // 找最大值
min(a, b); // 找最小值
rand(); // 獲得隨機數字, 要取得[a,b)的隨機整数, 使用 a + (rand() % (b - a));

lower_bound(vec.begin(), vec.end(), x); // 第一個大於等於x的數, 返回iterator
upper_bound(vec.begin(), vec.end(), x); // 第一個大於x的數, 返回iterator
/*
            lower_bound(4);
            v
nums=[1,2,3,4,4,4,5]
                  ^
                  upper_bound(4);
*/
```
## 有關排序
sort跟priority_queue剛好相反
### Sort

#### 小到大 (default)
```cpp
sort(vec.begin(), vec.end());
sort(vec.begin(), vec.end(), less<int>());

auto comp1 = [](int a, int b){return a < b; };
sort(vec.begin(), vec.end(), comp1);
```

#### 大到小
```cpp
sort(vec.begin(), vec.end(), greater<int>());

auto comp2 = [](int a, int b){return a > b; };
sort(vec.begin(), vec.end(), comp2);
```


### Priority Queue

#### 大到小, max heap (default)
```cpp
priority_queue<int> pq;
priority_queue<int, vector<int>> pq1;
priority_queue<int, vector<int>, less<int>> pq2;

auto comp1 = [](int a, int b){return a < b; };
priority_queue<int, vector<int>, decltype(comp1)> pq3(comp1);
```

#### 小到大, min heap

```cpp
priority_queue<int, vector<int>, greater<int>> pq4;

auto comp2 = [](int a, int b){return a > b; };
priority_queue<int, vector<int>, decltype(comp2)> pq5(comp2);
```

## stringstream
```cpp
ostringstream oss;
string name = "Alice";
int age = 22;

oss << "Name: " << name << ", Age: " << age; // 將資料用<<加入到oss
string str = oss.str(); // 需要先轉str
cout << str << endl; // Name: Alice, Age: 22

istringstream iss(str);
string s1, s2, s3;
int i;
iss >> s1 >> s2 >> s3 >> i; // 將資料從iss取出, 以" "為分隔
cout << s1 << endl; // Name:
cout << s2 << endl; // Alice,
cout << s3 << endl; // Age:
cout << i << endl; // 22
```

===

// Need Verify:
二分法小結
Reference:
https://medium.com/@CecileLiu/%E4%BA%8C%E5%88%86%E6%B3%95%E7%9A%84%E5%8D%80%E9%96%93-%E7%B6%B2%E8%B7%AF%E4%B8%8A%E6%9C%80%E8%A9%B3%E7%9B%A1%E7%9A%84%E8%A7%A3%E8%AA%AA-5adf3be3bc5e
https://blog.csdn.net/allenhsu6/article/details/113779530

1. 找某目標, 可以分成左閉右閉[l,r) 或左閉右開[l,r]
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return -1;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid - 1;
    }
    return -1;
}


2. 找第一個不小於某目標的(lower_bound), ex:[0,1,1,1,1], target=1, return=1
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (target <= nums[mid]) r = mid - 1
        else l = mid + 1;
    }
    return l;
}


3. 找第一個大於某目標的(upper_bound), ex:[0,1,1,1,1], target=1, return=5
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid - 1;
    }
    return l;
}
