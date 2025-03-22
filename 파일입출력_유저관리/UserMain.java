package 파일입출력_유저관리;

import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		User user = new User();

		int win=0;
		int lose=0;
		int same=0;
		
		System.out.print("Enter ID : ");
		String id = sc.next();
		UserLoad ul = new UserLoad();
		user = ul.loadInfo(id); // 반환된 객체를 user 변수에 저장 
		if(user != null) { // 이제 user에 올바른 값이 들어가므로 정상 출력됨.
			System.out.println("ID : "+user.getid()); 
			System.out.print(user.getWin()+"승 ");
			System.out.print(user.getLose()+"패 ");
			System.out.println(user.getSame()+"무 " );
		}

		// -----------------<1차 ID 불러오기 및 기본정보 불러오기 완료>------------------------
		
		while(true) {
			System.out.print("R S P  : ");
			String rsp = sc.next();
			Rsp r = new Rsp(rsp); // Rsp 클래스 불러오면서 자동적으로 가위바위보 게임가능
			int com = r.rsp();
			// 결과 값을 받아옴. 0(무승부) 1(이김) 2(짐)
			
			if(com==0) {
				System.out.println("비겼습니다.");
				same++;
				}
			if(com == 1 ) {
				System.out.println("이겼습니다.");
				win++;
				}
			if(com == 2 ) {
				System.out.println("졌습니다.");
				lose++;
				}
			
			System.out.println("다시 ㄱㄱ?(1) ㄴㄴ?(0)");
			int a = sc.nextInt();
			if(a==1)
				continue;
			if(a==0)
				break;
			
		}
		// ------------------------- 가위바위보 여러번하면서 승패 기록 후 리턴 -----------------
		user.setLose(lose+user.getLose());
		user.setWin(win+user.getWin());
		user.setSame(same+user.getSame());
		// -------------------------- 승패 기록 저장 ------------------------------
		// user 객체를 저장하기 위한 클래스 만들기 
		UserData ud = new UserData();
		ud.writeInfo(user);

		sc.close();
	}

}
