package org.why.study.implict

/**
  * Created by wuheyi on 2018/12/22.
  */
object ImplictStudy {

  def main(args: Array[String]): Unit = {

    println("implict study!!")

    // 隐式转换
    implicit def int2intdata(a: Int): IntData = {
      IntData(a)
    }
    IntData.show(IntData(2), 3)

    implicit def intdata2newintdata(a: IntData) = new NewIntData
    IntData(2).display()

    IntData(2).display2()

    // 如果能不通过隐式转换就能通过编译，则不会进行隐式转换


    // 隐式参数
    def magic(info: String)(implicit ev: Message): Unit = {
      println(ev.msg + ":" + info)
    }
    // 使用起来有点丑陋
    magic("aaaa")(Message("xxxx"))

    // 编译器会从当前作用域的val和def查找隐式值
    implicit val defaultMessage = Message("yyyy")
    magic("bbbb")


    def smaller[T](a: T, b: T)(implicit ev: T => Ordered[T]): T = {
      if (a < b) a else b
    }

    def add[T](a: T, b: T)(implicit ev: Numeric[T]): T = {
      ev.plus(a, b)
    }
    println(add(1, 2))


    // 类型类的使用
    def algoSort[C, T](data: Map[C, T])(implicit ev: Algo[C, T]): Unit = {
      println(ev.sort(data).mkString(","))
    }
    val mapStringDouble = Map("aaa" -> 1.1, "bbb" -> 2.2, "ccc" -> 0.1)
    import org.why.study.implict.Algo.AlgoStringDouble.sort
    algoSort(mapStringDouble)
    val mapStringInt = Map("aaa" -> 1, "bbb" -> 2, "ccc" -> 0)
    import org.why.study.implict.Algo.AlgoStringInt.sort
    algoSort(mapStringInt)
    val matStringInfo = Map("aaa" -> Info("", "", 1), "bbb" -> Info("", "", 1))
    import org.why.study.implict.Info.AlgoStringInfo.sort
    algoSort(matStringInfo)

  }

}

class Info(msg: String, content: String, code: Int) {

}
object Info {
  def apply(msg: String, content: String, code: Int): Info = new Info(msg, content, code)
  implicit object AlgoStringInfo extends Algo[String, Info] {
    override def sort(data: Map[String, Info]): Array[String] = {
      Array("xxxx")
    }
  }
}

// 一个带有排序功能的算法特质  类型类
// 能实现多态的效果
trait Algo[C, T] {
  def sort(data: Map[C, T]): Array[C]
}

object Algo {
  implicit object AlgoStringDouble extends Algo[String, Double] {
    override def sort(data: Map[String, Double]): Array[String] = {
      data.toArray.sortWith(_._2 < _._2).map(_._1)
    }
  }
  implicit object AlgoStringInt extends Algo[String, Int] {
    override def sort(data: Map[String, Int]): Array[String] = {
      data.toArray.sortWith(_._2 < _._2).map(_._1)
    }
  }

}


case class Message(msg: String)



class IntData(var data: Int) {

}

object IntData {
  def apply(data: Int): IntData = new IntData(data)
  def show(a: IntData, b: IntData): Unit = {
    println(a.data + b.data)
  }
  // 隐式类，不能放在顶层，且必须有单入参的构造函数，该构造函数自动成为隐式转换
  // 最好放到伴生对象中，不能放到伴生类中
  // 隐式类一般用于为类增加新的功能，非常灵活
  implicit class NewIntData2(a: IntData) {
    def display2(): Unit = {
      println("newIntData2")
    }
  }
}


class NewIntData {
  def display(): Unit = {
    println("newIntData")
  }
}

