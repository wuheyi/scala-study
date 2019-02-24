package org.why.study.util

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.util.control.NonFatal

/**
 * Created by dxy_why on 2018/9/5.
 */
object JacksonUtil {

  private val _mapper = new ObjectMapper()
  _mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  _mapper.registerModule(DefaultScalaModule)

  def toJson[T](obj: T): String = {
    _mapper.writeValueAsString(obj)
  }

  def fromJson[T](json: String, `class`: Class[T]): T = {
    try {
      _mapper.readValue(json, `class`)
    } catch {
      case NonFatal(e) =>
        null.asInstanceOf[T]
    }
  }

  def prettyPrint[T](obj: T): String = {
    _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
  }

}
