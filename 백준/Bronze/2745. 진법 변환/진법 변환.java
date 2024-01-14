import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		int B = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N.length(); i++) {
			char c = N.charAt(i);
			
			if (c < 65) {
				answer += Character.getNumericValue(c) * Math.pow(B, N.length() - 1 - i);
			} else {
				answer += (c -55) * Math.pow(B, N.length() - 1 - i);
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}