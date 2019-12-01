package example

import scala.xml._ 

case class Pos(lat: Double, lon: Double) {
  override def toString = s"lat: $lat, lon: $lon"
}

case class Charger(
  id: Int,
  name: String,
  chargerId: Int,
  chargerType: Int,
  state: Int,
  pos: Pos,
  address: String,
  useTime: String
) {
  def this(x: xml.Node) {
    this(
      id = (x \ "statId").text.toInt,
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

implicit class RichXML(x: xml.Node) {
  def toCharger = Charger(
      id = (x \ "statId").text.toInt,
      name = (x \ "statNm").text,
      chargerId = (x \ "chgerId").text.toInt,
      chargerType = (x \ "chgerType").text.toInt,
      state = (x \ "stat").text.toInt,
      pos = Pos((x \ "lat").text.toDouble, (x \ "lng").text.toDouble),
      address = (x \ "addrDoro").text,
      useTime = (x \ "useTime").text
  )    
}

