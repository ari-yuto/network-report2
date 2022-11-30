package enshu10;

import java.awt.Color;
import java.awt.Graphics;

public class Figure extends Coord{
	Color color;
	int w,h;
	Figure(){
		color =new Color(0,0,0);
		w=h=0;
	}
	public void paint(Graphics g) {}
	public void setWH(int w, int h){
		this.w = w;
		this.h = h;
	}
}

