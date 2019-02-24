package org.why.study.single

/**
 * Created by wuheyi on 2019/2/24.
 */


class ProxyManager(manager: Manager) {

  // class里面实现对外提供的功能和服务

  // 传入某一种功能和服务的集合，利用构造Manager实现

  def read(): String = {
    manager.read()
  }

}

object ProxyManager {

  // 对应的伴生类里面一般提供apply方法，其他一些中间使用的工具方法等
  // 内部的方法可以放在这里

  private var _instance : ProxyManager = _
  private val _lock = new Object()
  def getOrCreate: ProxyManager = {
    if (_instance == null) {
      _lock.synchronized {
        _instance = new ProxyManager(new MyManager())
      }
    }
    _instance
  }

}
