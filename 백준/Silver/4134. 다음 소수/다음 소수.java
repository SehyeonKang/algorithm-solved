import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < T; testCase++) {
			long n = Long.parseLong(br.readLine());
			
			while (true) {
				boolean flag = false;
				
				for (int i = 2; i <= Math.sqrt(n); i++) {
					if (n % i == 0) {
						flag = true;
						break;
					}
				}
				
				if (!flag && n > 1 && n != 4) {
					break;
				}
				
				n++;
			}
			
			System.out.println(n);
		}
	}
}