# SQL 語法筆記

- Study Plan: https://leetcode.com/studyplan/top-sql-50/
- 對應答案: `note/SQL50.sql`
- 以 **MySQL** 為基礎

---

## 總覽

| 類型 | 技巧 | 重要度 |
|------|-------------|--------|
| SELECT | JOIN / GROUP BY / Window / Subquery | ⭐⭐⭐⭐⭐ |
| INSERT | 單筆、多筆、INSERT SELECT | ⭐⭐⭐⭐ |
| UPDATE | WHERE、JOIN UPDATE、CASE | ⭐⭐⭐⭐⭐ |
| DELETE | WHERE、JOIN DELETE、duplicate cleanup | ⭐⭐⭐⭐⭐ |
| CREATE TABLE | PK / FK / UNIQUE / DEFAULT / INDEX | ⭐⭐⭐⭐⭐ |
| ALTER TABLE | ADD / MODIFY / DROP column | ⭐⭐⭐ |
| Transaction | START TRANSACTION / COMMIT / ROLLBACK | ⭐⭐⭐⭐⭐ |

---

## 目錄

### A. 查詢（SELECT）

1. [查詢執行順序](#1-查詢執行順序)
2. [SELECT / WHERE / DISTINCT / AS](#2-select--where--distinct--as)
3. [ORDER BY / LIMIT / OFFSET](#3-order-by--limit--offset)
4. [NULL 處理](#4-null-處理)
5. [JOIN](#5-join)
6. [聚合函式 GROUP BY / HAVING](#6-聚合函式-group-by--having)
7. [CASE / IF](#7-case--if)
8. [COALESCE / IFNULL / NULLIF](#8-coalesce--ifnull--nullif)
9. [日期與時間函式](#9-日期與時間函式)
10. [子查詢 / EXISTS](#10-子查詢--exists)
11. [UNION / 集合運算](#11-union--集合運算)
12. [Window Function（完整常考）](#12-window-function完整常考)
13. [CTE（WITH）](#13-ctewith)
14. [字串函式](#14-字串函式)
15. [型別轉換 CAST / CONVERT](#15-型別轉換-cast--convert)
16. [其他常用運算](#16-其他常用運算)

### B. 資料異動與結構

17. [INSERT](#17-insert)
18. [UPDATE](#18-update)
19. [DELETE / TRUNCATE](#19-delete--truncate)
20. [CREATE TABLE](#20-create-table)
21. [ALTER TABLE](#21-alter-table)
22. [INDEX](#22-index)
23. [Transaction 交易](#23-transaction-交易)
24. [VIEW 檢視表（補充）](#24-view-檢視表補充)

---

# A. 查詢（SELECT）

## 1. 查詢執行順序

```text
FROM / JOIN
  → WHERE
  → GROUP BY
  → HAVING
  → SELECT（含 Window）
  → DISTINCT
  → ORDER BY
  → LIMIT / OFFSET
```

- `WHERE`：聚合**前**過濾列
- `HAVING`：聚合**後**過濾組
- Window Function 在 `SELECT` 階段計算，**不能**直接寫在同層 `WHERE`（要包一層子查詢 / CTE）

---

## 2. SELECT / WHERE / DISTINCT / AS

### SELECT

```sql
SELECT name, population, area
FROM World;
```

### WHERE

```sql
SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';
```

### DISTINCT

```sql
SELECT DISTINCT author_id AS id
FROM Views
WHERE author_id = viewer_id;
```

### AS（別名）

```sql
SELECT u.unique_id, e.name
FROM Employees AS e
LEFT JOIN EmployeeUNI AS u ON e.id = u.id;
```

---

## 3. ORDER BY / LIMIT / OFFSET

```sql
SELECT *
FROM Cinema
WHERE id % 2 = 1 AND description <> 'boring'
ORDER BY rating DESC;

-- 第二高薪水：跳過 1 筆再取 1 筆
SELECT DISTINCT salary
FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET 1;
-- 等價：LIMIT 1, 1  （OFFSET, COUNT）
```

---

## 4. NULL 處理

`NULL` 不能用 `=` / `<>` 比較。

```sql
SELECT name
FROM Customer
WHERE referee_id <> 2 OR referee_id IS NULL;

SELECT e.name, b.bonus
FROM Employee AS e
LEFT JOIN Bonus AS b ON e.empId = b.empId
WHERE b.bonus < 1000 OR b.bonus IS NULL;
```

找「沒有對應」：

```sql
FROM Visits v
LEFT JOIN Transactions t ON v.visit_id = t.visit_id
WHERE t.transaction_id IS NULL;
```

---

## 5. JOIN

### INNER JOIN

只保留兩邊都配得上的列。

```sql
FROM Activity a
JOIN Activity b
  ON a.machine_id = b.machine_id
 AND a.process_id = b.process_id
 AND a.activity_type = 'start'
 AND b.activity_type = 'end';
```

### LEFT JOIN / RIGHT JOIN

- `LEFT JOIN`：保留左表全部；右表沒配到 → `NULL`
- `RIGHT JOIN`：保留右表全部（實務較少用，多半改寫成 LEFT）

```sql
FROM Employees e
LEFT JOIN EmployeeUNI u ON e.id = u.id;
```

### Self Join

```sql
SELECT w1.id
FROM Weather w1
JOIN Weather w2
  ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
WHERE w1.temperature > w2.temperature;
```

### CROSS JOIN

笛卡兒積，不能寫 `ON`。

```sql
FROM Students s
CROSS JOIN Subjects sub;  -- 每個學生 × 每個科目
```


---

## 6. 聚合函式 GROUP BY / HAVING

| 函式 | 意思 |
|------|------|
| `COUNT(*)` | 列數 |
| `COUNT(col)` | 非 NULL 個數 |
| `COUNT(DISTINCT col)` | 去重個數 |
| `SUM` / `AVG` / `MAX` / `MIN` | 加總 / 平均 / 最大 / 最小 |

```sql
SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt
FROM Teacher
GROUP BY teacher_id;

SELECT class
FROM Courses
GROUP BY class
HAVING COUNT(*) >= 5;   -- 聚合後過濾
```

```text
LEFT JOIN 後若要數「有沒有事件」：
用 COUNT(欄位)（忽略 NULL），不要無腦 COUNT(*)
```

`ROUND(x, n)`：四捨五入到小數 n 位。

```sql
ROUND(AVG(experience_years), 2)
```

---

## 7. CASE

### CASE

```sql
CASE
    WHEN 條件1 THEN 結果1
    WHEN 條件2 THEN 結果2
    ELSE 預設
END
```

```sql
-- 轉 0/1 再 AVG = 比例
AVG(CASE WHEN c.action = 'confirmed' THEN 1 ELSE 0 END)

-- 條件加總
SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END)

-- 分類標籤
CASE
    WHEN income < 20000 THEN 'Low'
    WHEN income <= 50000 THEN 'Average'
    ELSE 'High'
END
```

---

## 8. COALESCE / IFNULL / NULLIF

```sql
COALESCE(a, b, c)   -- 左到右第一個非 NULL（標準 SQL，可多個）
```

```sql
COALESCE(SUM(price * units) / SUM(units), 0)

-- 避免除以 0
SELECT total / NULLIF(cnt, 0);
```

---

## 9. 日期與時間函式

### DATEDIFF / TIMESTAMPDIFF

```sql
DATEDIFF(A, B)          -- A - B（天）
TIMESTAMPDIFF(UNIT, A, B) -- B - A（依 UNIT）

-- UNIT 例：SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR
TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age
```

```text
DATEDIFF(A, B) > 0 → A 較晚；= 0 同一天；< 0 A 較早
含今天共 30 天 → BETWEEN 0 AND 29
```

### DATE_FORMAT / DATE_ADD / DATE_SUB

```sql
DATE_FORMAT(trans_date, '%Y-%m') AS month

DATE_ADD(CURDATE(), INTERVAL 7 DAY)
DATE_SUB(NOW(), INTERVAL 1 MONTH)
```

| 格式 | 意思 | 範例 |
|------|------|------|
| `%Y` / `%y` | 年 4/2 位 | 2026 / 26 |
| `%m` / `%c` | 月（補0 / 不補） | 09 / 9 |
| `%d` / `%e` | 日 | 14 / 14 |
| `%H` `%i` `%s` | 時分秒 | 17:30:45 |
| `%W` / `%a` | 星期名 | Monday / Mon |

### 其他常用

```sql
CURDATE() / CURRENT_DATE()
NOW() / CURRENT_TIMESTAMP()
YEAR(d) / MONTH(d) / DAY(d)
DAYOFWEEK(d)          -- 1=Sunday ... 7=Saturday
LAST_DAY(d)           -- 該月最後一天
BETWEEN start AND end -- 含兩端
```

---

## 10. 子查詢 / EXISTS

### IN / NOT IN

```sql
WHERE num IN (
    SELECT num FROM MyNumbers
    GROUP BY num HAVING COUNT(*) = 1
);

-- 多欄位（每組第一筆 / 最後一筆）
WHERE (customer_id, order_date) IN (
    SELECT customer_id, MIN(order_date)
    FROM Delivery
    GROUP BY customer_id
);
```

```text
NOT IN 子查詢若含 NULL → 整段失效。
較安全：改用 NOT EXISTS。
```

### EXISTS / NOT EXISTS（常考、通常比 IN 好）

只關心「有沒有列」，不取出值。

```sql
-- 有下屬的員工
SELECT e.id, e.name
FROM Employee e
WHERE EXISTS (
    SELECT 1
    FROM Employee s
    WHERE s.managerId = e.id
);

-- manager 已不在公司（較安全寫法）
SELECT employee_id
FROM Employees e
WHERE salary < 30000
  AND e.manager_id IS NOT NULL
  AND NOT EXISTS (
      SELECT 1
      FROM Employees m
      WHERE m.employee_id = e.manager_id
  );
```

### FROM 子查詢 / Scalar Subquery

```sql
-- 衍生表
JOIN (
    SELECT player_id, MIN(event_date) AS first_date
    FROM Activity GROUP BY player_id
) f ON a.player_id = f.player_id

-- 純量：沒資料仍回一列 NULL
SELECT (
    SELECT DISTINCT salary FROM Employee
    ORDER BY salary DESC LIMIT 1 OFFSET 1
) AS SecondHighestSalary;
```

### ANY / ALL（補充）

```sql
WHERE salary > ANY (SELECT salary FROM Employee WHERE dept = 1)  -- > 其中某個
WHERE salary > ALL (SELECT salary FROM Employee WHERE dept = 1)  -- > 全部
```

---

## 11. UNION / 集合運算

| 寫法 | 行為 |
|------|------|
| `UNION` | 合併去重 |
| `UNION ALL` | 合併保留重複（較快） |

```sql
SELECT ... HAVING COUNT(*) = 1
UNION ALL
SELECT ... WHERE primary_flag = 'Y';
```

---

## 12. Window Function

對一組列計算，**列不會被收合**（和 `GROUP BY` 不同）。

### 基本語法

```sql
函式() OVER (
    [PARTITION BY 分組]
    [ORDER BY 排序]
    [ROWS | RANGE BETWEEN 起點 AND 終點]
)
```

```text
不能把 Window 結果直接寫在同層 WHERE
→ 先算完，外包 CTE / 子查詢再過濾
```

### 排名類（超高頻）

| 函式 | 同名次行為 | 例 100,100,90 |
|------|-----------|---------------|
| `ROW_NUMBER()` | 強制唯一序號 | 1, 2, 3 |
| `RANK()` | 同名次，**下一名跳號** | 1, 1, 3 |
| `DENSE_RANK()` | 同名次，**不跳號** | 1, 1, 2 |
| `NTILE(n)` | 切成 n 組 | 依序分桶 |

```sql
-- 每組取最新一筆：ROW_NUMBER = 1
WITH t AS (
    SELECT
        *,
        ROW_NUMBER() OVER (
            PARTITION BY user_id
            ORDER BY created_at DESC
        ) AS rn
    FROM Orders
)
SELECT * FROM t WHERE rn = 1;

-- 每部門薪水 Top 3（含並列）
DENSE_RANK() OVER (
    PARTITION BY departmentId
    ORDER BY salary DESC
) AS SalaryRank
-- 再 WHERE SalaryRank <= 3

-- 分數由低 → 高 依序分成 4 組
NTILE(4) OVER (ORDER BY score) AS quartile
```

### 位移類：LAG / LEAD（常考）

取**同一分區**裡，往前 / 往後第 n 列的值。

```sql
-- LAG = 看前一筆
LAG(欄位, n, 預設值)  OVER (PARTITION BY ... ORDER BY ...)

-- LEAD = 看後一筆
LEAD(欄位, n, 預設值) OVER (PARTITION BY ... ORDER BY ...)

-- n 預設 1；預設值可省略（沒有則 NULL）
```

```sql
-- 與前一天比溫度 / 營收
SELECT
    recordDate,
    temperature,
    LAG(temperature, 1) OVER (
        ORDER BY recordDate
    ) AS prev_temp,
    temperature - LAG(temperature, 1) OVER (
        ORDER BY recordDate
    ) AS diff
FROM Weather;

-- 與下一筆訂單間隔
SELECT
    user_id,
    order_date,
    LEAD(order_date, 1) OVER (
        PARTITION BY user_id ORDER BY order_date
    ) AS next_order_date
FROM Orders;
```

### 首尾值：FIRST_VALUE / LAST_VALUE

```sql
FIRST_VALUE(salary) OVER (
    PARTITION BY departmentId
    ORDER BY salary DESC
) AS top_salary_in_dept

-- LAST_VALUE() 找到的是目前 window frame 裡的最後一筆
-- 而不是整個 department 的最後一筆。
-- 若要整組最後一個，常寫：
LAST_VALUE(salary) OVER (
    PARTITION BY departmentId
    ORDER BY salary
    ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
)
```

### 聚合當 Window：SUM / AVG / COUNT / MAX / MIN

```sql
-- 累加和（running total）
SUM(weight) OVER (ORDER BY turn) AS total_weight

-- 分組內總和（每列都帶上該組總和，方便算占比）
SUM(amount) OVER (PARTITION BY user_id) AS user_total,
amount / SUM(amount) OVER (PARTITION BY user_id) AS pct
```

### 視窗框架 ROWS / RANGE

```sql
ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
-- 含當前列往前共 7 列（移動平均常用）

ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
-- 從分區開頭累加到現在（預設在有 ORDER BY 的聚合 Window 常是這種語意）

ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
-- 整份分區

ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING
-- 前一列 + 自己 + 後一列
```

```text
ROWS：依「列數」
RANGE：依 ORDER BY「值的範圍」（較少考，但要知道有差）
```

### 實戰組合範例

```sql
-- 7 日移動平均（先按日聚合）
WITH DailySum AS (
    SELECT visited_on, SUM(amount) AS amount
    FROM Customer
    GROUP BY visited_on
)
SELECT
    visited_on,
    SUM(amount) OVER (
        ORDER BY visited_on
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) AS amount,
    ROUND(AVG(amount) OVER (
        ORDER BY visited_on
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ), 2) AS average_amount
FROM DailySum
ORDER BY visited_on
LIMIT 1000000 OFFSET 6;  -- 前 6 天視窗不足，跳過
```

---

## 13. CTE（WITH）

CTE = Common Table Expression  
先把一個查詢結果暫時取名字，後面的 SQL 再使用它
```sql
WITH 名稱 AS (
    SELECT ...
),
名稱2 AS (
    SELECT ... FROM 名稱 ...
)
SELECT ... FROM 名稱2;
```

```sql
WITH DailySum AS (
    SELECT visited_on, SUM(amount) AS amount
    FROM Customer
    GROUP BY visited_on
)
SELECT * FROM DailySum;
```

### 遞迴 CTE（階層資料，常考）

```sql
WITH RECURSIVE EmpTree AS (
    -- 錨點：最高層
    SELECT employee_id, manager_id, name, 1 AS lvl
    FROM Employees
    WHERE manager_id IS NULL

    UNION ALL

    -- 遞迴：找下屬
    SELECT e.employee_id, e.manager_id, e.name, t.lvl + 1
    FROM Employees e
    JOIN EmpTree t ON e.manager_id = t.employee_id
)
SELECT * FROM EmpTree ORDER BY lvl, employee_id;
```

---

## 14. 字串函式

```sql
LENGTH(s) / CHAR_LENGTH(s)     -- 位元組長 / 字元長
CONCAT(a, b, ...)
CONCAT_WS(',', a, b, c)        -- 用分隔符連接，自動跳過 NULL
UPPER(s) / LOWER(s)
SUBSTRING(s, pos, len)         -- pos 從 1；len 可省略
LEFT(s, n) / RIGHT(s, n)
TRIM(s) / LTRIM(s) / RTRIM(s)
REPLACE(s, from_str, to_str)
LOCATE(substr, s) / INSTR(s, substr)  -- 找到回位置，找不到 0
```

```sql
CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2)))

WHERE conditions LIKE 'DIAB1%'
   OR conditions LIKE '% DIAB1%'
-- % 任意長度；_ 單一字元

-- 正規表示式
WHERE email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'
```

### GROUP_CONCAT

```sql
GROUP_CONCAT(
    DISTINCT product
    ORDER BY product
    SEPARATOR ','
)
```

---

## 15. 型別轉換 CAST / CONVERT

```sql
CAST(expr AS 型別)
CONVERT(expr, 型別)

CAST('2026-09-15' AS DATE)
CAST(amount AS DECIMAL(10, 2))
CAST(id AS CHAR)

-- 隱式轉型風險：字串和數字比較時要注意
```

常用型別：`SIGNED` / `UNSIGNED` / `CHAR` / `DATE` / `DATETIME` / `DECIMAL(m,d)`

---

## 16. 其他常用運算

```sql
id % 2 = 1
=  <>  !=  >  >=  <  <=
BETWEEN a AND b
IN (1, 2, 3)

-- 百分比避免整數除法
COUNT(*) * 100.0 / total

-- 條件函式
GREATEST(a, b, c) / LEAST(a, b, c)
```

---

# B. 資料異動與結構

## 17. INSERT

### 單筆

```sql
INSERT INTO users (name, email, age)
VALUES ('Alan', 'alan@example.com', 28);
```

### 多筆

```sql
INSERT INTO users (name, email, age)
VALUES
    ('Bob', 'bob@example.com', 30),
    ('Cathy', 'cathy@example.com', 25);
```

### INSERT ... SELECT

把查詢結果插入另一張表。

```sql
INSERT INTO users_archive (id, name, email)
SELECT id, name, email
FROM users
WHERE last_login < '2020-01-01';
```

### INSERT IGNORE / ON DUPLICATE KEY UPDATE

```sql
-- 主鍵或 UNIQUE 衝突則忽略
INSERT IGNORE INTO users (id, email) VALUES (1, 'a@x.com');

-- 衝突則改成更新（upsert）
INSERT INTO users (id, email, login_count)
VALUES (1, 'a@x.com', 1)
ON DUPLICATE KEY UPDATE
    login_count = login_count + 1,
    email = VALUES(email);
```

---

## 18. UPDATE

### 基本 UPDATE + WHERE

```sql
UPDATE users
SET age = 29, updated_at = NOW()
WHERE id = 1;
-- 務必加 WHERE，否則整表更新
```

### 用 CASE 一次改多種值

```sql
UPDATE employees
SET salary = CASE
    WHEN title = 'Senior' THEN salary * 1.10
    WHEN title = 'Junior' THEN salary * 1.05
    ELSE salary
END
WHERE department_id = 3;
```


### 子查詢 UPDATE

```sql
UPDATE products
SET price = (
    SELECT AVG(price) FROM (
        SELECT price FROM products WHERE category = 'book'
    ) t
)
WHERE category = 'book' AND price IS NULL;
-- MySQL 不能直接在子查詢讀正在 UPDATE 的同一張表，常需再包一層
```

---

## 19. DELETE / TRUNCATE

### 基本 DELETE

```sql
DELETE FROM users
WHERE id = 10;
-- 務必加 WHERE
```

### JOIN DELETE（清重複）

```sql
-- 同 email 保留較小 id
DELETE a
FROM Person a
JOIN Person b
  ON a.email = b.email
 AND a.id > b.id;
```

### 子查詢 DELETE

```sql
DELETE FROM orders
WHERE user_id IN (
    SELECT id FROM (
        SELECT id FROM users WHERE status = 'deleted'
    ) t
);
```

### TRUNCATE vs DELETE

| | DELETE | TRUNCATE |
|--|--------|----------|
| 可加 WHERE | ✅ | ❌（整表清空） |
| 可 ROLLBACK | ✅（In InnoDB 交易內） | 通常視為 DDL，難回滾 |
| 自增計數 | 通常保留 | 重置 |
| 速度 | 較慢 | 較快 |

```sql
TRUNCATE TABLE logs;
```

---

## 20. CREATE TABLE

```sql
CREATE TABLE employees (
    id            INT AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    department_id INT,
    salary        DECIMAL(10, 2) DEFAULT 0,
    status        ENUM('active', 'left') DEFAULT 'active',
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uk_employees_email (email),
    INDEX idx_employees_dept (department_id),
    CONSTRAINT fk_employees_dept
        FOREIGN KEY (department_id)
        REFERENCES departments(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 約束速記

| 約束 | 意思 |
|------|------|
| `PRIMARY KEY` | 主鍵；唯一且非 NULL |
| `FOREIGN KEY` | 外鍵，參照他表 |
| `UNIQUE` | 值唯一（NULL 行為依版本/設定） |
| `NOT NULL` | 不可空 |
| `DEFAULT` | 預設值 |
| `AUTO_INCREMENT` | 自動遞增（通常配 PK） |
| `CHECK` (MySQL 8.0.16+) | 檢查條件 |

### 外鍵 ON DELETE / ON UPDATE

```text
RESTRICT / NO ACTION  → 有參照就不准刪/改
CASCADE               → 連帶刪/改
SET NULL              → 子表該欄設成 NULL
SET DEFAULT           → 設成預設值
```

### 用查詢建表

```sql
CREATE TABLE users_backup AS
SELECT * FROM users WHERE 1 = 0;   -- 只複製結構（無資料的一種写法）

CREATE TABLE users_2026 AS
SELECT * FROM users WHERE YEAR(created_at) = 2026;
-- 注意：AS SELECT 不會複製索引 / 約束
```

---

## 21. ALTER TABLE

```sql
-- 新增欄位
ALTER TABLE users
ADD COLUMN phone VARCHAR(20) NULL AFTER email;

-- 一次新增多欄
ALTER TABLE users
ADD COLUMN age INT NULL,
ADD COLUMN city VARCHAR(50) NULL;

-- 修改型別 / 屬性（MySQL）
ALTER TABLE users
MODIFY COLUMN name VARCHAR(150) NOT NULL;

-- 改名 + 改型別
ALTER TABLE users
CHANGE COLUMN phone mobile VARCHAR(30) NULL;

-- 刪欄位
ALTER TABLE users
DROP COLUMN city;

-- 加 / 刪主鍵、唯一、外鍵
ALTER TABLE users ADD PRIMARY KEY (id);
ALTER TABLE users ADD UNIQUE KEY uk_email (email);
ALTER TABLE users DROP INDEX uk_email;

ALTER TABLE employees
ADD CONSTRAINT fk_dept
FOREIGN KEY (department_id) REFERENCES departments(id);

ALTER TABLE employees DROP FOREIGN KEY fk_dept;

-- 改表名
RENAME TABLE users TO app_users;
-- 或
ALTER TABLE users RENAME TO app_users;
```

---

## 22. INDEX

加速查詢，但會讓寫入變慢、佔空間。

```sql
-- 建索引
CREATE INDEX idx_users_email ON users(email);
CREATE UNIQUE INDEX uk_users_email ON users(email);

-- 複合索引（注意最左前綴原則）
CREATE INDEX idx_orders_user_date ON orders(user_id, order_date);
-- 可優化：WHERE user_id = ? 
-- 可優化：WHERE user_id = ? AND order_date = ?
-- 難優化：WHERE order_date = ?（沒用到最左欄）

-- 刪索引
DROP INDEX idx_users_email ON users;

-- 看執行計畫
EXPLAIN SELECT * FROM users WHERE email = 'a@x.com';
```

```text
WHERE / JOIN ON 的欄位常是建索引候選人
先 EXPLAIN，再決定要不要加索引
```

---

## 23. Transaction 交易

一組操作要嘛全成功，要嘛全失敗（InnoDB）。

### 基本流程

```sql
START TRANSACTION;   -- 或 BEGIN;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

COMMIT;     -- 確認寫入
-- 或
ROLLBACK;   -- 全部撤銷
```

### 出錯時回滾範例（概念）

```sql
START TRANSACTION;

UPDATE products SET stock = stock - 1 WHERE id = 10 AND stock >= 1;
-- 若 ROW_COUNT() = 0 表示庫存不足
-- 則 ROLLBACK; 

INSERT INTO orders (product_id, qty) VALUES (10, 1);
COMMIT;
```

### 自動提交

```sql
SELECT @@autocommit;     -- 1 表示每句自動 commit
SET autocommit = 0;      -- 改手動
-- 連線結束前記得 COMMIT / ROLLBACK
```

### 隔離層級（面試常問概念）

```sql
SELECT @@transaction_isolation;
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
```

| 層級 | 髒讀 | 不可重複讀 | 幻讀 |
|------|------|-----------|------|
| READ UNCOMMITTED | 可能 | 可能 | 可能 |
| READ COMMITTED | 防 | 可能 | 可能 |
| REPEATABLE READ（MySQL 預設） | 防 | 防 | 大致防 |
| SERIALIZABLE | 防 | 防 | 防 |

### SAVEPOINT（部分回滾）

```sql
START TRANSACTION;
UPDATE ...;
SAVEPOINT sp1;
UPDATE ...;
ROLLBACK TO sp1;  -- 回到存檔點
COMMIT;
```

---

## 24. VIEW 檢視表（補充）

把常用查詢存成虛擬表。

```sql
CREATE VIEW v_dept_salary AS
SELECT department_id, AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id;

SELECT * FROM v_dept_salary WHERE avg_salary > 80000;

DROP VIEW v_dept_salary;
```

---

## 速查：看到需求 → 用什麼

| 需求 | 語法 |
|------|------|
| 沒有對應的列 | `LEFT JOIN` + `IS NULL` / `NOT EXISTS` |
| 每組第一筆 / 最新一筆 | `ROW_NUMBER()=1` 或 `(id, date) IN (MIN/MAX)` |
| Top N（含並列） | `DENSE_RANK()` |
| 和上一筆比 | `LAG()` |
| 累加 / 移動平均 | `SUM/AVG() OVER (... ROWS BETWEEN ...)` |
| 比例 | `AVG(CASE WHEN ... THEN 1 ELSE 0)` |
| 插入查詢結果 | `INSERT ... SELECT` |
| 衝突則更新 | `ON DUPLICATE KEY UPDATE` |
| 多表更新 | `UPDATE ... JOIN ... SET` |
| 刪重複 | `DELETE a FROM t a JOIN t b ON ... AND a.id > b.id` |
| 要嘛全成要嘛全敗 | `START TRANSACTION` + `COMMIT` / `ROLLBACK` |
