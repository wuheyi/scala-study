package org.why.study.concurrentCoding.countDownLatch

import java.util.concurrent.CountDownLatch

/**
 * Created by wuheyi on 2019/3/29.
 */
abstract class BaseHealthChecker(serviceName: String, latch: CountDownLatch) extends Runnable {

  private var serviceUp: Boolean = _

  override def run() = {
    try {
      verifyService()
      serviceUp = true
    } catch {
      case t: Throwable =>
        t.printStackTrace(System.err)
        serviceUp = false
    } finally {
      if(latch != null) {
        latch.countDown()
      }
    }
  }

  def verifyService(): Unit

  def getServiceName(): String = serviceName

  def isServiceUp(): Boolean = serviceUp

}
