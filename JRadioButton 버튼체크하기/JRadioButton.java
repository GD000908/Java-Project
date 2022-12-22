import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class MyFrame extends JFrame implements ActionListener {

	JLabel l2; // 바깥으로 빼버림
	JRadioButton b1;
	JRadioButton b2;
	JRadioButton b3;

	MyFrame() { // Constructor 생성

		setSize(300, 150);
		setLocation(600, 400);
		setTitle("JRadioButton");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		JPanel main = new JPanel(); // 전체 패널
		JPanel up = new JPanel(); // 위
		JPanel mid = new JPanel();// 중간
		JPanel down = new JPanel();// 아래
		JLabel l1 = new JLabel("커피 사이즈를 선택하세요.");

		b1 = new JRadioButton("Small");
		b2 = new JRadioButton("Midium");
		b3 = new JRadioButton("Large");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		ButtonGroup bg = new ButtonGroup(); // 버튼 두개 이상을 동시에 선택 못하게함

		bg.add(b1);

		bg.add(b2);

		bg.add(b3);

		l2 = new JLabel(""); // JLabel 바깥으로 뺴기

		up.add(l1);

		mid.add(b1);

		mid.add(b2);

		mid.add(b3);

		down.add(l2);

		main.add(up);

		main.add(mid);

		main.add(down);

		this.add(main);

		setVisible(true);

	}

	@Override

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1)
			l2.setText("Small 사이즈가 선택됨");

		else if (e.getSource() == b2)
			l2.setText("Midium 사이즈가 선택됨");

		else if (e.getSource() == b3)
			l2.setText("Large 사이즈가 선택됨");// l2.setText(""); 하면 l2가 바깥에 있어서 에러남

	}
}

public class Main11 {

	public static void main(String[] args) {

		new MyFrame();

	}

}
