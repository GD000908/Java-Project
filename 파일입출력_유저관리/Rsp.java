package 파일입출력_유저관리;

import java.util.Random;

public class Rsp {
	String my;
	int res;

	public Rsp(String my) {
		this.my = my;  
	}
	
	public int rsp() {
		Random rnd = new Random();
		String[] options = {"R", "S", "P"};  // 바위 가위 보
		int randomIndex = rnd.nextInt(options.length);
		System.out.println("랜덤 선택: " + options[randomIndex]);
		String com = options[randomIndex];
		
		// 게임 결과 처리
        if (my.equals(com)) {
            res =0;
        } else if (my.equals("R") && com.equals("S")) {
            res = 1;
        } else if (my.equals("R") && com.equals("P")) {
            res = 2;
        } else if (my.equals("P") && com.equals("R")) {
            res =1;
        } else if (my.equals("P") && com.equals("S")) {
            res=2;
        } else if (my.equals("S") && com.equals("P")) {
            res=1;
        } else if (my.equals("S") && com.equals("R")) {
            res=2;
        }
		
	
		return res;
		// 무승부는 0 이김은 1 짐은 2
		
		
	}
}