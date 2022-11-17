import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
 
public class Frame{
	JFrame frame = new JFrame("MineSweeper");
	ImageIcon img = new ImageIcon("./reset/reset.png");
	JLabel scoreLabel = new JLabel();
	JLabel timeLabel = new JLabel();
	JPanel jp = new JPanel();	//for button
	JPanel jp2 = new JPanel();	//for others
	JPanel sizepane = new JPanel();
	JPanel minepane = new JPanel();
	JButton reset = new JButton(img);
	
	//reset.setBorderPainted(false);
	//JButton pause = new JButton("pause");
	
	//	스피너는 두 개의 화살표가 붙은 텍스트 필드이다. 텍스트 필드는 숫자를 표현하고 있고 사용자는 화살표를 클릭하여 값들을 증가 또는 감소시킬 수 있다.
	JSpinner x = new JSpinner(new SpinnerNumberModel(5,0,30,1));
	JSpinner y = new JSpinner(new SpinnerNumberModel(5,0,30,1));

	String message;
	
	int width;
	int height;
	int numMine;
	
	int size;
	int pressCount, againAnswer, score;
	double startTime,currentTime, second;
	
	JButton buttons[][];
	boolean isOpened[][];
	boolean stop;
	Mine mine;
	
	//지뢰 게임 시작전 가로세로 설정, 지뢰 개수 설정하는 창 만들기.
	public Frame() {
		sizepane.add(x);
		sizepane.add(y);
		
		//**  JOptionPane.showMessageDialog = 단순한 알림창을 띄울 수 있는 함수다.  
		JOptionPane.showMessageDialog(frame,sizepane,"가로*세로 칸수 설정",JOptionPane.QUESTION_MESSAGE);
		
		width = (int)x.getValue();
		height = (int)y.getValue();
		
		
		JSpinner M = new JSpinner(new SpinnerNumberModel(5,0,width*height,1)); //지뢰개수 설정.
		
		minepane.add(M);
		//**  JOptionPane.showMessageDialog = 단순한 알림창을 띄울 수 있는 함수다.  
		JOptionPane.showMessageDialog(frame,minepane,"지뢰 개수 설정",JOptionPane.QUESTION_MESSAGE);
		numMine = (int)M.getValue();
		
		size = width*height;
		buttons = new JButton[width][height];
		isOpened= new boolean[width][height];
		
		getTime();
	    setBoard();
	}
	
	
	void setBoard() {
		//startTime=0.0;
		//currentTime=0.0;
		pressCount=0;
		score=0;
		second=0.0;
		stop=false;
		
		// currentTimeMillis => 타이머 설정. 밀리세컨드 단위로 출력.
		startTime = System.currentTimeMillis();
		scoreLabel.setText("[Score: 0]");
		timeLabel.setText("[Time: 0]");
		
		mine = new Mine(width, height, numMine);

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				buttons[i][j] = new JButton(" ");
				
				//clicked location  
				int x =i;
				int y=j;
				
				//Right click 우클릭시 선택한 위치에 지뢰가 있을거라고 생각하고 표시.
				buttons[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e)) {
							checkflag(x,y);
							//여기에 우클릭시 지뢰개수 마이너스카운트하는 GUI넣어야할것같은데...
						}
					}
				});

				//Left click  좌클릭시 블록 오픈.
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						openBlock(x,y);
					}
				});
				
				jp.add(buttons[i][j]);
				
			}
		}
		
		/*
		//For PAUSE button
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop = !stop;

				//if(pause.getText()=="pause") {
				if(pause.getText().equals("pause")) {
					pause.setText("continue");
					for(int i=0; i<width;i++)
						for(int j=0; j<height; j++)
							buttons[i][j].setEnabled(false);
				}else {
					pause.setText("pause");
					for(int i=0; i<width;i++)
						for(int j=0; j<height; j++)
							buttons[i][j].setEnabled(true);
				}
			}
		});
*/
		//For RESET button
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(width, height);
				//pause.setText("pause");
			}
		});
		jp2.add(scoreLabel);
		jp2.add(reset);
		//setBorderPainted => 버튼 테두리 삭제
		reset.setBorderPainted(false);
		//setContentAreaFilled => 버튼 배경 투명하게 하기.
		reset.setContentAreaFilled(false);
		
		//jp2.add(pause);
		
		jp2.add(timeLabel);
		jp.setLayout(new GridLayout(width, 1));
		frame.add(jp,"Center");
		frame.add(jp2,BorderLayout.NORTH);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//오른쪽 마우스 클릭 시 깃발표시(X)
	public void checkflag(int x, int y) {
		if(buttons[x][y].getText().equals(" ")) {
			buttons[x][y].setText("X");
			buttons[x][y].setFont(new Font("맑은 고딕 ",Font.BOLD,15));
		}
		else if(buttons[x][y].getText().equals("X"))
			buttons[x][y].setText(" ");
	}
	
	//Assigning number to button 버튼에 숫자 할당??
	public void setNumber(int x, int y) {
		int board[][] = new int[width][height];
		board = mine.getBoard();
		String value = Integer.toString(board[x][y]);
		
		if(value.equals("0"))
			buttons[x][y].setForeground(Color.GRAY);
		else if(value.equals("1"))
			buttons[x][y].setForeground(Color.BLUE);
		else if(value.equals("2"))
			buttons[x][y].setForeground(Color.GREEN);
		else if(value.equals("9")) // number 9 means mine.
			buttons[x][y].setBackground(Color.BLACK);
		else
			buttons[x][y].setForeground(Color.RED);
		
		buttons[x][y].setText(value);
	}

	void pressButton(int x, int y) {
		
		buttons[x][y].setOpaque(true);
		buttons[x][y].setBorderPainted(false);
		
		buttons[x][y].setBackground(Color.GRAY);
		buttons[x][y].setFont(new Font("맑은 고딕 ",Font.BOLD,15));
		
		isOpened[x][y] = true;
		setNumber(x,y);
		getScore();

		pressCount++; //For checking win
		
		//WIN
		if(checkWin(x,y) == 1) {
			stop=true;
			message = String.format("***WIN!!***\n Your socre is "+score+"\n Game Time: "+second+" seconds");
			JOptionPane.showMessageDialog(null,message,"End Of Game", JOptionPane.PLAIN_MESSAGE);
			againAnswer =JOptionPane.showConfirmDialog(null, "NEW GAME?", "Again", JOptionPane.YES_NO_OPTION);
			if(againAnswer ==JOptionPane.YES_OPTION) {			
				restart();
			}else
				System.exit(0);
		}
		
		//LOSE
		else if(checkWin(x,y) == -1) {
			stop=true;
			message = String.format("GAME OVER ㅜ_ㅜ ");
			JOptionPane.showMessageDialog(null,message,"End Of Game", JOptionPane.PLAIN_MESSAGE);
			againAnswer=JOptionPane.showConfirmDialog(null, "NEW GAME?", "Again", JOptionPane.YES_NO_OPTION);
			if(againAnswer ==JOptionPane.YES_OPTION) {
				restart();
			}else
				frame.dispose();
		}
	}
	
//Recursively open blocks when there is no mine nearby   근처에 지뢰가 없을 때 블록 열기
	void openBlock(int x, int y) {
		if(x<0||x>=width || y<0||y>=height)
			return;
		int board[][] = new int[width][height];
		board = mine.getBoard();
		int value = board[x][y];
		
		if(isOpened[x][y]==true)
			return;
		
		pressButton(x,y);
		
		if(value != 0)
			return;
		
		if(value == 0) {
			openBlock(x-1, y-1);
			openBlock(x-1, y);
			openBlock(x-1, y+1);
			openBlock(x, y-1);
			openBlock(x, y+1);
			openBlock(x+1, y-1);
			openBlock(x+1, y);
			openBlock(x+1, y+1);
		}
		return;
	}
	
	int checkWin(int x, int y) {
		int board[][] = new int[width][height];
		board = mine.getBoard();
		int value = board[x][y];
		
		if((pressCount == size-numMine) && value!=9)	//WIN
			return 1;
		if(value==9)		//LOSE
			return -1;
		if(pressCount < size-numMine)		//Game in progress
			return 0;
		
		return 0;
	}
	
	void getScore() {
		score+=10;
		scoreLabel.setText("[Score: " +score+"]");
	}
	void getTime() {
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			
			public void run() {
				currentTime = System.currentTimeMillis();
				second = (currentTime-startTime)/1000.0;
				if(stop == false)
					timeLabel.setText("[Time: "+second+"]");
			}
		};
		m_timer.schedule(m_task,0,100);
	}
	
	//restart game in new board
	void restart() {
		Frame newFrame = new Frame();
		frame.dispose();
	}
	
	//restart game in same board
	void reset(int width, int height) {

		buttons = new JButton[width][height];
		isOpened = new boolean[width][height];

		resetBoard();
	}
	
	//reset only button(except 'pause','reset' buttons)
	void resetBoard() {
		pressCount=0;
		score=0;
		second=0.0;
		stop=false;
		
		jp.removeAll();
		
		startTime = System.currentTimeMillis();
		scoreLabel.setText("[Score: 0]");
		timeLabel.setText("[Time: 0]");

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				buttons[i][j] = new JButton();
				
				//clicked location
				int x =i;
				int y=j;
				
				//Right click
				buttons[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e)) {
							checkflag(x,y);
						}
					}
				});
				//Left click
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						openBlock(x,y);
					}
				});
				
				jp.add(buttons[i][j]);
			}
		}
		
		jp.setLayout(new GridLayout(width, 1));
		frame.add(jp,"Center");
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jp.revalidate();
		jp.repaint();
	}
}
