package org.why.study.enumeration

/**
 * Created by wuheyi on 2019/2/24.
 */
object EnumerationStudy {

  def main(args: Array[String]): Unit = {
    println(MetaType.HBase)
    type StringType = String
    def test(a: StringType, b: MetaType.MetaType): StringType = {
      val metaType = b match {
        case MetaType.HBase => "hbase"
        case MetaType.Es => "es"
        case MetaType.Redis => "redis"
        case _ => "err"
      }
      a + " | " + metaType
    }
    println(test("wuheyi", MetaType.HBase))

  }

}
