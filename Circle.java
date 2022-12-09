package enshu10;

import java.awt.Graphics;

public class Circle extends Figure{
	Circle(){}
	@Override public void paint(Graphics g){
		int r = (int)Math.sqrt((double)(w * w + h * h));
		g.setColor(color);
		if(status==true) {
			g.fillOval(x - r, y - r, r * 2 , r * 2);
		}
		else{
			g.drawOval(x - r, y - r, r * 2 , r * 2);
		}
	}
}
