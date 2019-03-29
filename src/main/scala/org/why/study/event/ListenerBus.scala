package org.why.study.event

import java.util.concurrent.CopyOnWriteArrayList

import scala.collection.JavaConverters._
import org.apache.logging.log4j.scala.Logging

import scala.util.control.NonFatal

/**
 * Created by wuheyi on 2019/3/29.
 */
trait ListenerBus[L <: AnyRef, E] extends Logging {

  private[this] val listeners = new CopyOnWriteArrayList[L]

  /**
   * Add a listener to listen events. This method is thread-safe and can be called in any thread.
   */
  final def addListener(listener: L): Unit = {
    listeners.add(listener)
  }

  /**
   * Remove a listener and it won't receive any events. This method is thread-safe and can be called
   * in any thread.
   */
  final def removeListener(listener: L): Unit = {
    listeners.asScala.find(_ eq listener).foreach { listenerAndTimer =>
      listeners.remove(listenerAndTimer)
    }
  }

  /**
   * Post the event to all registered listeners. The `postToAll` caller should guarantee calling
   * `postToAll` in the same thread for all events.
   */
  def postToAll(event: E): Unit = {
    val iter = listeners.iterator
    while (iter.hasNext) {
      val listener = iter.next()
      try {
        doPostEvent(listener, event)
      } catch {
        case NonFatal(e) =>
          logger.error(s"Listener ${listener} threw an exception", e)
      }
    }
  }

  def post(event: LifeListenerEvent)

  /**
   * Post an event to the specified listener. `onPostEvent` is guaranteed to be called in the same
   * thread for all listeners.
   */
  protected def doPostEvent(listener: L, event: E): Unit

}
