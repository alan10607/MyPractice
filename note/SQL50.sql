# Leetcode SQL50 Solutions
https://leetcode.com/studyplan/top-sql-50/

1757. Recyclable and Low Fat Products
https://leetcode.com/problems/recyclable-and-low-fat-products/
SELECT product_id FROM Products WHERE low_fats = 'Y' AND recyclable = 'Y';



584. Find Customer Referee
https://leetcode.com/problems/find-customer-referee/
SELECT name FROM Customer WHERE referee_id <> 2 OR referee_id IS NULL;



595. Big Countries
https://leetcode.com/problems/big-countries/
SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000;



1148. Article Views I
https://leetcode.com/problems/article-views-i/
SELECT DISTINCT author_id AS id
FROM Views
WHERE author_id = viewer_id
ORDER BY author_id ASC;



1683. Invalid Tweets
https://leetcode.com/problems/invalid-tweets/
SELECT tweet_id FROM Tweets WHERE LENGTH(content) > 15;



1378. Replace Employee ID With The Unique Identifier
https://leetcode.com/problems/replace-employee-id-with-the-unique-identifier/
SELECT u.unique_id, e.name 
FROM Employees AS e
LEFT JOIN EmployeeUNI AS u
    ON e.id = u.id;



1068. Product Sales Analysis I
https://leetcode.com/problems/product-sales-analysis-i/
SELECT p.product_name, s.year, s.price
FROM Sales AS s
LEFT JOIN Product AS p
    ON p.product_id = s.product_id;



1581. Customer Who Visited but Did Not Make Any Transactions
https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/
SELECT v.customer_id, COUNT(*) AS count_no_trans
FROM Visits AS v
LEFT JOIN Transactions AS t
    ON v.visit_id = t.visit_id
WHERE t.transaction_id IS NULL
GROUP BY v.customer_id;



197. Rising Temperature
https://leetcode.com/problems/rising-temperature/
SELECT w1.id
FROM Weather AS w1 
JOIN Weather AS w2
    ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
WHERE w1.temperature > w2.temperature;
/*
DATEDIFF(A, B)就是: A - B

所以:

DATEDIFF(A, B) > 0  ->  A 比 B 晚
DATEDIFF(A, B) = 0  ->  同一天
DATEDIFF(A, B) < 0  ->  A 比 B 早
*/



1661. Average Time of Process per Machine
https://leetcode.com/problems/average-time-of-process-per-machine/
SELECT a.machine_id, ROUND(AVG(b.timestamp - a.timestamp), 3) AS processing_time
FROM Activity AS a
JOIN Activity AS b
    ON a.machine_id = b.machine_id
    AND a.process_id = b.process_id
    AND a.activity_type = 'start'
    AND b.activity_type = 'end'
GROUP BY a.machine_id;
/*
ROUND(AVG(b.timestamp - a.timestamp), 3)

ROUND(value, 3)  ->  四捨五入到小數點後 3 位
*/



577. Employee Bonus
https://leetcode.com/problems/employee-bonus/
SELECT e.name, b.bonus
FROM Employee AS e
LEFT JOIN Bonus AS b
    ON e.empId = b.empId
WHERE b.bonus < 1000
    OR b.bonus IS NULL



1280. Students and Examinations
https://leetcode.com/problems/students-and-examinations/
SELECT 
    s.student_id, 
    s.student_name, 
    sub.subject_name, 
    COUNT(e.subject_name) as attended_exams -- 要用subject_name因為LEFT JOIN後出現NULL也會算一行
FROM Students AS s
CROSS JOIN Subjects AS sub
LEFT JOIN Examinations AS e
    ON s.student_id = e.student_id
    AND sub.subject_name = e.subject_name
GROUP BY s.student_id, sub.subject_name
ORDER BY s.student_id, sub.subject_name
/*
CROSS JOIN = 每一筆都跟另一張表的每一筆配一次, 產生兩張表的所有組合（笛卡兒積）
A 有 2 筆、B 有 3 筆 → 產生 2 × 3 = 6 筆
常用於「每個學生 × 每個科目」這種所有組合

CROSS JOIN 本身不能用 ON,
CROSS JOIN 就是「全部組合」, 所以沒有 ON 條件
*/



570. Managers with at Least 5 Direct Reports
https://leetcode.com/problems/managers-with-at-least-5-direct-reports/
SELECT m.name
FROM Employee AS m
LEFT JOIN Employee AS e
    ON m.id = e.managerId
WHERE e.managerId IS NOT NULL
GROUP BY m.id
HAVING COUNT(*) >= 5



1934. Confirmation Rate
https://leetcode.com/problems/confirmation-rate/
SELECT 
    s.user_id,
    ROUND(AVG(CASE WHEN c.action = 'confirmed' THEN 1 ELSE 0 END), 2) as confirmation_rate
FROM Signups AS s
LEFT JOIN Confirmations AS c
    ON s.user_id = c.user_id
GROUP BY s.user_id
/*
CASE 用法:

CASE
    WHEN score >= 90 THEN 'A'
    WHEN score >= 80 THEN 'B'
    WHEN score >= 70 THEN 'C'
    ELSE 'F'
END

*/



620. Not Boring Movies
https://leetcode.com/problems/not-boring-movies/
SELECT *
FROM Cinema
WHERE id % 2 = 1 AND description <> 'boring'
ORDER BY rating DESC;



1251. Average Selling Price
https://leetcode.com/problems/average-selling-price/
SELECT 
    p.product_id,
    ROUND(
        COALESCE(
            SUM(p.price * u.units) / SUM(u.units), 0
        ), 
    2) AS average_price
FROM Prices AS p
LEFT JOIN UnitsSold AS u
    ON p.product_id = u.product_id
    AND u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id
/*
COALESCE 可以把它理解成:
從左到右, 回傳第一個不是 NULL 的值

基本用法
COALESCE(value1, value2, value3)

例如:
COALESCE(NULL, 10) -> 10
*/



1075. Project Employees I
https://leetcode.com/problems/project-employees-i/
SELECT p.project_id, ROUND(AVG(experience_years), 2) AS average_years
FROM Project AS p
LEFT JOIN Employee AS e
    ON p.employee_id = e.employee_id
GROUP BY p.project_id;



1633. Percentage of Users Attended a Contest
https://leetcode.com/problems/percentage-of-users-attended-a-contest/
SELECT 
    r.contest_id,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Users), 2) AS percentage
FROM Register AS r
GROUP BY r.contest_id
ORDER BY percentage DESC, r.contest_id



1211. Queries Quality and Percentage
https://leetcode.com/problems/queries-quality-and-percentage/
SELECT 
    query_name,
    ROUND(
        AVG(rating / position),
    2) AS quality,
    ROUND(
        SUM(
            CASE 
                WHEN rating < 3 THEN 1 
                ELSE 0 
            END
        ) * 100.0 / COUNT(*),
    2) AS poor_query_percentage
FROM Queries
GROUP BY query_name;



1193. Monthly Transactions I
https://leetcode.com/problems/monthly-transactions-i/
SELECT
    DATE_FORMAT(trans_date, '%Y-%m') as month,
    country,
    COUNT(*) AS trans_count,
    SUM(CASE
        WHEN state = 'approved' THEN 1
        ELSE 0
    END) AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(CASE
        WHEN state = 'approved' THEN amount
        ELSE 0
    END) AS approved_total_amount
FROM Transactions
GROUP BY 
    DATE_FORMAT(trans_date, '%Y-%m'),
    country
/*
GROUP BY DATE_FORMAT(trans_date, '%Y-%m')
把同一個月份的交易分到同一組

最常用的 format
格式	意思	範例
%Y	4 位數年份	2026
%y	2 位數年份	26
%m	2 位數月份	09
%c	月份，不補 0	9
%d	2 位數日期	14
%e	日期，不補 0	14
%H	24 小時制	17
%i	分鐘	30
%s	秒	45
*/



1174. Immediate Food Delivery II
https://leetcode.com/problems/immediate-food-delivery-ii/
SELECT
    ROUND(
        AVG(CASE 
            WHEN order_date = customer_pref_delivery_date THEN 100
            ELSE 0
        END),
    2) AS immediate_percentage
FROM Delivery
WHERE (customer_id, order_date) IN (
    SELECT 
        customer_id,
        MIN(order_date)
    FROM Delivery
    GROUP BY customer_id
);



550. Game Play Analysis IV
https://leetcode.com/problems/game-play-analysis-iv/
SELECT 
    ROUND(
        (COUNT(*) / (SELECT COUNT(DISTINCT player_id) FROM Activity)),
    2) AS fraction
FROM Activity a
JOIN (
    SELECT
        player_id,
        MIN(event_date) as first_date
    FROM Activity
    GROUP BY player_id
) AS f
    ON a.player_id = f.player_id
WHERE DATEDIFF(a.event_date, f.first_date) = 1



2356. Number of Unique Subjects Taught by Each Teacher
https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/
SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt -- DISTINCT也可以用在count
FROM Teacher
GROUP BY teacher_id;



1141. User Activity for the Past 30 Days I
https://leetcode.com/problems/user-activity-for-the-past-30-days-i/
SELECT 
    activity_date AS day,
    COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) BETWEEN 0 AND 29 -- DATEDIFF 算的是兩個日期相差幾天, 而「包含今天的 30 天」是從 0 算到 29，總共剛好 30 個數字。
GROUP BY activity_date



1070. Product Sales Analysis III
https://leetcode.com/problems/product-sales-analysis-iii/
SELECT
    product_id,
    year AS first_year,
    quantity,
    price
FROM Sales
WHERE (product_id, year) in (
    SELECT
        product_id,
        MIN(year)
    FROM Sales
    GROUP BY product_id
);



596. Classes More Than 5 Students
https://leetcode.com/problems/classes-more-than-5-students/
SELECT class
FROM Courses
GROUP BY class
HAVING COUNT(*) >= 5;



1729. Find Followers Count
https://leetcode.com/problems/find-followers-count/
SELECT user_id, COUNT(*) AS followers_count
FROM Followers
GROUP BY user_id
ORDER BY user_id;



619. Biggest Single Number
https://leetcode.com/problems/biggest-single-number/
SELECT MAX(num) AS num
FROM MyNumbers
WHERE num IN (
    SELECT num
    FROM MyNumbers
    GROUP BY num
    HAVING COUNT(*) = 1
);



1045. Customers Who Bought All Products
https://leetcode.com/problems/customers-who-bought-all-products/
SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = ( -- Customer may contain duplicates rows
    SELECT COUNT(*) FROM Product
)



1731. The Number of Employees Which Report to Each Employee
https://leetcode.com/problems/the-number-of-employees-which-report-to-each-employee/
SELECT 
    m.employee_id,
    m.name,
    COUNT(e.employee_id) AS reports_count,
    ROUND(AVG(e.age), 0) AS average_age
FROM Employees AS m
LEFT JOIN Employees AS e
    ON m.employee_id = e.reports_to
WHERE e.employee_id IS NOT NULL
GROUP BY m.employee_id
ORDER BY m.employee_id



1789. Primary Department for Each Employee
https://leetcode.com/problems/primary-department-for-each-employee/
SELECT employee_id, department_id
FROM Employee
GROUP BY employee_id
HAVING COUNT(*) = 1

UNION ALL

SELECT employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y';



610. Triangle Judgement
https://leetcode.com/problems/triangle-judgement/
SELECT 
    *,
    (CASE
        WHEN (x + y) > z AND (x + z) > y AND (y + z) > x THEN 'Yes'
        ELSE 'No'
    END) AS triangle
FROM Triangle



180. Consecutive Numbers
https://leetcode.com/problems/consecutive-numbers/
SELECT 
    DISTINCT l1.num AS ConsecutiveNums -- 避免連續四次以上會重複
FROM Logs AS l1
LEFT JOIN Logs AS l2
    ON l1.id + 1 = l2.id
LEFT JOIN Logs AS l3
    ON l2.id + 1 = l3.id
WHERE l1.num = l2.num
    AND l2.num = l3.num



1164. Product Price at a Given Date
https://leetcode.com/problems/product-price-at-a-given-date/
SELECT
    product_id,
    new_price AS price
FROM Products
WHERE (product_id, change_date) IN (
    SELECT
        product_id,
        MAX(change_date) AS change_date
    FROM Products
    WHERE change_date <= '2019-08-16' -- 已更新過的取最接近的
    GROUP BY product_id
)

UNION

SELECT
    product_id,
    10 AS price
FROM Products
GROUP BY product_id
HAVING MIN(change_date) > '2019-08-16' -- 從來沒有更新過價格的



1204. Last Person to Fit in the Bus
https://leetcode.com/problems/last-person-to-fit-in-the-bus/
SELECT 
    q.person_name
FROM (
    SELECT 
        person_name,
        SUM(weight) OVER(ORDER BY turn) AS total_weight
    FROM Queue
) AS q
WHERE q.total_weight <= 1000
ORDER BY q.total_weight DESC
LIMIT 1;



1907. Count Salary Categories
https://leetcode.com/problems/count-salary-categories/
SELECT
    'Low Salary' AS category,
    COUNT(*) AS accounts_count
FROM Accounts
WHERE income < 20000

UNION

SELECT
    'Average Salary' AS category,
    COUNT(*) AS accounts_count
FROM Accounts
WHERE 20000 <= income
    AND income <= 50000

UNION

SELECT
    'High Salary' AS category,
    COUNT(*) AS accounts_count
FROM Accounts
WHERE 50000 < income;



1978. Employees Whose Manager Left the Company
https://leetcode.com/problems/employees-whose-manager-left-the-company/
SELECT employee_id
FROM Employees
WHERE salary < 30000
    AND manager_id NOT IN (
        SELECT employee_id
        FROM Employees
    )
ORDER BY employee_id;



626. Exchange Seats
https://leetcode.com/problems/exchange-seats/
SELECT
    CASE
        WHEN id % 2 = 1 THEN
            CASE
                WHEN id = (SELECT MAX(id) FROM Seat) THEN id
                ELSE id + 1
            END
        ELSE id - 1
    END AS id,
    student
FROM Seat
ORDER BY id;



1341. Movie Rating
https://leetcode.com/problems/movie-rating/
(
    SELECT 
        name AS results
    FROM Users AS u
    JOIN MovieRating AS mr
        ON u.user_id = mr.user_id
    GROUP BY u.user_id
    ORDER BY COUNT(*) DESC, u.name
    LIMIT 1
)

UNION ALL -- 避免u.name, m.title 相同會只出現一個

(
    SELECT 
        m.title AS results
    FROM Movies AS m
    JOIN MovieRating AS mr
        ON m.movie_id = mr.movie_id
    WHERE DATE_FORMAT(created_at, '%Y-%m') = '2020-02'
    GROUP BY m.movie_id
    ORDER BY AVG(mr.rating) DESC, m.title
    LIMIT 1
);



1321. Restaurant Growth
https://leetcode.com/problems/restaurant-growth/
WITH DailySum AS (
    SELECT 
        visited_on,
        SUM(amount) AS amount
    FROM Customer
    GROUP BY visited_on
)

SELECT 
    visited_on,
    SUM(amount) OVER (
        ORDER BY visited_on
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) AS amount,
    ROUND(
        AVG(amount) OVER (
            ORDER BY visited_on
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ),
    2) AS average_amount
FROM DailySum
ORDER BY visited_on
LIMIT 1000000 OFFSET 6;



602. Friend Requests II: Who Has the Most Friends
https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/
WITH FriendSum AS (
    SELECT
        requester_id AS id,
        COUNT(requester_id) AS num
    FROM RequestAccepted
    GROUP BY requester_id

    UNION ALL

    SELECT
        accepter_id AS id,
        COUNT(accepter_id) AS num
    FROM RequestAccepted
    GROUP BY accepter_id
)

SELECT
    id,
    SUM(num) AS num
FROM FriendSum
GROUP BY id
ORDER BY num DESC
LIMIT 1;



585. Investments in 2016
https://leetcode.com/problems/investments-in-2016/
SELECT
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM Insurance
WHERE tiv_2015 in (
        SELECT
            tiv_2015
        FROM Insurance
        GROUP BY tiv_2015
        HAVING COUNT(*) > 1
    )
    AND (lat, lon) in (
        SELECT 
            lat,
            lon
        FROM Insurance
        GROUP BY lat, lon
        HAVING COUNT(*) = 1
    );



185. Department Top Three Salaries
https://leetcode.com/problems/department-top-three-salaries/
WITH DepartmentRank AS (
    SELECT 
        d.name AS Department,
        e.name AS Employee,
        e.salary AS Salary,
        DENSE_RANK() OVER ( -- DENSE_RANK: 同名次時不跳號
            PARTITION BY d.id
            ORDER BY salary DESC
        ) AS SalaryRank
    FROM Department AS d
    JOIN Employee AS e
        ON d.id = e.departmentId
)

SELECT
    Department,
    Employee,
    Salary
FROM DepartmentRank
WHERE SalaryRank <= 3;



1667. Fix Names in a Table
https://leetcode.com/problems/fix-names-in-a-table/
SELECT
    user_id,
    CONCAT(
        UPPER(SUBSTRING(name, 1, 1)), -- SUBSTRING(str, pos, len), pos從1開始
        LOWER(SUBSTRING(name, 2))
    ) AS name
FROM Users
ORDER BY user_id;



1527. Patients With a Condition
https://leetcode.com/problems/patients-with-a-condition/
SELECT *
FROM Patients
WHERE conditions like 'DIAB1%' -- DIAB1必須是獨立的condition
    OR conditions like '% DIAB1%';



196. Delete Duplicate Emails
https://leetcode.com/problems/delete-duplicate-emails/
DELETE a
FROM Person a
JOIN Person b -- LEFT JOIN會拉到沒匹配的
    ON a.email = b.email
    AND a.id > b.id



176. Second Highest Salary
https://leetcode.com/problems/second-highest-salary/
SELECT (
    SELECT
        DISTINCT salary AS SecondHighestSalary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;
/*
Scalar Subquery 純量子查詢:
內層查詢會產生一個值, 外層 SELECT 將它當成一個欄位輸出 (強制顯示null)

SELECT (
    SELECT ...
) AS column_name;

*/



1484. Group Sold Products By The Date
https://leetcode.com/problems/group-sold-products-by-the-date/
SELECT
    sell_date,
    COUNT(DISTINCT product) AS num_sold,
    GROUP_CONCAT(
        DISTINCT product
        ORDER BY product
        SEPARATOR ','
    ) AS products
FROM Activities
GROUP BY sell_date;
/*
GROUP_CONCAT(
    [DISTINCT] 欄位名
    [ORDER BY 欄位名 [ASC | DESC]]
    [SEPARATOR 'separator']
)

*/



1327. List the Products Ordered in a Period
https://leetcode.com/problems/list-the-products-ordered-in-a-period/
SELECT
    p.product_name,
    SUM(o.unit) AS unit
FROM Products AS p
JOIN Orders AS o
    ON p.product_id = o.product_id
WHERE DATE_FORMAT(o.order_date, '%Y-%m') = '2020-02'
GROUP BY p.product_id
HAVING SUM(o.unit) >= 100;



1517. Find Users With Valid E-Mails
https://leetcode.com/problems/find-users-with-valid-e-mails/
SELECT *
FROM Users
WHERE mail REGEXP '^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\\.com$'
    AND BINARY mail LIKE '%@leetcode.com'; -- 強制判斷大小寫 (REGEXP 不會判斷)
