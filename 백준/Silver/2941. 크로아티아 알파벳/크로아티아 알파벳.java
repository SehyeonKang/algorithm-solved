import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		
		if(s.contains("c="))
			s = s.replace("c=", "c");
		if(s.contains("c-"))
			s = s.replace("c-", "c");
		if(s.contains("dz="))
			s = s.replace("dz=", "d");
		if(s.contains("d-"))
			s = s.replace("d-", "d");
		if(s.contains("lj"))
			s = s.replace("lj", "l");
		if(s.contains("nj"))
			s = s.replaceAll("nj", "n");
		if(s.contains("s="))
			s = s.replace("s=", "s");
		if(s.contains("z="))
			s = s.replace("z=", "z");
		
		bw.write(s.length() + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}