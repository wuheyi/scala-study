package org.why.study.cache

/**
 * Created by wuheyi on 2019/2/24.
 */
object CacheStudy {
  def main(args: Array[String]): Unit = {
    WhyCacheManager.init()
    WhyCacheManager.save("wuheyi", 1111)
    val age = WhyCacheManager.get("wuheyi")
    println(age)

  }

}
