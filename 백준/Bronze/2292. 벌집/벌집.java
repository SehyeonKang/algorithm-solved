import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int answer = 0;		
		int N = Integer.parseInt(br.readLine());
		int i = 0;
		
		while (true) {
			if(N <= 1 + 6 * i * (i + 1) / 2) {
				answer = i + 1;
				break;
			}
			i++;
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}