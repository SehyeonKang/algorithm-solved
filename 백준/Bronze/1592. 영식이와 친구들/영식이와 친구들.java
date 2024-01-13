import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		int[] ballCounts = new int[N + 1];
		int currentBall = 1;
		ballCounts[currentBall] = 1;
		
		while (true) {
			if (ballCounts[currentBall] == M) {
				break;
			}
			
			if (ballCounts[currentBall] % 2 == 1) {
				currentBall += L;
				if (currentBall > N) {
					currentBall -= N;
				}
				ballCounts[currentBall]++;
			} else {
				currentBall -= L;
				if (currentBall < 1) {
					currentBall += N;
				}
				ballCounts[currentBall]++;
			}
			answer++;
		}
		
		System.out.println(answer);
	}
}