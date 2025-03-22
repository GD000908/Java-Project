package 파일입출력_유저관리;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class UserLoad {
	
	User user = new User();
	UserData ud = new UserData();
    public User loadInfo(String id) {
    	
        String path = Mypath.PATH + id + "\\save.sav";
        File f = new File(path);
        
        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                
                user =  (User) ois.readObject();  
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            user.setid(id);  // 이게 저장이되도록 해야함. 0
            
            user.setWin(0);
            user.setLose(0);
            user.setSame(0);  // -> 처음 만드는 아이디 일시 최초 승패 기록을 000으로 초기화.
            
            ud.writeInfo(user); // -> writeInfo메서드를 통해 원래 없는 번호 입력시 자동 저장.
            System.out.println("가입을 환영합니다. ");
        }
        return user;
    }
}
