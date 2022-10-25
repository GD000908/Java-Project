// C드라이브에 가위,바위,보, 물음표 사진이 저장되어있어야 실행 가능.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

class MyFrame extends JFrame implements ActionListener { 
	MyFrame() {

		setSize(560, 450); // 창 크기 조절
		setTitle("박건도 20191093");// 창 이름
		setLocation(700, 300);// 창 위치 조정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image0 = new ImageIcon("C://Rock.png"); // 바위
		ImageIcon image1 = new ImageIcon("C://paper.png"); // 보자기
		ImageIcon image2 = new ImageIcon("C://scissor.png"); // 가위
		ImageIcon image3 = new ImageIcon("C://q.png"); // 물음표 사진

		JPanel main = new JPanel(); // 전체 패널
		JPanel uppanel = new JPanel(); // 위 패널
		JPanel bottompanel = new JPanel();
		JPanel downpanel = new JPanel(); // 아래패널

		JLabel label = new JLabel(image3); // 물음표 사진

		JLabel label1 = new JLabel("누가 이겼을까요?"); 
		label1.setFont(new Font("Serif", Font.BOLD, 11)); //라벨 텍스트 굵기 설정
		label1.setFont(label1.getFont().deriveFont(20.0f)); // 라벨 텍스트 폰트 설정

		JButton rock = new JButton(image0); // 바위 그림
		JButton paper = new JButton(image1); // 보 그림
		JButton scissor = new JButton(image2); // 가위 그림

		uppanel.add(label); // 위 패널add

		downpanel.add(paper);
		downpanel.add(scissor);
		downpanel.add(rock);
		bottompanel.add(label1);// 아래 패널 add

		main.add(uppanel, BorderLayout.NORTH); // 메인 패널에 각각의 패널을 덧붙여 넣는다.
		main.add(downpanel, BorderLayout.SOUTH);
		main.add(bottompanel, BorderLayout.SOUTH);

		this.add(main); //메인패널 add

		ActionListener buttonListener = new ActionListener()

		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = 1;
				int s = 0;
				int p = 2;
				Random random = new Random(); 
				int computer = random.nextInt(3); // 0~3 랜덤 숫자 생성

				if (e.getSource() == rock) {
					if (computer == r) {
						label1.setText("DRAW"); //label1 에서 설정한 텍스트 변경
						label.setIcon(image0);  //label에서 설정한 물음표 사진이 변경됨.
					} else if (r == (computer + 1) % 3) {
						label1.setText("WIN");
						label.setIcon(image2);
					} else {
						label1.setText("LOSE");
						label.setIcon(image1);
					}
					
				} 
				
				else if (e.getSource() == scissor) {
					if (computer == s) {
						label1.setText("DRAW");
						label.setIcon(image2);
					}
					else if (s == (computer + 1) % 3) {
						label1.setText("WIN");
						label.setIcon(image1);
					}
					else {
						label1.setText("LOSE");
						label.setIcon(image0);
					}
				} 
				
				else if (e.getSource() == paper) {
					if (computer == p) {
						label1.setText("DRAW");
						label.setIcon(image1);}
					else if (p == (computer + 1) % 3) {
						label1.setText("WIN");
						label.setIcon(image0);}
					else {
						label1.setText("LOSE");
						label.setIcon(image2);
					}
				}
			}
		};
		// 각 버튼이 눌렸을때 반응
		rock.addActionListener(buttonListener);
		scissor.addActionListener(buttonListener);
		paper.addActionListener(buttonListener);

		setVisible(true); // 프레임 화면에 등장
	}

	@Override
	public void actionPerformed(ActionEvent e) { // class MyFrame의 오류해결??
	}
}

public class main {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}
}
