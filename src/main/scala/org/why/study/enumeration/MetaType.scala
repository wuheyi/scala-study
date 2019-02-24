package org.why.study.enumeration

/**
 * Created by wuheyi on 2019/2/24.
 */
object MetaType extends Enumeration {
  val HBase, Es, Redis = Value
  type MetaType = Value
}
