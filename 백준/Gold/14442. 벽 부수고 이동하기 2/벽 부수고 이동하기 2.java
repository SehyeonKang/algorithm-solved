import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, result;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Node {
		int r, c, dis, breakCnt;

		public Node(int r, int c, int dis, int breakCnt) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.breakCnt = breakCnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K + 1];
		result = -1;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Node node = q.poll();
				int r = node.r;
				int c = node.c;
				int dis = node.dis;
				int breakCnt = node.breakCnt;
				
				if (r == N - 1 && c == M - 1) {
					result = dis;
					return;
				}
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if (isIn(nr, nc)) {
						if (map[nr][nc] == 0 && !visited[nr][nc][breakCnt]) {
							q.offer(new Node(nr, nc, dis + 1, breakCnt));
							visited[nr][nc][breakCnt] = true;
						} else if (breakCnt < K && !visited[nr][nc][breakCnt + 1]) {
							q.offer(new Node(nr, nc, dis + 1, breakCnt + 1));
							visited[nr][nc][breakCnt + 1] = true;
						}
						
					}
				}
				
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
