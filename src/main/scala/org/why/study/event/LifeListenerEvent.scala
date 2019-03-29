package org.why.study.event


/**
 * Created by wuheyi on 2019/3/29.
 */
trait LifeListenerEvent {
  /* Whether output this event to the event log */
  protected[event] def logEvent: Boolean = true
}

case class Born(name: String, sex: Int, time: String, hospital: String) extends LifeListenerEvent

case class Study(name: String, school: String, subject: String) extends LifeListenerEvent

case class Work(name: String, company: String, city: String) extends LifeListenerEvent

case class Marry(name: String, time: String, soulmate: String) extends LifeListenerEvent

case class Dead(name: String, time: String) extends LifeListenerEvent
