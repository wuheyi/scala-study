package org.why.study.concurrentCoding.countDownLatch

import java.util.concurrent.CountDownLatch

/**
 * Created by wuheyi on 2019/3/29.
 */
class NetworkHealthChecker(latch: CountDownLatch)
  extends BaseHealthChecker("network", latch) {

  override def verifyService(): Unit = {
    println("checking Service " + this.getServiceName())
    try {
      Thread.sleep(5000)
    } catch {
      case e: InterruptedException =>
        e.printStackTrace()
    }
    println(this.getServiceName() + " is Up")
  }
}
