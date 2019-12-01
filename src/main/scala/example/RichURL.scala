package example

object RichURL {
  implicit class MoreString(val url: String) {
    import java.net.{HttpURLConnection, URL, URLEncoder}

    def addKey(key: String) = url + "?ServiceKey=" + key

    def addParam(p: String, q: String): String = {
      def encode(s: String) = URLEncoder.encode(s, "UTF-8")

      url + "&" + encode(p) + "=" + encode(q)
    }
    
    def openConnection = {
      val con = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
      con.setRequestMethod("GET")
      con.setRequestProperty("Content-type", "application/json")
      con
    }
  }
}
