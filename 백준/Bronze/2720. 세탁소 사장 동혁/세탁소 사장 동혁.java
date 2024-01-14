import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			int C = Integer.parseInt(br.readLine());
			
			int quarterCount =C / 25;
			C %= 25;
			int dimeCount = C / 10;
			C %= 10;
			int nickelCount = C / 5;
			C %= 5;
			int pennyCount = C;
			
			sb.append(quarterCount + " " + dimeCount + " " + nickelCount + " " + pennyCount + "\n");
		}
		
		System.out.println(sb);
	}
}