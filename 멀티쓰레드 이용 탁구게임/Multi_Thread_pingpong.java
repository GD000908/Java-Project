import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyFrame extends JFrame
{
	JPanel p;
	
	JPanel p_stick;

	MyThread[] th = new MyThread[10];
	int stick_y = 90;
	
	ImageIcon image1 = new ImageIcon("C://ball2.png");
	



	MyFrame() throws InterruptedException
	{ // Constructor 생성
		setSize(800, 500);
		setLocation(600, 200);
		setTitle("Ball Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		p.setLayout(null);
		
		//막대 생성.
		p_stick = new JPanel(); 
		p_stick.setBackground(Color.BLUE);
		p_stick.setBounds(0, stick_y, 25, 120);
		
		p.add(p_stick);
		
		//팅기는 네모 생성 개수 설정.
		this.add(p);
		th[0] = new MyThread(3);
		th[0].start();
		th[1] = new MyThread(4);
		th[1].start();
		th[2] = new MyThread(5);
		th[2].start();
		th[3] = new MyThread(4);
		th[3].start();
		th[4] = new MyThread(3);
		th[4].start();
		
		this.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				switch (code)
				{
				case KeyEvent.VK_UP:
					stick_y -= 8;
					break;
				case KeyEvent.VK_DOWN:
					stick_y += 8;
					break;
				}
				p_stick.setBounds(0, stick_y, 25, 120);
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
			}
		});
		setVisible(true);
		th[0].join();
		th[1].join();
		th[2].join();
		th[3].join();
		th[4].join();
	}

	class MyThread extends Thread
	{
		JLabel l;
		int interval;
		int x = 0;
		int y = 0;
		int x_increment = 1;
		int y_increment = 1;
		
		
		MyThread(int interval)
		{
			this.interval = interval;
			l = new JLabel(image1);
			l.setFont(new Font("courier", Font.BOLD, 60));
			x = (int) (Math.random() * 340);
			y = (int) (Math.random() * 220);
			//l.setBounds(x, y, 70, 70);
			//l.setBackground(Color.RED);
			l.setOpaque(true); //JLabele의 경우 배경색을 설정하면 효과가 나타나지 않음. true를 해줘야 효과적용
			p.add(l);
		}

		@Override
		public void run()
		{
			while (true) // 멀티 Threading 기본 형태
			{
				try
				{
					// System.out.print("hello");
					x += x_increment;
					
					y += y_increment;
					
					if (y > p.getHeight() - 50 || y < 0)//화면 위아래 충돌일때 
						y_increment *= -1;
					
					if (x > p.getWidth() - 50)//화면 오른쪽만 충돌 일때
						x_increment *= -1;

					if(circleRect(x + 25, y + 25, 25, 
							p_stick.getLocation().x, p_stick.getLocation().y, 
							p_stick.getSize().width, p_stick.getSize().height))
						x_increment *= -1;//공과 막대가 충돌하면
					
					if(x < -50)//왼쪽으로 벗어나면
						break;

					l.setBounds(x, y, 50, 50);

					Thread.sleep(interval); // 실행되는 속도 설정
				} catch (InterruptedException e)
				{
				}
			}
		}
	}
	
	
	//막대와 공 충돌 검사
	boolean circleRect(float cx, float cy, float radius, float rx, float ry, float rw, float rh) {

		  // temporary variables to set edges for testing
		  float testX = cx;
		  float testY = cy;

		  // which edge is closest?
		  if (cx < rx)         testX = rx;      // test left edge
		  else if (cx > rx+rw) testX = rx+rw;   // right edge
		  if (cy < ry)         testY = ry;      // top edge
		  else if (cy > ry+rh) testY = ry+rh;   // bottom edge

		  // get distance from closest edges
		  float distX = cx-testX;
		  float distY = cy-testY;
		  double distance = Math.sqrt( (distX*distX) + (distY*distY) );

		  // if the distance is less than the radius, collision!
		  if (distance <= radius) {
		    return true;
		  }
		  return false;
		}

}
class MyFrame1 extends JFrame
{
	MyFrame1()
	{
		setSize(800, 500);
		setLocation(600, 200);
		setTitle("Ball Gameover");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1 = new JPanel();
		ImageIcon image2 = new ImageIcon("C://gameover.png");
		JLabel L1 = new JLabel(image2);
		
		
		
		p1.add(L1);
		
		this.add(p1);
		setVisible(true);
	}
}
public class Main11
{

	public static void main(String[] args) throws InterruptedException
	{
		new MyFrame(); // join 됨.여기서 main이 멈춤.
		// join된 쓰레드가 모두stop되면 다시 실행
		//System.exit(-1);
		//System.out.println("Game End");
		new MyFrame1();
	}
}
