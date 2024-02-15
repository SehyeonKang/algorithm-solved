import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, answer;
	static boolean endFlag;
	// 오른쪽 위, 가운데, 아래 순서
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		dfs(0, 0, 0, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int startR, int r, int c, int count) {
		if (endFlag) {
			return;
		}
		
		if (startR == R) {
			answer = count;
			endFlag = true;
			return;
		}
		
		for (int dir = 0; dir < 3; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (isIn(nr) && map[nr][nc] == '.') {
				map[nr][nc] = 'x';
				if (nc == C - 1) {
					dfs(startR + 1, startR + 1, 0, count + 1);
				} else {
					dfs(startR, nr, nc, count);
				}
			}
		}
		
		if (c == 0) {
			dfs(startR + 1, startR + 1, 0, count);
		}
	}
	
	private static boolean isIn(int r) {
		return r >= 0 && r < R;
	}
}