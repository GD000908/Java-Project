package 파일입출력_유저관리;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UserData {
    public void writeInfo(User user) {
        String fullpath = Mypath.PATH + user.getid().trim() + "\\save.sav";
        File dir1 = new File(Mypath.PATH);
        
        if (!dir1.exists()) {
            dir1.mkdir();
        }
        File dir2 = new File(dir1, user.getid().trim());
        if (!dir2.exists()) { // 수정: dir2가 존재하지 않을 때만 생성
            dir2.mkdirs();
        }
        
        try (FileOutputStream fos = new FileOutputStream(fullpath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (Exception e) {
            System.out.println("저장 실패 ");
            e.printStackTrace();
        }
    }
}
