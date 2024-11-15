//O(n) O(1)
class Solution151 {
public:
    string reverseWords(string s) {
        // 本題follow up要求 in-place, 空間O(1), 可以透過原地reverse後resize解決
        reverse(s.begin(), s.end());

        int i = 0; // 為了同時搬移單字, 記錄要寫入的點
        for (int l = 0; l < s.length(); ++l) {
            if (s[l] != ' ') { // 新單字的開始
                if (i != 0)
                    s[i++] = ' '; // 不是第一個單字就補上空格

                int r = l;
                while (r < s.length() && s[r] != ' ') {
                    s[i++] = s[r++]; // 動態補上
                }

                reverse(s.begin() + i - (r - l), s.begin() + i); // r - l相當於這次for捕捉的單字長度
                l = r;
            }
        }

        s.resize(i);
        return s;
    }
};
/* ex: s = "good   example"
先reverse全部, 然後再各個單字reverse, 不能有空格, 所以同時做搬移
遇到空格先跳過, 有需碰到單字在輸入, 最後resize輸入的量就好

全部reverse後:
elpmaxe   doog
l
i

透過while迴圈r找到單字右界:
elpmaxe   doog
l      r
       i

交換該單字後, 下一次l從前次r開始:
elpmaxe   doog
       l
       i

...第一個單字完成, 接著第二個

跳過三個空格:
elpmaxe   doog
       i  l

第二輪for迴圈開始, 同時i往前補上一空格:
elpmaxe   doog
        i l

透過while迴圈r找到單字右界, 此時i也重新寫入位置:
elpmaxe doogog
          l   r
            i

交換該單字後:
example goodog
              l
            i

resize(i):
example good
*/