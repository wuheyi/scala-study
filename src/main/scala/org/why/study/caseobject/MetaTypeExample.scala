package org.why.study.caseobject

import org.why.study.caseobject.MetaType.{EsType, HBaseType, RedisType}

/**
 * Created by wuheyi on 2019/2/25.
 */
object MetaTypeExample {

  def main(args: Array[String]): Unit = {
    val metaType = MetaType("hbase")
    metaType match {
      case HBaseType => println("hbase")
      case EsType => println("es")
      case RedisType => println("redis")
      case _ => println(metaType)
    }


  }

}
