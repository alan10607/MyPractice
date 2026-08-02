# Python Basic

```py
# =========================
# list
# =========================
lst = []

lst.append(1)                 # 加到尾端
lst.insert(0, 0)              # 指定位置插入
lst.pop()                     # 移除最後一個
lst.pop(1)                    # 移除 index 1
lst.remove(1)                 # 移除第一個出現的值

lst[1]                        # 取值
lst[-1]                       # 最後一個
lst[0]                        # 第一個

len(lst)                      # 長度
lst.clear()                   # 清空
lst.sort()                    # 排序（升序）
lst.sort(reverse=True)        # 降序
sorted(lst)                   # 回傳新排序 list
lst.reverse()                 # 反轉
```

```py
# =========================
# dict
# =========================
d = {}

d[1] = "One"                  # 新增 / 更新
d.get(1)                      # 取值（不存在回 None）
d.get(1, "default")           # 預設值
d.pop(1)                      # 移除 key
d.keys()                      # 所有 key
d.values()                    # 所有 value
d.items()                     # (key, value)
len(d)                        # 大小
1 in d                        # key 是否存在
```

```py
# =========================
# set
# =========================
s = set()

s.add(10)                     # 加入
s.remove(10)                  # 移除（不存在會錯）
s.discard(10)                 # 移除（不存在不會錯）
10 in s                       # 是否存在
len(s)                        # 大小
s.clear()                     # 清空
```

```py
# =========================
# tuple
# =========================
t = (1, 2, 3)

t[0]                          # 取值
len(t)                        # 長度
# tuple 不可修改
```

```py
# =========================
# stack（用 list 模擬）
# =========================
stack = []

stack.append(1)               # push
stack.pop()                   # pop
stack[-1]                     # top
len(stack)                    # size
not stack                     # empty
```

```py
# =========================
# queue（用 collections.deque）
# =========================
from collections import deque

q = deque()

q.append(1)                   # enqueue
q.popleft()                   # dequeue
q[0]                          # front
q[-1]                         # back
len(q)                        # size
not q                         # empty
```

```py
# =========================
# deque
# =========================
dq = deque()

dq.append(1)                  # 後面加入
dq.appendleft(0)              # 前面加入
dq.pop()                      # 後面移除
dq.popleft()                  # 前面移除

dq[0]                         # front
dq[-1]                        # back
len(dq)
not dq
```

```py
# =========================
# heap / priority queue
# =========================
import heapq

heap = []

heapq.heappush(heap, 10)      # push
heapq.heappop(heap)           # pop (min heap)
heap[0]                       # top（最小值）

# max heap
heapq.heappush(heap, -10)
-max(heap[0])
```

```py
# =========================
# 常用工具方法
# =========================
print("log")                  # print

str(123)                      # int -> string
int("123")                    # string -> int

max(a, b)
min(a, b)

abs(-10)

sum([1, 2, 3])

sorted([3, 1, 2])             # 回傳新 list
reversed([1, 2, 3])           # iterator
```

```py
# =========================
# bisect（二分搜尋）
# =========================
import bisect

arr = [1, 2, 2, 3, 5]

bisect.bisect_left(arr, 2)    # 第一個 >= x
bisect.bisect_right(arr, 2)   # 第一個 > x
```

```py
# =========================
# random
# =========================
import random

random.randint(1, 10)         # [1, 10]
random.random()               # [0, 1)
random.randrange(1, 10)       # [1, 10)
```

```py
# =========================
# string 常用
# =========================
s = "hello"

s.upper()
s.lower()
s.strip()
s.replace("h", "H")
s.split()
",".join(["a", "b"])
ord("a") #97
ord("A") #65
chr(97) #"a"
chr(65) #"A"
```

```py
# =========================
# enumerate / zip
# =========================
for i, v in enumerate([10, 20, 30]):
    pass

for a, b in zip([1, 2], [3, 4]):
    pass
```

# =========================
# lambda / map / filter
# =========================
```py
add = lambda x, y: x + y
add(1, 2)              # 3

square = lambda x: x * x
square(5)              # 25

list(map(lambda x: x * 2, [1, 2, 3]))
# [2, 4, 6]

list(filter(lambda x: x > 2, [1, 2, 3, 4]))
# [3, 4]
```

# =========================
# defaultdict 常用
# =========================
```py
from collections import defaultdict


# key -> list
groups = defaultdict(list)

groups["a"].append(1)
groups["a"].append(2)

# {
#   "a": [1,2]
# }


# key -> count
count = defaultdict(int)

for c in "apple":
    count[c] += 1

# {
#   "a":1,
#   "p":2,
#   "l":1,
#   "e":1
# }


# key -> set
seen = defaultdict(set)

seen["user"].add("Alan")


# nested defaultdict
graph = defaultdict(list)

graph["A"].append("B")
graph["A"].append("C")


tree = defaultdict(lambda: defaultdict(int))

tree["user"]["score"] += 1

```