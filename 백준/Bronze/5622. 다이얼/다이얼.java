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
		int time = 0;
		
		for(int i=0; i<s.length(); i++) {
			int num = (int)s.charAt(i);
			
			if(num < 68) {
				time += 3;
			} else if(num < 71) {
				time += 4;
			} else if(num < 74) {
				time += 5;
			} else if(num < 77) {
				time += 6;
			} else if(num < 80) {
				time += 7;
			} else if(num < 84) {
				time += 8;
			} else if(num < 87) {
				time += 9;
			} else
				time += 10;
		}
		
		bw.write(time + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
