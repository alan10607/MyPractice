package leetCode.java;

//add(), find(): O(1) O(n), n = counts.size()
class TwoSum {//Solution170
    private Map<Integer, Integer> counts = new HashMap<>(); // <數字, 出現次數>

    public void add(int number) {
        counts.put(number, counts.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> count : counts.entrySet()) {
            int num1 = count.getKey();
            int num2 = value - count.getKey();
            if ((num1 == num2 && count.getValue() > 1) // 一對數字不同, 確認是否存在另一對
                || (num1 != num2 && counts.containsKey(num2))) { // 一對數字相同, 確認是否存在兩個以上
                    return true;
            }
        }
        return false;
    }
}