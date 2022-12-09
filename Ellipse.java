package enshu10;

import java.awt.Graphics;

public class Ellipse extends Figure{
	Ellipse(){}
	@Override public void paint(Graphics g){
		g.setColor(color);
		if(w>0) {
			if(h>0) {
				g.drawOval(x - (int)(Math.sqrt(2)*w), y - (int)(Math.sqrt(2)*h), (int)(Math.sqrt(2)*w) * 2 , (int)(Math.sqrt(2)*h) * 2);
			}
			else {
				g.drawOval(x - (int)(Math.sqrt(2)*w), y - (int)(Math.sqrt(2)*(-h)), (int)(Math.sqrt(2)*w) * 2 , (int)(Math.sqrt(2)*(-h)) * 2);
			}
		}
		if(w<0){
			if(h>0) {
				g.drawOval(x - (int)(Math.sqrt(2)*(-w)), y - (int)(Math.sqrt(2)*h), (int)(Math.sqrt(2)*(-w)) * 2 , (int)(Math.sqrt(2)*h) * 2);
			}
			else {
				g.drawOval(x - (int)(Math.sqrt(2)*(-w)), y - (int)(Math.sqrt(2)*(-h)), (int)(Math.sqrt(2)*(-w)) * 2 , (int)(Math.sqrt(2)*(-h)) * 2);
			}
		}
		
	}
}
