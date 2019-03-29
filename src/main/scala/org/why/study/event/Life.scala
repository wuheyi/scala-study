package org.why.study.event

import java.util.concurrent.Executors

/**
 * Created by wuheyi on 2019/3/29.
 */
object Life {

  def main(args: Array[String]): Unit = {
    LifeContext.startLocal()
    val threadPool = Executors.newFixedThreadPool(5)
    try {
      for(i <- 1 to 5){
        threadPool.execute(new LifeThread("thread"+i))
      }
    }finally {
      threadPool.shutdown()
    }
    Thread.sleep(1000)
  }

  class LifeThread(threadName:String) extends Runnable {
    override def run(): Unit = {
      LifeContext.born("lilei", 1, "20220101", "浙二医")
      LifeContext.born("xiaoming", 1, "20220101", "浙二医")
    }
  }


}
