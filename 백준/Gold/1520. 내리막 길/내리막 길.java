import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N;
	static int[][] map;
	static int[][] dp;
	static int[][] memo;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}
		
		dfs(0, 0);
		
		System.out.println(dp[0][0]);
	}
	
	private static int dfs(int r, int c) {
		// 목표 지점에 도착했을 경우
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		
        // 방문한 적이 없는 경우
		if (dp[r][c] == -1) {
			dp[r][c] = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (isIn(nr, nc) && map[r][c] > map[nr][nc]) {
					dp[r][c] += dfs(nr, nc);
				}
			}
		}
		return dp[r][c];
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}
