package org.why.study.scalatype

import scala.reflect.runtime.universe._

/**
 * Created by wuheyi on 2019/2/24.
 */
object PersonManager {

  def getPerson[T <: Person: TypeTag](person1: Person) = {
    if (typeOf[T] =:= typeOf[Student]) {
      showProfile(person1)
    }
  }

  private def showProfile(person: Person): Unit = {
    println(person.profile())
  }


}
