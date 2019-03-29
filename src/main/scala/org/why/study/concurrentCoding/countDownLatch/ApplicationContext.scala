package org.why.study.concurrentCoding.countDownLatch

import java.util
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.{CountDownLatch, ExecutorService, Executors}

/**
 * Created by wuheyi on 2019/3/29.
 */
class ApplicationContext {

  private[countDownLatch] var _latch: CountDownLatch = _
  private[countDownLatch] var _services: util.ArrayList[BaseHealthChecker] = _
  private[countDownLatch] var _executor: ExecutorService = _
  private[countDownLatch] val started = new AtomicBoolean(false)

  private[countDownLatch] def start(coreSize: Int) = {
    if (started.compareAndSet(false, true)) {
      _latch = new CountDownLatch(2)
      _services = new util.ArrayList[BaseHealthChecker]()
      _services.add(new NetworkHealthChecker(_latch))
      _services.add(new CacheHealthChecker(_latch))
      _executor = Executors.newFixedThreadPool(coreSize)
      for (i <- 0 until _services.size()) {
        _executor.execute(_services.get(i))
      }
      println(_latch.getCount)
      _latch.await()
      println(_latch.getCount)
    } else {
      throw new IllegalStateException(s"already started!")
    }
  }

  private[countDownLatch] def checkExternalServices(): Boolean = {
    if (started.get()) {
      for (i <- 0 until _services.size()) {
        if (!_services.get(i).isServiceUp()) {
          return false
        }
      }
      return true
    } else {
      throw new IllegalStateException(s"has not started!")
    }
  }

  private[countDownLatch] def stop() = {
    if (started.compareAndSet(true, false)) {
      _executor.shutdown()
    }

  }

}

object ApplicationContext {

  @volatile private var _instance: ApplicationContext = _
  private val _lock = new Object()

  def getOrCreate: ApplicationContext = {
    if (_instance == null) {
      _lock.synchronized {
        if (_instance == null) {
          _instance = new ApplicationContext()
        }
      }
    }
    _instance
  }

}
