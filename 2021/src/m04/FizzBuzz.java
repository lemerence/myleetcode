package m04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Author: YST
 * @Date: 2021/4/6 1:56
 * @Version: 1.0
 * @Description: 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FizzBuzz {

    Semaphore fizzSemaphore = new Semaphore(0);
    Semaphore buzzSemaphore = new Semaphore(0);
    Semaphore fizzbuzzSemaphore = new Semaphore(0);
    Semaphore numberSemaphore = new Semaphore(1);

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i = i + 3) {
            if (i % 5 != 0) {
                fizzSemaphore.acquire();
                printFizz.run();
                numberSemaphore.release();
            }

        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i = i + 5) {
            if (i % 3 != 0) {
                buzzSemaphore.acquire();
                printBuzz.run();
                numberSemaphore.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i = i + 15) {
            fizzbuzzSemaphore.acquire();
            printFizzBuzz.run();
            numberSemaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numberSemaphore.acquire();
            if (i%3==0&&i%5!=0){
                fizzSemaphore.release();
            }else if (i%3!=0&&i%5==0){
                buzzSemaphore.release();
            }else if (i%3==0&&i%5==0){
                fizzbuzzSemaphore.release();
            }else {
                printNumber.accept(i);
                numberSemaphore.release();
            }
        }
    }

}
