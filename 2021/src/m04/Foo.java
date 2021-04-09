package m04;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static jdk.nashorn.internal.objects.Global.print;


/**
 * @Author: YST
 * @Date: 2021/4/6 0:44
 * @Version: 1.0
 * @Description: 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 * 一个将会调用 first() 方法 一个将会调用 second() 方法 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/print-in-order 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Foo {

    public static volatile int step = 1;

    public Foo() {

    }

//    Semaphore semaphore2 = new Semaphore(0);
//    Semaphore semaphore3 = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
//        semaphore2.release();
        step++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
//        semaphore2.acquire();
        while (step!=2){
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
//        semaphore3.release();
        step++;
    }

    public void third(Runnable printThird) throws InterruptedException {
//        semaphore3.acquire();
        while (step!=3){
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


    public static void main(String[] args) {
        Foo foo = new Foo();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        };
        
        try {
            foo.first(runnable1);
            foo.third(runnable3);
            foo.second(runnable2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
