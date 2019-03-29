package org.why.study.event

import org.apache.logging.log4j.scala.Logging

/**
 * Created by wuheyi on 2019/3/29.
 */
class LifeLoggerListener extends LifeListenerInterface with Logging {

  override def onBorn(event: Born): Unit = {
    logger.info(s"我来到了这个世界, ${event.toString}")
  }

  override def onStudy(event: Study): Unit = {
    logger.info(s"我在努力的学习, ${event.toString}")
  }

  override def onWork(event: Work): Unit = {
    logger.info(s"我在努力的工作, ${event.toString}")
  }

  override def onMarry(event: Marry): Unit = {
    logger.info(s"我结婚了, ${event.toString}")
  }

  override def onDead(event: Dead): Unit = {
    logger.info(s"我离开了这个世界, ${event.toString}")
  }

  override def onOtherEvent(event: LifeListenerEvent): Unit = {
    logger.info("我干了其他人生大事！")
  }
}
