package upickle

import rx._
import utest._

object RxTests {

  case class Wat(w: String)

  object Wat {
    implicit val pklR = upickle.default.macroR[Wat]
    implicit val pklW = upickle.default.macroW[Wat]
  }
  def getWat(str: String) = upickle.default.read[Wat](str)

  val tmp: Rx[Wat] = Rx { Wat("wat") }

  val tmpRx: Rx[Wat] = tmp.map { w =>
    //getWat("""{"w":"wat"}""") //this works
    upickle.default.read[Wat]("""{"w":"wat"}""") //compile time error
  }
}

//object RxTest extends TestSuite {
//  case class Wat(w: String)
//
//  val tmp: Rx[Wat] = Rx { Wat("Wat") }
//
//  val responseRedditRx2: Rx[Wat] = tmp.map { w =>
//    upickle.default.read[Wat]("""{"w":"wzz"}""")
//  }
//
//  val tests = TestSuite {
//    "wat" - {
//      println(upickle.default.write(Wat("wzz")))
//      println(responseRedditRx2.now)
//    }
//  }
//}