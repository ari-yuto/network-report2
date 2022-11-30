package enshu10;

import java.awt.Graphics;

public class Rect extends Figure{
	Rect(){}
	@Override public void paint(Graphics g){
		g.setColor(color);
		if(w<0) {
			if(h>0) {
				g.drawRect(x+w, y, -w, h);
			}
			else {
				g.drawRect(x+w, y+h, -w, -h);
			}
		}
		if(w>0) {
			if(h<0) {
				g.drawRect(x, y+h, w, -h);
			}
			else {
				g.drawRect(x, y, w, h);
			}
		}
	}
}
