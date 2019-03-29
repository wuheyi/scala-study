package org.why.study.event

import org.apache.logging.log4j.scala.Logging

/**
 * Created by wuheyi on 2019/3/29.
 */
class LifeContext {

  private[event] var _listenerBus: LifeListenerBus = _

  def start(): Unit = {
    _listenerBus = new AsyncListenerBus("life-server").start()
    registeListener
  }

  private def registeListener: Unit = {
    val consolePrintLifeListener = new LifeLoggerListener()
    _listenerBus.addListener(consolePrintLifeListener)
  }

}

object LifeContext extends Logging {
  private[this] val lifeContext = new LifeContext()

  def startLocal(): Unit = {
    logger.info("我是造物主，我醒了！")
    lifeContext.start()
  }

  def born(name: String, sex: Int, time: String, hospital: String) = {
    lifeContext._listenerBus.post(Born(name, sex, time, hospital))
  }

  def study(name: String, school: String, subject: String) = {
    lifeContext._listenerBus.post(Study(name, school, subject))
  }

  def work(name: String, company: String, city: String) = {
    lifeContext._listenerBus.post(Work(name, company, city))
  }

  def marry(name: String, time: String, soulmate: String) = {
    lifeContext._listenerBus.post(Marry(name, time, soulmate))
  }

  def dead(name: String, time: String) = {
    lifeContext._listenerBus.post(Dead(name, time))
  }

}
