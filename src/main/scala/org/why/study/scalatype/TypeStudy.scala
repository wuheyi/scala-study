package org.why.study.scalatype

/**
 * Created by wuheyi on 2019/2/24.
 */
object TypeStudy {

  def main(args: Array[String]): Unit = {
    val teacher = Teacher("wuheyi", 1111)
    PersonManager.getPerson[Teacher](teacher)
  }

}
