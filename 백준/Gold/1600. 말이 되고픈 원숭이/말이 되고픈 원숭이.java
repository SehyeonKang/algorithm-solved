import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	
	static class Node {
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = bfs();
		System.out.println(answer);
	}
	
	private static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0, K));
		visited[0][0][K] = true;
		int time = 1;
		
		if (H == 1 && W == 1) {
			return 0;
		}
		
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Node cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				int cnt = cur.cnt;
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if (nr == H - 1 && nc == W - 1) {
						return time;
					}
					
					if (isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][cnt]) {
						q.offer(new Node(nr, nc, cnt));
						visited[nr][nc][cnt] = true;
					}
				}
				
				if (cnt > 0) {
					for (int j = 4; j < 12; j++) {
						int nr = r + dr[j];
						int nc = c + dc[j];
						
						if (nr == H - 1 && nc == W - 1) {
							return time;
						}
						
						if (isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][cnt - 1]) {
							q.offer(new Node(nr, nc, cnt - 1));
							visited[nr][nc][cnt - 1] = true;
						}
					}
				}
			}
			time++;
		}
		
		return -1;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
