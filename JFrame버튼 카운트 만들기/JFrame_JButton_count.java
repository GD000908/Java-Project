package pro2;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

class MyFrame extends JFrame implements ActionListener { // implements ActionListener를 사용하면 밑에 있는 ActionListener를 정의할 필요가 없다.
															

	int count = 0;

	MyFrame() {

		setSize(530, 280); // 창 크기 조절
		setTitle("JButton count");// 창 이름
		setLocation(700, 300);// 창 위치 조정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		JLabel l = new JLabel("현재의 카운트 값: [0] ");

		JButton b = new JButton("증가");

		JButton b2 = new JButton("감소");

		JButton b3 = new JButton("리셋");

		p.add(l);
		p.add(b);
		p.add(b2);
		p.add(b3);

		this.add(p);

		ActionListener buttonListener = new ActionListener()

		{

			@Override

			public void actionPerformed(ActionEvent e) {

				String s = e.getActionCommand(); // 버튼에 있는 글자 의미.

				System.out.println(s);

				if (e.getSource() == b)
					count++;

				else if (e.getSource() == b2)
					count--;

				else if (e.getSource() == b3)
					count = 0;

				l.setText("현재의 카운트 값: [" + count + "]");  //라벨 l의 "현재의 카운트 값: [0] "문구에서 count값만큼 []안의 숫자를 바꿔줌

			}

		};

		b.addActionListener(buttonListener);

		b2.addActionListener(buttonListener);

		b3.addActionListener(buttonListener);

		b.addActionListener(this); // 버튼과 리스너 관계 설정.

		setVisible(true); // 프레임 화면에 등장

	}

	@Override  //MyFrame 유형은 상속된 추상 메소드 이므로  ActionListener.actionPerformed(ActionEvent)를 구현해야 

	public void actionPerformed(ActionEvent e) { 

	}

}

public class main {

	public static void main(String[] args) {

		MyFrame frame = new MyFrame();

	}

}
