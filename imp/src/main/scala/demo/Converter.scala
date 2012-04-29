import javax.swing._
import java.awt.event._

object Converter {
  def main(args: Array[String]) {
    val field = new JTextField("0.0")
    val result = new JLabel("0.0")

    field.addKeyListener(new TyperListener(
      e => result.setText(string2Double(field.getText, d => d * 1.62)) 
    ))

    val frame = makeFrame(200, 100)
    frame.getContentPane().add(field)
    frame.getContentPane().add(result)
    frame.setVisible(true)
  }
  
  private def string2Double(string: String, func: Double => Double): String = {
    try {
      "%.2f".format(func(string.toDouble)).toString
    }
    
    catch {
      case e: NumberFormatException => "n/a"
    }
  }
  
  def makeFrame(width: Int, height: Int): JFrame = {
    val frame = new JFrame
    frame.setLayout(new BoxLayout(frame.getContentPane, BoxLayout.Y_AXIS))
    frame.setSize(width, height)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    
    frame
  }
}

class TyperListener(private val block: KeyEvent => Unit) extends KeyListener {
  def keyReleased(e: KeyEvent) {
    block(e)
  }

  def keyPressed(e: KeyEvent) {}

  def keyTyped(e: KeyEvent) {}
}