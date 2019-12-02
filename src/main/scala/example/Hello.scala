package example

import example.RichURL._
import example.Charger._

// use xml
object EvCharger extends App {
  import scala.xml._ 
  def find(query: String) = {
    
  }
  
  val serviceKey = "%2FMaA6%2Ft6LKPv04auLFZf0l44%2Bp0OiAZ35nbgfb2RHDlP%2B0xAG6%2BIQBf2rDNjBefdS%2BHpqhZ1T%2FIxER19amno1A%3D%3D"
  val con = (
    "http://open.ev.or.kr:8080/openapi/services/rest/EvChargerService" 
     addKey serviceKey
  ).openConnection
  val cs = (xml.XML.load(con.getInputStream) \\ "item").map(_.toCharger)
  val daegu = cs filter (_.address contains "대구")
  daegu.foreach(println)
}

object AirKorea {
  val serviceKey = "%2FMaA6%2Ft6LKPv04auLFZf0l44%2Bp0OiAZ35nbgfb2RHDlP%2B0xAG6%2BIQBf2rDNjBefdS%2BHpqhZ1T%2FIxER19amno1A%3D%3D"
  val con = (
    "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst" 
     addKey serviceKey 
     addParam ("sidoName", "서울")
     addParam("searchCondition", "DAILY")
     addParam("_returnType", "json")
  ).openConnection
  val u = scala.io.Source.fromInputStream(con.getInputStream).getLines()
  u.foreach(println)
}