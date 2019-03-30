package org.why.study.concurrentCoding.atomic

import java.util.concurrent.atomic._

/**
 * Created by wuheyi on 2019/3/30.
 */
object AtomicTest {


  def main(args: Array[String]): Unit = {

    val atomicBoolean = new AtomicBoolean(true)
    atomicBoolean.compareAndSet(true, false)
    println(atomicBoolean.get())
    println("------------")


    val atomicInteger = new AtomicInteger(1)
    atomicInteger.addAndGet(3)
    println(atomicInteger.get())
    atomicInteger.decrementAndGet()
    println(atomicInteger.get())
    println(atomicInteger.getAndIncrement())
    atomicInteger.compareAndSet(4, 10)
    println(atomicInteger.get())
    println("------------")

    val atomicArray = new AtomicIntegerArray(Array(1,2,3))
    println(atomicArray.addAndGet(0, 3))
    println(atomicArray.getAndIncrement(0))
    println("------------")


  }

}
