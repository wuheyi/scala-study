package org.why.study.concurrentCoding.cyclicBarrier

import java.util.concurrent._

/**
 * Created by wuheyi on 2019/3/30.
 */
object CyclicBarrierTest {

  def main(args: Array[String]): Unit = {

    val results = new ConcurrentHashMap[String, Int]()

    val r = new Runnable {
      override def run() = {
        println("正在执行线程r")
        println(results.toString)
      }
    }

    val barrier = new CyclicBarrier(2, r)

    val executors = Executors.newFixedThreadPool(3)

    val r1 = new Runnable {
      override def run(): Unit = {
        println("正在执行线程r1")
        results.put("r1", 1)
        try {
          barrier.await()
        } catch {
          case e: InterruptedException =>
            e.printStackTrace()
          case e: BrokenBarrierException =>
            e.printStackTrace()
        }
      }
    }

    val r2 = new Runnable {
      override def run(): Unit = {
        println("正在执行线程r2")
        results.put("r2", 2)
        try {
          barrier.await()
        } catch {
          case e: InterruptedException =>
            e.printStackTrace()
          case e: BrokenBarrierException =>
            e.printStackTrace()
        }
      }
    }

    def run() = {
      try {
        executors.execute(r1)
        executors.execute(r2)
      } finally {
        executors.shutdown()
      }
    }

    run()

  }
}
