package org.why.study.scalatype

import org.why.study.util.JacksonUtil

import scala.reflect.runtime.currentMirror
import scala.reflect.runtime.universe._

/**
 * Created by wuheyi on 2019/2/24.
 */
object ReflectStudy {

  case class TypeInfo[V](value: V, valueType: Type)

  case class User(name: String, age: Int) {
    override def toString: String = {
      name + "|" + age
    }
  }

  def main(args: Array[String]): Unit = {

    def getType[T: TypeTag](input: T): String = {
      val typeInfo = TypeInfo(input, typeOf[T])
      var result = "err"
      if (typeInfo.valueType <:< typeOf[Map[String, Any]]) {
        result = "map"
      }
      if (input.isInstanceOf[Array[_]]) {
        result = "array"
      }
      if (typeInfo.valueType =:= typeOf[String]) {
        result = "string"
      }

      result
    }

    println(getType(Map[String, Long]("111" -> 222L)))
    println(getType(Array("111", "3333")))
    println(getType("dddd"))
    println(getType(3333L))


    val wuheyi = User("wuheyi", 18)
    val jsonData = JacksonUtil.toJson(wuheyi)
    val user = JacksonUtil.fromJson(jsonData, classOf[User])
    println(user.toString)

    val userType = typeOf[User]
    val clz: Class[_] = currentMirror.runtimeClass(userType)
    val newuser = JacksonUtil.fromJson(jsonData, clz)
    println(newuser.toString)





  }

}
