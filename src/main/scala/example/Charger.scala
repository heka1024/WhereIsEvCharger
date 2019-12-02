package example

import scala.xml._ 
object Charger {
  case class Pos(lat: Double, lon: Double) {
    override def toString = s"lat: $lat, lon: $lon"
  }

  case class Charger(
    id: String,
    name: String,
    chargerId: Int,
    chargerType: Int,  // 01 -> dc chademo | 03 -> 01 + ac3 phase | 06 -> 03 + dc combo
    state: Int,        // 01 -> ~work | 2 -> ready | 3 -> now charging | 4 -> stop | 5 -> checking
    pos: Pos,
    address: String,
    useTime: String
  ) {

  }

  implicit class RichXML(x: xml.Node) {
    def toCharger = Charger(
        id = (x \ "statId").text,
        name = (x \ "statNm").text,
        chargerId = (x \ "chgerId").text.toInt,
        chargerType = (x \ "chgerType").text.toInt,
        state = (x \ "stat").text.toInt,
        pos = Pos((x \ "lat").text.toDouble, (x \ "lng").text.toDouble),
        address = (x \ "addrDoro").text,
        useTime = (x \ "useTime").text
    )
  }
}



