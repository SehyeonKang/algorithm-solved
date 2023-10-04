import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int max = 0;
		int cnt = 0;
		
		for(int i = 0; i < 9; i++) {
			int n = Integer.parseInt(br.readLine());
			if(max < n) {
				max = n;
				cnt = i + 1;
			}
		}
		
		bw.write(max + "\n" + cnt);
		
		bw.flush();
		bw.close();
		br.close();
	}
}