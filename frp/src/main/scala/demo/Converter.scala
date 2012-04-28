import com.github.oetzi.echo.Echo._
import com.github.oetzi.echo.EchoApp	
import com.github.oetzi.echo.core._
import com.github.oetzi.echo.display._


object Converter extends EchoApp {
  def setup(args: Array[String]) {
    val field = Field()
    val slider = Slider()
    val result = Text(field.text.map2(slider.value) {
      (v,s) => string2Double(v, d => d * (1 + (s.toDouble / 100)))
    })
    
    val frame = Frame(200, 200, List(
      field,
      slider,
      result
    ))
  }
  
  private def string2Double(string: String, func: Double => Double): String = {
    try {
      "%.2f".format(func(string.toDouble)).toString
    }
    
    catch {
      case e: NumberFormatException => "n/a"
    }
  }
}