package org.why.study.cache

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by wuheyi on 2019/2/24.
 */
class WhyCache[K, V] {
  protected val _cache = new ConcurrentHashMap[K, V]()
  def get(key: K): Option[V] = {
    Option(_cache.get(key))
  }

  def save(key: K, value: V): Unit = {
    _cache.put(key, value)
  }

}
