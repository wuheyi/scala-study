package org.why.study.cache

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by wuheyi on 2019/2/24.
 */
object WhyCacheManager {
  private var _cache : WhyCache[String, Int] = _
  protected val _init = new AtomicBoolean(false)
  protected val _lock = new Object()
  def init(): Unit = {
    if (_cache == null) {
      _lock.synchronized {
        _cache = new WhyCache[String, Int]()
        _init.set(true)
      }
    }
  }

  def save(key: String, value: Int) : Unit = {
    _cache.save(key, value)
  }

  def get(key: String, default: Int = 0): Int = {
    _cache.get(key).getOrElse(default)
  }

}
