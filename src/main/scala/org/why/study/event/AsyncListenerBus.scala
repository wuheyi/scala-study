package org.why.study.event

import java.util.concurrent.LinkedBlockingQueue

import org.apache.logging.log4j.scala.Logging

/**
 * Created by wuheyi on 2019/3/29.
 */
class AsyncListenerBus(name: String) extends LifeListenerBus with Logging {

  private val eventQueue = new LinkedBlockingQueue[LifeListenerEvent]()

  def start(): this.type = {
    dispatchThread.start()
    this
  }

  private val dispatchThread = new Thread(s"life-listener-group-$name") {
    setDaemon(true)

    override def run(): Unit = {
      dispatch()
    }
  }

  private def dispatch(): Unit = {
    try {
      var next: LifeListenerEvent = eventQueue.take()
      while (next != AsyncListenerBus.POISON_PILL) {
        super.postToAll(next)
        next = eventQueue.take()
      }
    } catch {
      case ie: InterruptedException =>
        logger.error(s"Stopping listener queue $name.", ie)
    }
  }

  override def post(event: LifeListenerEvent): Unit = {
    eventQueue.add(event)
  }
}

object AsyncListenerBus {
  val POISON_PILL = new LifeListenerEvent {}
}