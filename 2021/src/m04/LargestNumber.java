package m04;

import java.util.Arrays;

/**
 * @Author: YST
 * @Date: 2021/4/12 1:00
 * @Version: 1.0
 * @Description: 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。  注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            int max;
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                max = max(nums[index], nums[j]);
                if (max == nums[j]) {
                    index = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;

        }

        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }

        int index = removeHeadZero(sb);

        return sb.substring(index, sb.length());

    }

    /**
     * @Author: YST
     * @Date: 2:16 2021/4/12
     * @Param: [sb]
     * @Return: int
     * @Description: 删除开头的0
     **/
    private int removeHeadZero(StringBuilder sb) {
        int index = sb.length() - 1;
        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * @Author: YST
     * @Date: 2:14 2021/4/12
     * @Param: [num1, num2]
     * @Return: int
     * @Description: 最大值
     **/
    private static int max(int num1, int num2) {
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        int length1 = str1.length();
        int length2 = str2.length();
        int index = 0;
        int commonMultiple = commonMultiple(length1, length2);
        while (index <= commonMultiple) {
            if (str1.charAt(index % length1) > str2.charAt(index % length2)) {
                return num1;
            } else if (str1.charAt(index % length1) < str2.charAt(index % length2)) {
                return num2;
            }
            if (index == length1 - 1 && index == length2 - 1) {
                return num1;
            }
            index++;

        }
        return num1;
    }


    /**
     * @Author: YST
     * @Date: 2:23 2021/4/12
     * @Param: [n, m]
     * @Return: int
     * @Description: 最小公倍数
     **/
    public static int commonMultiple(int n, int m) {
        return n * m / commonDivisor(n, m);
    }

    /**
     * @Author: YST
     * @Date: 2:24 2021/4/12
     * @Param: [n, m]
     * @Return: int
     * @Description: 最小公约数
     **/
    public static int commonDivisor(int n, int m) {
        //辗转相除是用大的除以小的。如果n<m，第一次相当n与m值交换
        while (n % m != 0) {
            int temp = n % m;
            n = m;
            m = temp;
        }
        return m;
    }

    /**
     * @Author: YST
     * @Date: 2:33 2021/4/12
     * @Param: [nums]
     * @Return: java.lang.String
     * @Description: 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/largest-number/solution/zui-da-shu-by-leetcode-solution-sid5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     **/
    public String largestNumber1(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }


    public static void main(String[] args) {

        int[] nums = new int[]{432, 43243};
        System.out.println(new LargestNumber().largestNumber(nums));
    }

}
