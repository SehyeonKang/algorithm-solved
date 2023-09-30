import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int result = 0;
		
		for(int i=1; i<=n; i++) {
			int tmp = 0;
			
			if(i < 100) {
				tmp = i + i/10 + i%10;
				if(tmp == n) {
					result = i;
					break;
				}
			} else if(i < 1000) {
				tmp = i + i/100 + i/10%10 + i%10;
				if(tmp == n) {
					result = i;
					break;
				}
			} else if(i < 10000) {
				tmp = i + i/1000 + i/100%10 + i/10%10 + i%10;
				if(tmp == n) {
					result = i;
					break;
				}
			} else if(i < 100000) {
				tmp = i + i/10000 + i/1000%10 + i/100%10 + i/10%10 + i%10;
				if(tmp == n) {
					result = i;
					break;
				}
			} else {
				tmp = i + i/100000 + i/10000%10 + i/1000%10 + i/100%10 + i/10%10 + i%10;
				if(tmp == n) {
					result = i;
					break;
				}
			}
		}
		
		bw.write(result + "");
			
		bw.flush();
		bw.close();
		br.close();
	}
}
