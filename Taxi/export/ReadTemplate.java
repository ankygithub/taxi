package export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTemplate {
	public static String readTemplate(int t) throws IOException{
		String fileName= "export\\template"+t+".txt";
		System.out.println(new File(fileName).getAbsoluteFile());
		FileReader fr = new FileReader(new File(fileName));

		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		while (br.ready()) {
			sb.append(br.readLine()+"\n");
		}
		return sb.toString();
	}
	
	
}
