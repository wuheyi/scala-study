package org.why.study.concurrentCoding.semaphore

import java.util.concurrent.{Executors, Semaphore, TimeUnit}

/**
 * Created by wuheyi on 2019/3/30.
 */
object SemaphoreLockTest {

  def main(args: Array[String]): Unit = {

    val semaphore = new Semaphore(1)

    val r1 = new Runnable {
      override def run() = {
        semaphore.acquire()
        println("running " + Thread.currentThread().getName)
        Thread.sleep(3000)
        semaphore.release()
      }
    }


    val executors = Executors.newFixedThreadPool(10)

    try {
      for (i <- 0 until 10) {
        executors.execute(r1)
      }
    } finally {
      executors.shutdown()
    }

  }

}
