package org.why.study.concurrentCoding.countDownLatch

/**
 * Created by wuheyi on 2019/3/29.
 */
object ServicesCheck {

  def main(args: Array[String]): Unit = {

    val context = ApplicationContext.getOrCreate
    context.start(10)
    val status = context.checkExternalServices()
    println(status)
    context.stop()

  }
}
