import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.swing.*;

class MyFrame extends JFrame implements KeyListener {

	int count = 0;

	MyFrame() {

		setSize(530, 280); // 창 크기 조절

		setTitle("KeyListener");// 창 이름

		setLocation(700, 300);// 창 위치 조정

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField txt = new JTextField();
	
		/*
			e.getKeyChar() : 눌린 키의 문자를 리턴
			e.getKeyCode() : 눌린 키의 아스키코드를 리턴
			e.getModifiers() : Shift, Ctrl, Alt키 인식 각각 1,2,8 리턴
		 */
		
		//KeyListener = 키보드를 눌렀을 때 호출되는 메소드를 가지고 있는 인터페이스
		txt.addKeyListener(new KeyListener()

		{

			@Override
			// keyTyped(KeyEvent e) : 문자를 눌렀을 때 호출, 문자키에만 반응.
			public void keyTyped(KeyEvent e) {

				System.out.println("Keytype   " + e.getKeyChar() + " " + e.getKeyCode() + " " + e.getKeyLocation());

			}

			@Override
			//keyPressed(KeyEvent e) : 키보드를 눌렀을 때 호출, 모든 키보드에 반응.
			public void keyPressed(KeyEvent e) {

				System.out.println("Keypressed" + e.getKeyChar() + " " + e.getKeyCode() + " " + e.getKeyLocation());

			}

			
			@Override
			// keyReleased(KeyEvent e) : 키보드를 땟을 때 호출, 모든 키보드에 반응.
			public void keyReleased(KeyEvent e) {

				System.out.println("Keyreleased" + e.getKeyChar() + " " + e.getKeyCode() + " " + e.getKeyLocation());

			}

		}

		);

		this.add(txt);

		ActionListener buttonListener = new ActionListener()

		{

			@Override

			public void actionPerformed(ActionEvent e) {

			}

		};

		// b.addActionListener(this); //버튼과 리스너 관계 설정.

		setVisible(true); // 프레임 화면에 등장

	}

	@Override

	public void keyTyped(KeyEvent e) {

		// TODO Auto-generated method stub

	}

	@Override

	public void keyPressed(KeyEvent e) {

		// TODO Auto-generated method stub

	}

	@Override

	public void keyReleased(KeyEvent e) {

		// TODO Auto-generated method stub

	}

}

public class Main {

	public static void main(String[] args) {

		MyFrame frame = new MyFrame();

	}

}
