import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

class MyFrame extends JFrame implements ActionListener {
	
	//공의 x,y좌표 
	int x = 0;
	int y = 0;
	
	//바의 y값 초기값. 
	int stick_y=90;
	
	int x_increment = 3;
	int y_increment = 3;

	class MyPanel extends JPanel {
		
		
		//공 
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponents(g);

			g.setColor(Color.RED);
			g.fillOval(x, y, 40, 40);

			x += x_increment;
			y += y_increment;

			if (y >= 330) //아래
				y_increment = -3;

			if (y < 0) //위
				y_increment = 3;

			if (x >= 530) //오른쪽 벽
				x_increment = -3;

			 if (x <= 25 && (y <=(stick_y+60) & (y>=stick_y-60)) ) //왼쪽 벽
		           x_increment = 3;
			  

			
		}

	}

	MyFrame() { // Constructor 생성

		setSize(600, 420);
		setLocation(600, 400);
		setTitle("1p PingPong Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch(code)
				{
				case KeyEvent.VK_UP : 
					stick_y-=4; 
					break;
				case KeyEvent.VK_DOWN  :
					stick_y+=4;
					break;
			
				}
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}} );
		

		Timer t = new Timer(1, this);

		t.start();

		MyPanel p = new MyPanel();

		this.add(p);
		
		setVisible(true);
		
		
		
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		repaint();

	}
	@Override
	public void paint(Graphics g) {
		
		super.paintComponents(g);
		g.setColor(Color.black);
		g.fillRect(0,stick_y, 25, 120); //네모그리기
		
		
		
	}
	
	


}

public class Mine {

	public static void main(String[] args) {

		new MyFrame();

	}

}
