package org.why.study.concurrentCoding

import java.util.concurrent.locks.ReentrantLock

/**
 * Created by wuheyi on 2019/3/28.
 */
object ReentrantLockStudy {

  def main(args: Array[String]): Unit = {
    val lock = new ReentrantLock()
    var data = 0

    def writer(): Unit = {
      lock.lock()
      try {
        data += 1
      } finally {
        lock.unlock()
      }
    }

    def reader() = {
      lock.lock()
      try {
        println(data)
      } finally {
        lock.unlock()
      }
    }

    writer()
    reader()

  }

}
