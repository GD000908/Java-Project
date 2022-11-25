import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.swing.*;

class MyFrame extends JFrame implements KeyListener {

	int loc_x, loc_y;

	int count = 0;

	MyFrame() {

		setSize(600, 600); // 창 크기 조절
		setTitle("KeyListener로 사진 움직이기");// 창 이름
		setLocation(700, 300);// 창 위치 조정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JPanel p = new JPanel(null);
		ImageIcon image = new ImageIcon("C:\\reset.png"); // 사진 위치 설정. 이름까지동일
		JLabel l = new JLabel(image);

		Dimension d = this.getSize();

		l.setSize(100, 100); //l 라벨에 넣은 png파일 사이즈 설정

		loc_x = (int) d.getHeight() / 2;

		loc_y = (int) d.getHeight() / 2;

		l.setLocation(loc_x, loc_y);

		p.add(l);
		l.setIcon(image);

		this.add(p);
		l.setFocusable(true);
		l.requestDefaultFocus();
		
		l.addKeyListener(new KeyListener()
		{
			@Override

			public void keyPressed(KeyEvent e) {
				
				//getKeyCode = 눌린 키의 아스키코드를 리턴 -> code에 리턴값 저장.
				int code = e.getKeyCode();

				switch (code)

				{
				
				//좌우 상하 방향키로 loc_x, loc_y값에 5씩 더하거나 빼서 움직이게 만든다.
				case KeyEvent.VK_UP: 

					l.setLocation(loc_x, loc_y -= 5);

					break;

				case KeyEvent.VK_DOWN:

					l.setLocation(loc_x, loc_y += 5);

					break;

				case KeyEvent.VK_LEFT:

					l.setLocation(loc_x -= 5, loc_y);

					break;

				case KeyEvent.VK_RIGHT:

					l.setLocation(loc_x += 5, loc_y);

					break;

				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub			
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub		
			}
		}

	);
		setVisible(true); // 프레임 화면에 등장
	}

	

}
public class Main {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}

}
