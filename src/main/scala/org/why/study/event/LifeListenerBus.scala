package org.why.study.event

/**
 * Created by wuheyi on 2019/3/29.
 */
trait LifeListenerBus extends ListenerBus[LifeListenerInterface, LifeListenerEvent] {
  /**
   * Post an event to the specified listener. `onPostEvent` is guaranteed to be called in the same
   * thread for all listeners.
   */
  override protected def doPostEvent(listener: LifeListenerInterface,
                                     event: LifeListenerEvent): Unit = {
    event match {
      case e: Born =>
        listener.onBorn(e)
      case e: Study =>
        listener.onStudy(e)
      case e: Work =>
        listener.onWork(e)
      case e: Marry =>
        listener.onMarry(e)
      case e: Dead =>
        listener.onDead(e)
      case _ => listener.onOtherEvent(event)
    }
  }
}
