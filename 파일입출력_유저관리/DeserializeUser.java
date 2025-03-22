package 파일입출력_유저관리;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeUser {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\java_GD\\User\\7777\\save.sav"))) {
            Object obj = ois.readObject();

            if (obj instanceof User) {
                User user = (User) obj;
                System.out.println("ID: " + user.getid());
                System.out.println("승리: " + user.getWin());
                System.out.println("패배: " + user.getLose());
                System.out.println("무승부: " + user.getSame());
            } else {
                System.out.println("User 객체가 아님!");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

