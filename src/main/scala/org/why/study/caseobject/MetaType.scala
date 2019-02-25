package org.why.study.caseobject

/**
 * Created by wuheyi on 2019/2/25.
 */
sealed trait MetaType {
  val metaType : String
}

object MetaType {
  case object HBaseType extends MetaType {
    override val metaType: String = "hbase"
  }
  case object EsType extends MetaType {
    override val metaType: String = "es"
  }
  case object RedisType extends MetaType {
    override val metaType: String = "redis"
  }

  case class UnKnowType(override val metaType: String = "unknowType") extends MetaType

  def apply(metaType: String): MetaType = {
    metaType match {
      case HBaseType.metaType => HBaseType
      case EsType.metaType => EsType
      case RedisType.metaType => RedisType
      case _ => UnKnowType(metaType)
    }
  }
  
}
