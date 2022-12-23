//a+b를 할때 Scanner 와 println을 사용하지 않고 BufferedReader를 이용하여 입력 받고 BufferedWriter를 사용하여 출력을 함.
//Scanner,print에 비해서 시간단축을 할 수 있다. 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException { //예외처리를 위해 throws IOException
		
		//기본적인 BufferedReader 선언 Scanner 와 유사
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		//기본적인 BufferedWriter 선언 System.out.println()과 유사
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//String s = bf.readLine(); //BufferedReader는 기본적으로 String 형태로 읽는다. 
		int T = Integer.parseInt(bf.readLine()); //따라서 Int형으로 받을때는 형변환을 해줘야한다.
												 // //BufferedReader 입력받는 방법.
		
		StringTokenizer st;
		for(int i=1; i<=T; i++)
		{			
			st = new StringTokenizer(bf.readLine()," ");
			// nextToken()함수를 쓰면 readLine()을 통해 입력받은 값을 공백단위로 구분하여 순서대로 호출할 수 있다. 
			bw.write((Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()))+ "\n");
			//System.out.println(a+b);  --> 짧은시간내에 출력해야하는경우 print문도 느리다.
	
		}
		bf.close();
        
		bw.flush();
		bw.close();

	}
}
