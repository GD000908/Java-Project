package to_DO;

import java.io.*;

public class FileWrite{
	String userId="";
	public void userId(String userId) {
		this.userId = userId;
	}
	public void write(String day, String message, String category) {
		String safeName = message.replaceAll("[\\\\/:*?\"<>|]", "_");
		String path = "C:\\java_GD\\todo\\" + userId + "\\" + day + "\\" + category;
		File dir = new File(path);
		if (!dir.exists()) dir.mkdirs();

		File file = new File(dir, safeName + ".txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("false::" + message);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

