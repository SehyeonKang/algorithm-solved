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
		int cnt = 0;
		
		for(int i=0; i<s.length(); i++){
			if(i != 0 && i != s.length()-1 && s.charAt(i) == ' ') {
				cnt++;
			} else if(s.length() != 1 && i == s.length() - 1 && s.charAt(i) == ' ' && s.charAt(i-1) != ' ' ) {
				cnt++;
			} else if(i == s.length() - 1 && s.charAt(i) != ' ') {
				cnt++;
			}
		}
		
		bw.write(cnt + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}