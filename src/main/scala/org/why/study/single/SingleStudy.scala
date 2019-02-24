package org.why.study.single

/**
 * Created by wuheyi on 2019/2/24.
 */
object SingleStudy {
  def main(args: Array[String]): Unit = {
    println(ProxyManager.getOrCreate.read())
  }

}
