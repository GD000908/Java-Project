package 파일입출력_유저관리;

import java.io.Serializable;

public class User implements Serializable {
	//private static final long serialVersionUID = 9015856990813750110L;

  
    
    private int win; // 승리
    private int lose; //패배
    private int same;  // 무승부 
    private String id;  // 이걸 ID로 바꿀예정. 
    
    public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getSame() {
		return same;
	}
	public void setSame(int same) {
		this.same = same;
	}
    
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
}
