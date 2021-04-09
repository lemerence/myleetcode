package m04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: YST
 * @Date: 2021/4/7 1:35
 * @Version: 1.0
 * @Description: 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 * 所有的哲学家都只会在思考和进餐两种行为间交替。
 * 哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。
 * 每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。
 * 只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/the-dining-philosophers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DiningPhilosophers {

    private AtomicInteger fork0 = new AtomicInteger();
    private AtomicInteger fork1 = new AtomicInteger();
    private AtomicInteger fork2 = new AtomicInteger();
    private AtomicInteger fork3 = new AtomicInteger();
    private AtomicInteger fork4 = new AtomicInteger();

    public DiningPhilosophers() {

    }

    /**
     * @Author: YST
     * @Date: 1:36 2021/4/7
     * @Param: [philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork]
     * @Return: void
     * @Description:    philosopher 哲学家的编号。从 0 到 4 按 顺时针 编号
     *                  pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
     *                  eat 表示吃面。
     *                  putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
     **/
    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        if (philosopher==0){
            int oraginFork0 = fork0.getAndIncrement();
            int oraginFork4 = fork4.getAndIncrement();
            if (oraginFork0==0&&oraginFork4==0){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork0.decrementAndGet();
                fork4.decrementAndGet();
            }else {
                fork0.decrementAndGet();
                fork4.decrementAndGet();
            }
        }else if (philosopher==1){
            int oraginFork0 = fork0.getAndIncrement();
            int oraginFork1 = fork1.getAndIncrement();
            if (oraginFork0==0&&oraginFork1==0){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork0.decrementAndGet();
                fork1.decrementAndGet();
            }else {
                fork0.decrementAndGet();
                fork1.decrementAndGet();
            }
        }else if (philosopher==2){
            int oraginFork2 = fork2.getAndIncrement();
            int oraginFork1 = fork1.getAndIncrement();
            if (oraginFork2==0&&oraginFork1==0){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork2.decrementAndGet();
                fork1.decrementAndGet();
            }else {
                fork2.decrementAndGet();
                fork1.decrementAndGet();
            }
        }else if (philosopher==3){
            int oraginFork3 = fork3.getAndIncrement();
            int oraginFork2 = fork2.getAndIncrement();
            if (oraginFork2==0&&oraginFork3==0){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork3.decrementAndGet();
                fork2.decrementAndGet();
            }else {
                fork3.decrementAndGet();
                fork2.decrementAndGet();
            }
        }else if (philosopher==4){
            int oraginFork3 = fork3.getAndIncrement();
            int oraginFork4 = fork4.getAndIncrement();
            if (oraginFork3==0&&oraginFork4==0){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork3.decrementAndGet();
                fork4.decrementAndGet();
            }else {
                fork3.decrementAndGet();
                fork4.decrementAndGet();
            }
        }


    }

}
