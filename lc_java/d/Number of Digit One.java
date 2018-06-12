/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * <p>
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

/*
 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  
例子:
以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况: 
    case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
    case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次. 
    case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次. 
以上三种情况可以用 一个公式概括:
(a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
*/

public class Solution {
  // count number of 1 on each digit repectively, say for 212, 212 / 100 = 2, so it give you two 10, 010 and 110 then
  // you count how many 10 starting with 210, there are three 210, 211, 212
  public int countDigitOne(int n) {
    int count = 0;
    long factor = 1; // start from geweishu, and its counting factor is 10; counting factor is 100 for shiweishu
    do {
      factor *= 10;
      long high = n / factor;
      long curr = (n - high * factor) / (factor / 10);
      long low = factor == 10 ? 0 : n % (factor / 10);
      count += high * factor / 10;
      if (curr > 1) count += factor / 10;
      if (curr == 1) count += low + 1;
    } while (n >= factor);
    return count;
  }
}
