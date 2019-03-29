package org.why.study.concurrentCoding.executors

import java.util.concurrent.{Callable, Executors}

/**
 * Created by wuheyi on 2019/3/29.
 */
object FixedThreadPoolTest {

  private[this] def c1(data: String) = new Callable[String] {
    override def call(): String = "c1 " + data
  }

  private[this] def c2(data: String) = new Callable[String] {
    override def call(): String = "c2 " + data
  }

  private[this] def r1() = new Runnable {
    override def run(): Unit = {
      println("r1")
    }
  }

  class ThreadDemo(threadName: String) extends Runnable {
    override def run(): Unit = {
      println("running!")
    }
  }

  def main(args: Array[String]): Unit = {
    val threadPool = Executors.newFixedThreadPool(5)
    try {
      for (i <- 1 to 5) {
        threadPool.execute(new ThreadDemo("thread" + i))
        threadPool.execute(new Thread(r1, "r1 thread" + i))
      }
      val feature1 = threadPool.submit(c1("hello"))
      val feature2 = threadPool.submit(c2("world"))
      println(feature1.get())
      println(feature2.get())
    } finally {
      threadPool.shutdown()
    }

  }

}
