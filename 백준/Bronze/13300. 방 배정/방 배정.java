import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] info = new int[2][7];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			info[S][Y]++;
		}
		
		int answer = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 7; j++) {
				if (info[i][j] > 0) {
					if (info[i][j] % K == 0) {
						answer += info[i][j] / K;
					} else {
						answer += info[i][j] / K + 1;						
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}