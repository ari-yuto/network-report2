package enshu10;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Paint4 extends Frame implements MouseListener, MouseMotionListener,ActionListener{
	int x, y;
	ArrayList<Figure> objList;//描画する全オブジェクトを管理する
	CheckboxGroup cbg,ccbg; //メニュー
	Checkbox c1,c2,c3,c4,c5,c6,cc1,cc2,cc3,cc4;//メニュー要素
	Button end,Undo;  // 終了ボタン
	int mode = 0; //描画モード(1: 1 点指定図形2: 2 点指定図形)
	boolean polyLine=false;
	boolean LeftClick=false;
	Figure obj;
	public static void main(String[] args) {
		Paint4 f = new Paint4();
		f.setSize(640, 480);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter() {//ウィンドウを閉じたとき
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}});
		f.setVisible(true); 
		
		if(args.length == 1) f.load(args[0]);
	}
	Paint4() {
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
		c5=new Checkbox("楕円",cbg,false);
		c5.setBounds(560,150,60,30);
		add(c5);
		c6=new Checkbox("折れ線",cbg,false);
		c6.setBounds(560,180,60,30);
		add(c6);
		
		end = new Button("終了"); // 「終了」ボタンの作成
		end.setBounds(560, 320, 60, 30); // 「終了」ボタンの座標設定
		add(end); // 「終了」メニューの追加
		
		Undo=new Button("削除");
		Undo.setBounds(560, 350, 60, 30);
		add(Undo);
		
		//マウス処理の登録
		addMouseListener(this);
		addMouseMotionListener(this);
		// 終了ボタン処理の登録
		end.addActionListener(this);
		//処理ボタンの処理の登録
		Undo.addActionListener(this);
		
		ccbg=new CheckboxGroup();
		cc1=new Checkbox("赤",ccbg,false);
		cc1.setBounds(560, 220, 60, 30);
		add(cc1);
		cc2=new Checkbox("緑",ccbg,false);
		cc2.setBounds(560, 240, 60, 30);
		add(cc2);
		cc3=new Checkbox("青",ccbg,false);
		cc3.setBounds(560, 260, 60, 30);
		add(cc3);
		cc4=new Checkbox("黒",ccbg,false);
		cc4.setBounds(560, 280, 60, 30);
		add(cc4);
	}
	
	public void save(String fname) {
		try {
			FileOutputStream fos =new FileOutputStream(fname);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(objList);
			oos.close();
			fos.close();
		}catch(IOException e){
		}
	}
	@SuppressWarnings("unchecked")
		public void load(String fname){
		try {
			FileInputStream fis = new FileInputStream(fname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			objList = (ArrayList<Figure>)ois.readObject();
			ois.close();
			fis.close();
		} catch(IOException e){
		} catch(ClassNotFoundException e){	
		}
		repaint();
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
		if(e.getSource()==Undo) {
			if(objList.size()>0) {
				objList.remove(objList.size()-1);
				repaint();
			}
		}
		else{
			System.exit(0);
		}
		save("paint.dat");
	}

	public void mousePressed(MouseEvent e) {
		if(LeftClick==false) {
			Checkbox c,cc;
			c = cbg.getSelectedCheckbox();
			cc=ccbg.getSelectedCheckbox();
			switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				x = e.getX();
				y = e.getY();

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
				}else if(c==c4) {
					mode = 2;
					obj = new Line();
				}
				else if(c==c5) {
					mode=2;
					obj=new Ellipse();
				}
				else if(c==c6) {
					mode=2;
					obj=new Polygonal();
					polyLine=true;

				}
				
				//カラーを選択できるボタンを生成
				if (cc == cc1) {
					obj.color=new Color(255,0,0);
				}
				else if (cc == cc2) {
					obj.color=new Color(0,255,0);
				}
				else if (cc == cc3) {
					obj.color=new Color(0,0,255);
				}
				else if (cc == cc4) {
					obj.color=new Color(0,0,0);
				}

				if(obj != null){
					obj.moveto(x,y);
					repaint();
				}
				break;
			}
		}
		
	}
	@Override public void mouseReleased(MouseEvent e) {
		switch(e.getButton()){
		case MouseEvent.BUTTON1:
			if(polyLine==false) {
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
				
			}else {
				if(LeftClick==true) {
					x=e.getX();
					y=e.getY();
					obj.setWH(x - obj.x, y - obj.y);
					objList.add(obj);
					obj = null;
					repaint();
					polyLine=false;
					LeftClick=false;
					mode= 0;
					break;
				}
				//一回目の左クリックが終わった段階でtreuに変換
				LeftClick=true;
			}
			break;
			
		case MouseEvent.BUTTON3:
			if(polyLine==true) {
				
				x=e.getX();
				y=e.getY();
				objList.add(obj);
				obj = null;
				repaint();
				
				obj=new Polygonal();
				
				//以下は折れ線において色を付ける動作である
				Checkbox cc;
				cc=ccbg.getSelectedCheckbox();
				if (cc == cc1) {
					obj.color=new Color(255,0,0);
				}
				else if (cc == cc2) {
					obj.color=new Color(0,255,0);
				}
				else if (cc == cc3) {
					obj.color=new Color(0,0,255);
				}
				else if (cc == cc4) {
					obj.color=new Color(0,0,0);
				}
				
				//折れ線の終点を新しく作った折れ線の始点に代入
				obj.x=x;
				obj.y=y;
				obj.setWH(x - obj.x, y - obj.y);
				
			}break;
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {
		if(polyLine==false) {
			x = e.getX();
			y = e.getY();
			if(mode == 1){
				obj.moveto(x, y);
			}else if(mode == 2){
				obj.setWH(x - obj.x, y - obj.y);
			}
			repaint();
		}else {
			x = e.getX();
			y = e.getY();
			obj.setWH(x - obj.x, y - obj.y);
			repaint();
		}
	}
	@Override public void mouseMoved(MouseEvent e) {
		if(polyLine==true) {
			x = e.getX();
			y = e.getY();
			obj.setWH(x - obj.x, y - obj.y);
			repaint();
		}
	}
}

