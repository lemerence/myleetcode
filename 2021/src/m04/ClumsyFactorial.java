package m04;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: YST
 * @Date: 2021/4/5 16:58
 * @Version: 1.0
 * @Description: 笨阶乘
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 */
public class ClumsyFactorial {

    /**
     * @Author: YST
     * @Date: 17:20 2021/4/5
     * @Param: [N]
     * @Return: int
     * @Description:
     **/
    public int clumsy(int N) {
        if (N<=1){
            return N;
        }

        /**定义数组 接收4个一组计算后的结果*/
        List<Integer> firstResult = new ArrayList<>(N / 2 + 1);

        /**分组计算 计算乘除法*/
        int i = N;
        for (; i > 0; i = i - 4) {
            int result = 0;
            if (i < 4) {
                if (i % 4 == 3) {
                    result = i * (i - 1) / (i - 2);
                } else if (i % 4 == 2) {
                    result = i * (i - 1);
                } else {
                    result = i;
                }
                firstResult.add(result);
                break;
            }
            result = i * (i - 1) / (i - 2);
            firstResult.add(result);
            firstResult.add(i - 3);
        }

        /**计算加减法*/
        int result = firstResult.get(0);
        boolean add = true;
        for (int j = 1; j < firstResult.size(); j++) {
            if (add){
                result += firstResult.get(j);
            }else {
                result -= firstResult.get(j);
            }
            add = !add;
        }

        return result;
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(new ClumsyFactorial().clumsy2(10000000));
        long end = System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(end-begin));

    }


    /**
     * @Author: 网友
     * @Date: 18:49 2021/4/5
     * @Param: [N]
     * @Return: int
     * @Description:
     **/
    public int clumsy1(int N) {
        if(N<=2)return N;
        if(N==3)return 6;
        int sum=N*(N-1)/(N-2)+N-3;
        N-=4;
        while(N>=4){
            sum+=(-N*(N-1)/(N-2)+N-3);
            N-=4;
        }
        return sum-clumsy(N);
    }

    /**
     * @Author: LeetCode-Solution
     * @Date: 18:49 2021/4/5
     * @Param: [N]
     * @Return: int
     * @Description: 根据题意，「笨阶乘」没有显式括号，运算优先级是先「乘除」后「加减」。我们可以从 NN 开始，枚举 N - 1N−1、N-2N−2 直到 11 ，枚举这些数的时候，认为它们之前的操作符按照「乘」「除」「加」「减」交替进行。
     *
     * 出现乘法、除法的时候可以把栈顶元素取出，与当前的 NN 进行乘法运算、除法运算（除法运算需要注意先后顺序），并将运算结果重新压入栈中；
     *
     * 出现加法、减法的时候，把减法视为加上一个数的相反数，然后压入栈，等待以后遇见「乘」「除」法的时候取出。
     *
     * 最后将栈中元素累加即为答案。由于加法运算交换律成立，可以将栈里的元素依次出栈相加。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/clumsy-factorial/solution/ben-jie-cheng-by-leetcode-solution-deh2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     **/
    public int clumsy2(int N) {
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(N);
        N--;

        int index = 0; // 用于控制乘、除、加、减
        while (N > 0) {
            if (index % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
            index++;
            N--;
        }

        // 把栈中所有的数字依次弹出求和
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * @Author: LeetCode-Solution
     * @Date: 19:05 2021/4/5
     * @Param: [N]
     * @Return: int
     * @Description: 利用数学简化
    观察「笨阶乘」的前三项，有
     N⋅(N−1)/(N−2)
     =(N*(N-2)+N)/(N-2)
     =N+N/(N-2)
     =N+(N-2+2)/(N-2)
     =N+1+2/(N-2)
     当 N-2 >2 可以舍去
     即 N>4时 前三项为 N+1
     即 N<=4时
     **/
    public int clumsy3(int N) {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }

        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }

}
