package to_DO;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class LoginPage {
	private static final String BASE_FOLDER_PATH = "C:\\java_GD\\Todo";
	public LoginPage() {
		
		//스윙(Swing) UI 컴포넌트의 외관(Look and Feel)을 
		//현재 운영체제(Windows, macOS 등)의 기본 스타일로 바꾸는 역할
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 로그인 페이지의 메인 프레임
		JFrame loginFrame = new JFrame("ToDo-List");
		loginFrame.setBounds(400, 300, 600, 290); 
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLayout(null);

		// 왼쪽 패널 (입력창)
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 400, 300); 
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(null);
		
		
		JLabel label = new JLabel("ID :");
		label.setBounds(90, 110, 100, 30); // 위치 약간 이동
		label.setFont(new Font("", Font.BOLD, 15));
		label.setForeground(Color.BLACK);
		leftPanel.add(label);

		JTextField idField = new JTextField();
		idField.setBounds(130, 110, 160, 30);
		idField.setFont(new Font("", Font.PLAIN, 14));
		leftPanel.add(idField);

		JButton loginButton = new JButton("로그인");
		loginButton.setBounds(150, 160, 100, 35); // 위치 및 크기 조정
		loginButton.setFont(new Font("", Font.BOLD, 16));
		loginButton.setBackground(new Color(70, 130, 180));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		leftPanel.add(loginButton);

		// 오른쪽 패널 (옅은 검정 배경 + 텍스트)
		JPanel rightPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(43, 51, 54));
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		
		rightPanel.setBounds(400, 0, 200, 300); 
		rightPanel.setOpaque(false);
		rightPanel.setLayout(null);

		JLabel titleLabel = new JLabel("LOGIN", JLabel.CENTER);
		titleLabel.setBounds(-10, 120, 200, 30);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		titleLabel.setForeground(Color.WHITE);
		rightPanel.add(titleLabel);

		// 상단 타이틀
		JLabel title = new JLabel("ToDo List");
		JLabel version = new JLabel("April Ver.");

		title.setFont(new Font("", Font.BOLD, 33));
		version.setFont(new Font("", Font.PLAIN, 13));

		title.setBounds(110, 50, 155, 50);   // 위치 중앙 정렬 느낌
		version.setBounds(267, 68, 100, 30);

		leftPanel.add(version);
		leftPanel.add(title);

		// 로그인 버튼 클릭 이벤트
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idField.getText().trim();
				performLogin(loginFrame, userId);
			}
		});

		// 텍스트 필드 엔터 입력 이벤트
		idField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idField.getText().trim();
				performLogin(loginFrame, userId);
			}
		});

		// 프레임에 패널 추가
		loginFrame.add(leftPanel);
		loginFrame.add(rightPanel);

		loginFrame.setVisible(true);
	}

	private static void performLogin(JFrame loginFrame, String userId) {
		if (userId.isEmpty()) {
			JOptionPane.showMessageDialog(loginFrame, "아이디를 입력하세요.");
			return;
		}

		File userFolder = new File(BASE_FOLDER_PATH + File.separator + userId);
		if (!userFolder.exists()) {
			userFolder.mkdirs();
		}
		
		loginFrame.dispose();
		openMainScreen(userId);
	}
	
	// main부분 로그인 프레임시작. 
	public static void main(String[] args) {
		new LoginPage();
	}
	
	
	//로그인 후 
	private static void openMainScreen(String userId) {
		FileWrite fl = new FileWrite();
		fl.userId(userId);
		new CalenderMain(userId);
	}
}
