import javax.swing._
import java.awt.event._

object Converter {
  def main(args: Array[String]) {
    val frame = makeFrame()
  }
  
  def makeFrame(): JFrame = {
    val frame = new JFrame
    frame.setContentPane(new ConverterView)
    frame.setLayout(new BoxLayout(frame.getContentPane, BoxLayout.Y_AXIS))
    frame.setSize(200, 200)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)
    
    frame
  }
}

class ConverterView extends JPanel {
  val field = new JTextField("0.0")
  val result = new JLabel("0.0")
  
  field.addKeyListener(new TyperListener(e => convert()))
  
  addElementsToView()
  
  private def convert() {
    val value = field.getText()
    
    try {
      result.setText((value.toDouble * 1.62).toString)
    }
      
    catch {
      case _ => result.setText("n/a")
    }
  }
  
  private def addElementsToView() {
    this.add(field)
    this.add(result)
  }
}

class TyperListener(private val block: KeyEvent => Unit) extends KeyListener {
  def keyReleased(e: KeyEvent) {
    block(e)
  }

  def keyPressed(e: KeyEvent) {}

  def keyTyped(e: KeyEvent) {}
}