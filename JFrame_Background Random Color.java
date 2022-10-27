package homework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MyFrame extends JFrame implements ActionListener { // implements ActionListener를 사용하면 밑에 있는 ActionListener를 정의할
															// 필요가 없다.
	//extends는 부모의 메소드를 그대로 사용할 수 있으며 오버라이딩 할 필요 없이 부모에 구현되있는 것을 직접 사용 가능하다.
	// extends와implement는 둘다 상속이지만 차이점은 implement는 밑에 오버리이딩(재정의)를 해주어야 한다.
	

	MyFrame() {

		setSize(530, 280); // 창 크기 조절
		setTitle("Backgroung Random color");// 창 이름
		setLocation(700, 300);// 창 위치 조정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();
		JButton b1 = new JButton("노랑");
		JButton b2 = new JButton("빨강");
		JButton b3 = new JButton("파랑");
		JButton b4 = new JButton("랜덤");

		p.add(b1); 
		p.add(b2);
		p.add(b3);
		p.add(b4);

		this.add(p);

		ActionListener buttonListener = new ActionListener()

		{

			@Override

			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand())

				{

				case "노랑":
					p.setBackground(Color.yellow);

					break;

				case "빨강":
					p.setBackground(Color.RED);

					break;

				case "파랑":
					p.setBackground(Color.BLUE);

					break;

				case "랜덤":
					float r = (float) (Math.random() * 255);

					float g = (float) (Math.random() * 255);

					float b = (float) (Math.random() * 255);

					Color c = Color.getHSBColor(r, g, b);

					p.setBackground(c);
					break;

				}
			}
		};

		b1.addActionListener(buttonListener);
		b2.addActionListener(buttonListener);
		b3.addActionListener(buttonListener);
		b4.addActionListener(buttonListener);

		setVisible(true); // 프레임 화면에 등장

	}
	@Override
	public void actionPerformed(ActionEvent e) { 

	}

}

public class main {

	public static void main(String[] args) {

		MyFrame frame = new MyFrame();

	}

}
