package org.why.study.concurrentCoding.executors

import java.util.concurrent.{Executors, TimeUnit}

/**
 * Created by wuheyi on 2019/3/29.
 */
object ScheduledThreadPoolExecutorTest {

  private[this] def r1(idx: Int) = new Runnable {
    override def run(): Unit = {
      println("thread" + idx)
    }
  }


  def main(args: Array[String]): Unit = {

    val scheduledThreadPool = Executors.newScheduledThreadPool(5)

    for (i <- 1 to 5) {
      scheduledThreadPool.scheduleAtFixedRate(r1(i), 0, 5, TimeUnit.SECONDS);
    }

    Thread.sleep(10000);
    System.out.println("Shutting down executor...");
    // 关闭线程池
    scheduledThreadPool.shutdown();

    var isDone = false
    // 等待线程池终止
    do {
      isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
      System.out.println("awaitTermination...");
    } while (!isDone);

    System.out.println("Finished all threads");

  }

}
