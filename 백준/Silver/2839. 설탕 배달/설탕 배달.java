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
		int threeKg = 0;
		int fiveKg = n / 5;
		int result = 0;
		
		for(int i = n / 5; i >= 0; i--) {
			if(i == 0) {
				if(n % 3 == 0) {
					fiveKg = 0;
					threeKg = n / 3;
					result = threeKg;
				} else 
					result = - 1;
			} else {
				if((n - (i * 5)) % 3 == 0) {
					fiveKg = i;
					threeKg = (n - (i * 5)) / 3;
					result = fiveKg + threeKg;
					break;
				}
			}
		}
		
		bw.write(result+"");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
