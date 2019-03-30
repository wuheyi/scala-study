package org.why.study.concurrentCoding.exchanger

import java.util.concurrent.{Exchanger, Executors, TimeUnit, TimeoutException}

import scala.util.Random

/**
 * Created by wuheyi on 2019/3/30.
 */
object ExchangerTest {

  def main(args: Array[String]): Unit = {

    val exchanger = new Exchanger[String]()

    val executors = Executors.newFixedThreadPool(2)

    val consumer = new Runnable {
      override def run() = {
        var data = "consumer_data"
        while (true) {
          try {
            data = exchanger.exchange(data, 1, TimeUnit.SECONDS)
            println("consume data " + data)
          } catch {
            case e: TimeoutException =>
              println("produce too slow!!")
          }
        }
      }
    }

    val producer = new Runnable {
      override def run() = {
        while (true) {
          val data = "msg " + Random.nextInt(10000)
          exchanger.exchange(data)
          Thread.sleep(Random.nextInt(3) * 1000)
          println("produce data " + data)
        }
      }
    }

    try {
      executors.execute(producer)
      executors.execute(consumer)
    } finally {
      executors.shutdown()
    }




  }

}
