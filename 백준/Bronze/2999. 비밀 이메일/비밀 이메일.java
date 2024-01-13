import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String message = br.readLine();
		int N = message.length();
		int R = 0;
		int C = 0;
		
		for (int i = 1; i < N; i++) {
			if (N % i == 0) {
				if (i > N / i) {
					break;
				}
				R = i;
				C = N / i;
			}
		}
		
		char[][] mat = new char[R][C];
		int cnt = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				mat[j][i] = message.charAt(cnt++);
			}
		}
		
		String answer = "";
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += mat[i][j]; 
			}
		}
		
		System.out.println(answer);
	}
}