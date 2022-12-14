package enshu10;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Paint3 extends Frame implements MouseListener, MouseMotionListener,ActionListener {
	int x, y;
	ArrayList<Figure> objList;//描画する全オブジェクトを管理する
	CheckboxGroup cbg; //メニュー
	Checkbox c1,c2,c3,c4;//メニュー要素
	Button end;  // 終了ボタン
	int mode = 0; //描画モード(1: 1 点指定図形2: 2 点指定図形)
	Figure obj;
	static int rang;
	public static void main(String[] args) {
		Paint3 f = new Paint3();
		f.setSize(640, 480);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter() {//ウィンドウを閉じたとき
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}});
		f.setVisible(true); //ウィンドウの表示
		/*Circle c = new Circle();
	        Circle d =c;
	        c.x=100;
	        System.out.println("c.x="+c.x);
	        System.out.println("d.x="+d.x);*/
	}
	Paint3() {
		objList=new ArrayList<Figure>();
		setLayout(null); 
		// 画面作成
		cbg = new CheckboxGroup(); // Checkbox の集合を作成
		c1 = new Checkbox("丸", cbg, true); // 「丸」メニューの作成
		c1.setBounds(560, 30, 60, 30); // 「丸」メニューの座標設定
		add(c1); // 「丸」メニューの追加
		c2 = new Checkbox("円", cbg, false); // 「円」メニューの作成
		c2.setBounds(560, 60, 60, 30); // 「円」メニューの座標設定
		add(c2); // 「円」メニューの追加
		c3 = new Checkbox("四角", cbg, false);// 「四角」メニューの作成
		c3.setBounds(560, 90, 60, 30); // 「四角」メニューの座標設定
		add(c3); // 「四角」メニューの追加
		c4 = new Checkbox("線", cbg, false); // 「線」メニューの作成
		c4.setBounds(560, 120, 60, 30); // 「線」メニューの座標設定
		add(c4); // 「線」メニューの追加
		end = new Button("終了"); // 「終了」ボタンの作成
		end.setBounds(560, 300, 60, 30); // 「終了」ボタンの座標設定
		add(end); // 「終了」メニューの追加
		//マウス処理の登録
		addMouseListener(this);
		addMouseMotionListener(this);
		// 終了ボタン処理の登録
		end.addActionListener(this);
	}
	@Override public void paint(Graphics g) {
		Figure f;
		for(int i=0;i<objList.size();i++) {
			f=objList.get(i);
			f.paint(g);
		}
		if(mode >= 1) obj.paint(g); // 現在作成中の要素も描画
	}
	@Override public void actionPerformed(ActionEvent e){//終了ボタン
		System.exit(0);
	}
	public void mousePressed(MouseEvent e) {
		Checkbox c;
		x = e.getX();
		y = e.getY();
		c = cbg.getSelectedCheckbox();
		obj = null;
		if(c == c1) {
			mode =1;
			obj = new Dot();
		}else if(c == c2) {
			mode = 2;
			obj = new Circle();
		}else if(c == c3) {
			mode = 2;
			obj = new Rect();
		}else if(c == c4) {
			mode = 2;
			obj = new Line();
		}
		if(obj != null){
			obj.moveto(x,y);
			repaint();
		}
	}
	@Override public void mouseReleased(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		if(mode == 1) obj.moveto(x, y);
		else if(mode == 2) obj.setWH(x - obj.x, y - obj.y);
		if(mode >= 1){
			objList.add(obj);
			obj = null;
		}
		mode= 0;
		repaint();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if(mode == 1){
			obj.moveto(x, y);
		}else if(mode == 2){
			obj.setWH(x -=obj.x, y - obj.y);
		}
		repaint();
	}
	@Override public void mouseMoved(MouseEvent e) {}
}

