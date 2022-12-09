package enshu10;

import java.awt.Graphics;

public class Polygonal extends Figure{
	Polygonal(){}
	@Override public void paint(Graphics g){
		g.setColor(color);
		g.drawLine(x,y, x + w, y + h);
	}
}
