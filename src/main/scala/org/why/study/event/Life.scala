package org.why.study.event

/**
 * Created by wuheyi on 2019/3/29.
 */
object Life {

  def main(args: Array[String]): Unit = {
    LifeContext.startLocal()
    LifeContext.born("lilei", 1, "20220101", "浙二医")
    LifeContext.born("xiaoming", 1, "20220101", "浙二医")
    LifeContext.born("chumei", 1, "20220101", "浙二医")
    Thread.sleep(100000)
  }

}
