package org.why.study.concurrentCoding.semaphore

import java.util.concurrent.{Executors, Semaphore, TimeUnit}

/**
 * Created by wuheyi on 2019/3/30.
 */
object SemaphoreTest {

  def main(args: Array[String]): Unit = {

    val semaphore = new Semaphore(5)

    val r1 = new Runnable {
      override def run() = {
        semaphore.acquire()
        println("running " + Thread.currentThread().getName)
        Thread.sleep(3000)
        semaphore.release()
      }
    }

    val r2 = new Runnable {
      override def run() = {
        if (semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
          println("running " + Thread.currentThread().getName)
          Thread.sleep(3000)
          semaphore.release()
        } else {
          println("老子不等了！")
        }

      }
    }

    val executors = Executors.newFixedThreadPool(20)

    try {
      for (i <- 0 until 10) {
        executors.execute(r1)
      }
      for (i <- 0 until 10) {
        executors.execute(r2)
      }
    } finally {
      executors.shutdown()
    }

  }

}
