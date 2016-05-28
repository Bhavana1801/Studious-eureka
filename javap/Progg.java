import java.applet.*;
import java.awt.*;

public class  Progg extends Applet{
	public static void main(String[] args) {
		public void paint(Graphics g){
      g.drawRect(300,150,200,100);
      g.setColor(Color.yellow);   
      g.fillRect( 300,150, 200, 100 );
      g.setColor(Color.magenta);
      g.drawString("Rectangle",500,150);
   }
	}
   
}