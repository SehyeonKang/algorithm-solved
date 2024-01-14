import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int answer = 4;
		
		for (int i = 1; i <= N; i++) {
			answer += 5 * Math.pow(4, i - 1) - ((Math.pow(2, i - 1) - 1) * Math.pow(2, i));
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}