package org.why.study.scalatype

/**
 * Created by wuheyi on 2019/2/24.
 */
class Person(name: String, age: Int) {
  override def toString: String = {
    name + " | " + age
  }
  def profile(): String = {
    "person " + toString
  }
}

case class Student(name: String, age: Int) extends Person(name, age) {
  override def profile(): String = {
    "student " + super.toString
  }
}

case class Teacher(name: String, age: Int) extends Person(name, age) {
  override def profile(): String = {
    "teacher " + super.toString
  }
}