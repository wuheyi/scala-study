package org.why.study.event

/**
 * Created by wuheyi on 2019/3/29.
 */
trait LifeListenerInterface {

  def onBorn(event: Born): Unit

  def onStudy(event: Study): Unit

  def onWork(event: Work): Unit

  def onMarry(event: Marry): Unit

  def onDead(event: Dead): Unit

  def onOtherEvent(event: LifeListenerEvent) : Unit

}
