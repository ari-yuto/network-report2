package enshu10;

import java.awt.Graphics;

public class Rect extends Figure{
	Rect(){}
	@Override public void paint(Graphics g){
		g.drawRect(x, y, w, h);
	}
}
