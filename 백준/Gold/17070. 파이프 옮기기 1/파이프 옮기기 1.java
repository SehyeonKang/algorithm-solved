import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	/* 3번째 index 0: 가로, 1: 세로, 2: 대각선
	 * (1번째 index, 2번째 index)칸에 3번째 index 모양으로 올 수 있는 경우의 수 저장
	 */
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		play();
		int answer = 0;
		for (int i = 0; i < 3; i++) {
			answer += dp[N - 1][N - 1][i];
		}
		System.out.println(answer);
	}
	
	private static void play() {
		// 초기 위치
		dp[0][1][0] = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 2; c < N; c++) {
				// (r, c) 위치에 벽이 있을 경우
				if (map[r][c] > 0) {
					continue;
				}
				
				// 가로로 이동하는 경우
				dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
				if (r > 0) {
					
					// 세로로 이동하는 경우
					dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
					
					// 대각선으로 이동할 때 이동 범위에 벽이 없는 경우
					if (map[r -1][c] == 0 && map[r][c - 1] == 0) {
						dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
					}
				}
			}
		}
	}
}