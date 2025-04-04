package to_DO;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CalenderMain {
	String categoryFolder="";
	String message = "";
	int yPosition1 = 580;
	int yPosition2 = 800;
	boolean isEdit = false;
	int listCount = 0;
	int listCount2 = 0;
	final int MAX_LIST_COUNT = 4;  // 리스트 별 최대 개수
	FileWrite fw = new FileWrite();
	int currentDay = 1;
	String userId ="";
	Label category1;
	Button category1_btn1;
	Label category2;
	Button category2_btn2;
	String currentCategory = "default";

	ArrayList<Component> category1Items = new ArrayList<>();
	ArrayList<Component> category2Items = new ArrayList<>();


	//=======================================================================
	//                     Calneder 디자인
	//=======================================================================
	public CalenderMain(String userId) {
		this.userId = userId;
		fw.userId(userId); 
		Font catekoryFont = new Font("", Font.BOLD, 20);
		Font font = new Font("", Font.BOLD, 20);
		Font dayNames_font = new Font("", Font.BOLD, 20);
		Font days_font = new Font("", Font.ITALIC, 18);
		Font april_font = new Font("", Font.BOLD, 30);

		Frame frame = new Frame();
		frame.setLayout(null);
		frame.setBounds(210, 10, 950, 980);

		Label to_do_list_label = new Label();
		to_do_list_label.setBounds(0, 510, 960, 5);
		to_do_list_label.setBackground(Color.black);
		to_do_list_label.setFont(font);

		Label april = new Label("April");
		april.setBounds(40, 15, 100, 110);
		april.setFont(april_font);

		Label undermonth = new Label();
		undermonth.setBounds(0, 98, 950, 5);
		undermonth.setBackground(Color.black);

		Label todoName = new Label("Todo List", Label.CENTER);
		todoName.setBounds(400, 500, 100, 20);
		todoName.setFont(days_font);

		//로그아웃 누르면 다시로그인창으로 뜨게 함.
		Button logoutBtn = new Button("Log Out");
		logoutBtn.setBounds(860, 40, 70, 40);
		logoutBtn.setBackground(new Color(255,250,250,120));
		frame.add(logoutBtn);

		// 로그아웃 버튼에 대한 동작감지
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginPage();

			}
		});

		String[] dayNames = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
		for (int i = 0; i < 7; i++) {
			Label day = new Label(dayNames[i], Label.CENTER);
			int x = i * 107 + 120;
			day.setBounds(x, 90, 70, 20);
			day.setForeground(Color.black);
			day.setFont(dayNames_font);
			frame.add(day);
		}



		//=======================================================================
		//                     Calneder 디자인
		//=======================================================================



		//=======================================================================
		//                        카테고리
		//=======================================================================
		TextField tf = new TextField();
		tf.setBounds(220, 540, 400, 35);
		tf.setFont(font);
		tf.setVisible(false);
		frame.add(tf);

		TextField tf2 = new TextField();
		tf2.setBounds(220, 760, 400, 35);
		tf2.setFont(font);
		tf2.setVisible(false);
		frame.add(tf2);



		category1_btn1 = new Button("+");
		category1_btn1.setBounds(150, 540, 50, 30);
		category1_btn1.setFont(catekoryFont);
		category1_btn1.setVisible(false);
		frame.add(category1_btn1);

		category2_btn2 = new Button("+");
		category2_btn2.setBounds(150, 760, 50, 30);
		category2_btn2.setFont(catekoryFont);
		category2_btn2.setVisible(false);
		frame.add(category2_btn2);

		category1_btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tf.isVisible()) {
					tf.setVisible(true);
					tf.requestFocus();
					return;
				}

				// 최대 4개까지 가능 -> 카테고리당 4개여야하는데 지금 총 4개까지만 됨. 
				// 수정해야함. 수정완료
				CalenderMain.this.message = tf.getText().trim();

				if (message.equals("")) {
					JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다.");
					return;
				}
				if (listCount >= MAX_LIST_COUNT) {
					JOptionPane.showMessageDialog(null, "리스트는 최대 4개까지 가능합니다.");
					tf.setText("");
					tf.setVisible(false);
					return;
				}
				addListItem(frame, font, message, yPosition1, true);

				fw.write(currentDay + "", message, "category1");
				yPosition1 += 40;
				
				// 여기에 버튼 색 칠하는 코드 넣으면 리스트 추가하자마자 바로 색 바뀜. 
				// ✅ 리스트 추가 직후 해당 날짜 버튼 색칠
				for (Component c : frame.getComponents()) {
					if (c instanceof Button btn && btn.getLabel().equals(String.valueOf(currentDay))) {
						btn.setBackground(new Color(225, 255, 255, 150));
						break;
					}
				}

				tf.setText("");
				tf.setVisible(false);
			}
		});

		category2_btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tf2.isVisible()) {
					tf2.setVisible(true);
					tf2.requestFocus();
					return;
				}
				message = tf2.getText().trim();
				if (message.equals("")) {
					JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다.");
					return;
				}
				if (listCount2 >= MAX_LIST_COUNT) {
					JOptionPane.showMessageDialog(null, "리스트는 최대 4개까지 가능합니다.");
					tf2.setText("");
					tf2.setVisible(false);
					return;
				}
				addListItem(frame, font, message, yPosition2, false);
				fw.write(currentDay + "", message, "category2");
				yPosition2 += 40;
				
				// 여기에 버튼 색 칠하는 코드 넣으면 리스트 추가하자마자 바로 색 바뀜. 
				
				// ✅ 리스트 추가 직후 해당 날짜 버튼 색칠
				for (Component c : frame.getComponents()) {
					if (c instanceof Button btn && btn.getLabel().equals(String.valueOf(currentDay))) {
						btn.setBackground(new Color(225, 255, 255, 150));
						break;
					}
				}

				
				tf2.setText("");
				tf2.setVisible(false);
			}
		});

		// 카테고리1 입력 필드
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					category1_btn1.dispatchEvent(new ActionEvent(category1_btn1, ActionEvent.ACTION_PERFORMED, ""));
				}
			}
		});

		// 카테고리2 입력 필드
		tf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					category2_btn2.dispatchEvent(new ActionEvent(category2_btn2, ActionEvent.ACTION_PERFORMED, ""));
				}
			}
		});


		category1 = new Label();
		category1.setBounds(30, 530, 120, 50);
		category1.setFont(catekoryFont);
		category1.setVisible(false);
		frame.add(category1);

		category2 = new Label();
		category2.setBounds(30, 750, 120, 50);
		category2.setFont(catekoryFont);
		category2.setVisible(false);
		frame.add(category2);

		Label date = new Label();
		//=======================================================================
		//                        카테고리
		//=======================================================================


		//=======================================================================
		//                        날짜 버튼
		//=======================================================================
		Button[] days = new Button[32];
		for (int i = 2; i < days.length; i++) {
			days[i] = new Button(String.valueOf(i-1));
			int row = (i - 1) / 7;
			int col = (i - 1) % 7;
			days[i].setBounds(col * 107 + 125, row * 80 + 118, 65, 65);
			days[i].setFont(days_font);
			days[i].setBackground(Color.WHITE);
			frame.add(days[i]);

			days[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Button clickedButton = (Button) e.getSource();
					int day = Integer.parseInt(clickedButton.getLabel());
					currentDay = day;  


					for (Component c : category1Items) frame.remove(c);
					for (Component c : category2Items) frame.remove(c);
					category1Items.clear();
					category2Items.clear();
					listCount = 0;
					listCount2 = 0;
					yPosition1 = 580;
					yPosition2 = 800;

					category1.setText("Home");
					category1.setVisible(true);
					category1_btn1.setVisible(true);
					category2.setText("Company");
					category2.setVisible(true);
					category2_btn2.setVisible(true);

					loadSavedItems(frame, font, day, true);
					loadSavedItems(frame, font, day, false);
					frame.repaint();


					frame.add(date);
					date.setText("2025-04-"+day);
					date.setFont(font);
					date.setBounds(750,520,120,50);

				}



			}); // 날짜 버튼 액션 리스너

		}  //날짜 버튼 for 생성 부분
		
		
		// 날짜 버튼 색칠하기. 
		for (int i = 2; i < days.length; i++) {
			Button btn = days[i];
			if (btn == null) continue;

			int btnDay = Integer.parseInt(btn.getLabel());

			boolean hasTodo = false;
			for (String category : new String[]{"category1", "category2"}) {
				String folderPath = "C:\\java_GD\\todo\\" + userId + "\\" + btnDay + "\\" + category;
				File folder = new File(folderPath);
				File[] txtFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
				if (txtFiles != null && txtFiles.length > 0) {
					hasTodo = true;
					break;
				}
			}

			if (hasTodo) {
				btn.setBackground(new Color(225, 255, 255, 150));
			}
		}





		//=======================================================================
		//                        날짜 버튼
		//=======================================================================
		frame.add(todoName);
		frame.add(undermonth);
		frame.add(april);
		frame.add(to_do_list_label);



		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	private void loadSavedItems(Frame frame, Font font, int day, boolean isCategory1) {
		categoryFolder = isCategory1 ? "category1" : "category2";
		String folderPath = "C:\\java_GD\\todo\\"+userId +"\\"+ day + "\\" + categoryFolder;
		File dir = new File(folderPath);
		if (!dir.exists()) return;

		File[] files = dir.listFiles();
		if (files == null) return;

		for (File file : files) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split("::");
					String content = parts.length > 1 ? parts[1] : line;
					if (isCategory1) {
						addListItem(frame, font, content, yPosition1, true);
						yPosition1 += 40;
					} else {
						addListItem(frame, font, content, yPosition2, false);
						yPosition2 += 40;
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void addListItem(Frame frame, Font font, String message, int yPosition, boolean isCategory1) {
	    Checkbox ck = new Checkbox("     " + message);
	    ck.setFont(font);
	    ck.setBounds(30, yPosition, 600, 30);

	    String categoryFolder = isCategory1 ? "category1" : "category2";
	    String folderPath = "C:\\java_GD\\todo\\" + userId + "\\" + currentDay + "\\" + categoryFolder;

	    final File[] fileRef = new File[1];
	    fileRef[0] = new File(folderPath + "\\" + message + ".txt");

	    // 체크박스 상태 불러오기
	    if (fileRef[0].exists()) {
	        try (BufferedReader br = new BufferedReader(new FileReader(fileRef[0]))) {
	            String line = br.readLine();
	            if (line != null) {
	                String[] parts = line.split("::");
	                if ("true".equals(parts[0])) {
	                    ck.setState(true);
	                    ck.setForeground(Color.DARK_GRAY);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // 체크 이벤트
	    ck.addItemListener(new ItemListener() {
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	            boolean isChecked = ck.getState();
	            ck.setForeground(isChecked ? Color.DARK_GRAY : Color.BLACK);
	            try {
	                if (!fileRef[0].getParentFile().exists()) fileRef[0].getParentFile().mkdirs();
	                BufferedWriter bw = new BufferedWriter(new FileWriter(fileRef[0], false));
	                bw.write((isChecked ? "true" : "false") + "::" + ck.getLabel().trim());
	                bw.newLine();
	                bw.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    });

	    Button editBtn = new Button("Edit");
	    editBtn.setBounds(640, yPosition, 60, 30);

	    Button cnclBtn = new Button("Cancel");
	    cnclBtn.setBounds(710, yPosition, 60, 30);
	    cnclBtn.setVisible(false);

	    Button deleteBtn = new Button("Delete");
	    deleteBtn.setBounds(710, yPosition, 60, 30);
	    deleteBtn.setVisible(true);

	    TextField editField = new TextField();
	    editField.setBounds(30, yPosition, 600, 30);
	    editField.setFont(font);
	    editField.setVisible(false);

	    if (isCategory1) {
	        category1Items.add(ck);
	        category1Items.add(editBtn);
	        category1Items.add(cnclBtn);
	        category1Items.add(deleteBtn);
	        category1Items.add(editField);
	        listCount++;
	    } else {
	        category2Items.add(ck);
	        category2Items.add(editBtn);
	        category2Items.add(cnclBtn);
	        category2Items.add(deleteBtn);
	        category2Items.add(editField);
	        listCount2++;
	    }

	    editBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String oldMessage = ck.getLabel().trim();
	            if (!isEdit) {
	                isEdit = true;
	                ck.setVisible(false);
	                editField.setVisible(true);
	                editField.setText(oldMessage);
	                cnclBtn.setVisible(true);
	                deleteBtn.setVisible(false);
	                editBtn.setLabel("save");
	            } else {
	                String newText = editField.getText().trim();
	                if (!newText.equals("")) {
	                    // UI 갱신
	                    ck.setLabel("     " + newText);
	                    editField.setVisible(false);
	                    ck.setVisible(true);
	                    cnclBtn.setVisible(false);
	                    deleteBtn.setVisible(true);
	                    editBtn.setLabel("Edit");
	                    isEdit = false;

	                    try {
	                        // 기존 파일 삭제
	                        if (fileRef[0].exists()) fileRef[0].delete();

	                        // 새 파일 저장
	                        File newFile = new File(folderPath + "\\" + newText + ".txt");
	                        fileRef[0] = newFile; // 파일 참조 갱신
	                        if (!newFile.getParentFile().exists()) newFile.getParentFile().mkdirs();
	                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(newFile, false));
	                        bw2.write((ck.getState() ? "true" : "false") + "::" + newText);
	                        bw2.newLine();
	                        bw2.close();
	                    } catch (IOException e2) {
	                        e2.printStackTrace();
	                    }
	                }
	            }
	        }
	    });

	    editField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                editBtn.dispatchEvent(new ActionEvent(editBtn, ActionEvent.ACTION_PERFORMED, ""));
	            }
	        }
	    });

	    cnclBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            editField.setVisible(false);
	            ck.setVisible(true);
	            cnclBtn.setVisible(false);
	            deleteBtn.setVisible(true);
	            editBtn.setLabel("Edit");
	            isEdit = false;
	        }
	    });

	    deleteBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Path filepath = fileRef[0].toPath();

	            try {
	                Files.delete(filepath);

	                if (isCategory1) listCount--;
	                else listCount2--;

	                // UI 리로드
	                for (Component c : frame.getComponents()) {
	                    if (c instanceof Button btn && btn.getLabel().equals(String.valueOf(currentDay))) {
	                        btn.dispatchEvent(new ActionEvent(btn, ActionEvent.ACTION_PERFORMED, ""));
	                        break;
	                    }
	                }

	                // 색칠 상태 갱신
	                boolean stillHasTodo = false;
	                for (String category : new String[]{"category1", "category2"}) {
	                    String folderPath = "C:\\java_GD\\todo\\" + userId + "\\" + currentDay + "\\" + category;
	                    File folder = new File(folderPath);
	                    File[] txtFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
	                    if (txtFiles != null && txtFiles.length > 0) {
	                        stillHasTodo = true;
	                        break;
	                    }
	                }

	                for (Component c : frame.getComponents()) {
	                    if (c instanceof Button btn && btn.getLabel().equals(String.valueOf(currentDay))) {
	                        btn.setBackground(stillHasTodo ? new Color(225, 255, 255, 150) : Color.WHITE);
	                        break;
	                    }
	                }

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	    });

	    frame.add(ck);
	    frame.add(editBtn);
	    frame.add(cnclBtn);
	    frame.add(editField);
	    frame.add(deleteBtn);
	    frame.validate();
	    frame.repaint();
	}

}   

