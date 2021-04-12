package m04;

import java.util.*;

/**
 * @Author: YST
 * @Date: 2021/4/11 18:04
 * @Version: 1.0
 * @Description: 给你一个整数 n ，请你找出并返回第 n 个 丑数 。  丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * <p>
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 */
public class UglyNumber {

    /**
     * @Author: YST
     * @Date: 22:40 2021/4/11
     * @Param: [n]
     * @Return: int
     * @Description: 暴力 丑数 = 1*2^x*3^y*5^z  x+y+z<n
     **/
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return n;
        }

        List<Double> result = new ArrayList<>();
        for (double i = 0; i <= n; i++) {
            for (double j = 0; j <= n - i; j++) {
                for (double k = 0; k <= n - i - j; k++) {
                    result.add(Math.pow(2, i) * Math.pow(3, j) * Math.pow(5, k));
                }
            }
        }
        Collections.sort(result);
        result.remove(0D);
        return result.get(n - 1).intValue();
    }

    /**
     * @Author: YST
     * @Date: 0:43 2021/4/12
     * @Param: [n]
     * @Return: int
     * @Description: 最小堆
     **/
    public int nthUglyNumber1(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        /**对元素进行自然顺序排序*/
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            /**每次循环弹一个 且PriorityQueue有序 能保证第n次能弹出排好序的第n个元素  */
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    /**避免重复*/
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     * @Author: YST
     * @Date: 0:24 2021/4/12
     * @Param: [n]
     * @Return: int
     * @Description: 三指针
     **/
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }

        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UglyNumber().nthUglyNumber1(4));
    }
}
