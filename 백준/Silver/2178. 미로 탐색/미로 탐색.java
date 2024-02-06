import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int answer = 1;
	static int[][] map;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Pos {
		int r, c;
		
		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(answer);
	}
	
	private static void bfs() {
		boolean flag = false;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos pos = q.poll();
				int r = pos.r;
				int c = pos.c;
				
				if (r == N - 1 && c == M - 1) {
					flag = true;
					break;
				}
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if (isIn(nr, nc) && map[nr][nc] == 1) {
						map[nr][nc] = 0;
						q.offer(new Pos(nr, nc));
					}
				}
			}
			
			if (flag) {
				break;
			}
			
			answer++;
		}
	}
	
	private static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M) {
			return true;
		}
		return false;
	}
}