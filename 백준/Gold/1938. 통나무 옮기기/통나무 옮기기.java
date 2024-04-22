import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// D: 0이면 가로, 1이면 세로
	static int N, startR, startC, startD, endR, endC, endD;
	static boolean[][][] visited;
	static int[][] map;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static class Train {
		int r, c, dir;

		public Train(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N][2];
		Point[] startCheck = new Point[3];
		Point[] endCheck = new Point[3];
		int sIdx = 0;
		int eIdx = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if (c == 'B') {
					startCheck[sIdx++] = new Point(i, j);
				} else if (c == 'E') {
					endCheck[eIdx++] = new Point(i, j);
				}
				
				map[i][j] = c - '0';
				if (c == 'B' || c == 'E') {
					map[i][j] = 0;
				}
			}
		}
		
		if (startCheck[0].y == startCheck[1].y) {
			startD = 1;
		}
		if (endCheck[0].y == endCheck[1].y) {
			endD = 1;
		}
		startR = startCheck[1].x;
		startC = startCheck[1].y;
		endR = endCheck[1].x;
		endC = endCheck[1].y;
		
		visited[startR][startC][startD] = true;
		int result = bfs();
		System.out.println(result);
	}
	
	private static int bfs() {
		Queue<Train> q = new ArrayDeque<>();
		q.offer(new Train(startR, startC, startD));
		
		int time = 1;
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Train train = q.poll();
				int r = train.r;
				int c = train.c;
				int dir = train.dir;
				
				if (r == endR && c == endC && dir == endD) {
					return time - 1;
				}

				// 가로 방향인 경우
				if (dir == 0) {
					// 위로 이동
					int nr = r - 1;
					int nc = c;
					if (isIn(nr, nc) && !visited[nr][nc][dir]
							&& map[nr][nc - 1] == 0 && map[nr][nc] == 0 && map[nr][nc + 1] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					// 아래로 이동
					nr = r + 1;
					if (isIn(nr, nc) && !visited[nr][nc][dir]
							&& map[nr][nc - 1] == 0 && map[nr][nc] == 0 && map[nr][nc + 1] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 왼쪽으로 이동
					nr = r;
					nc = c - 1;
					if (isIn(nr, nc - 1) && !visited[nr][nc][dir]
							&& map[nr][nc - 1] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 오른쪽으로 이동
					nc = c + 1;
					if (isIn(nr, nc + 1) && !visited[nr][nc][dir]
							&& map[nr][nc + 1] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 회전
					int nDir = 1;
					if (isIn(r - 1, c) && isIn(r + 1, c) && !visited[r][c][nDir]) {
						boolean empty = true;
						for (int d = 0; d < 8; d++) {
							if (map[r + dr[d]][c + dc[d]] == 1) {
								empty = false;
								break;
							}
						}
						if (empty) {
							q.offer(new Train(r, c, nDir));
							visited[r][c][nDir] = true;
						}
					}
				// 세로 방향인 경우
				} else {
					// 위로 이동
					int nr = r - 1;
					int nc = c;
					if (isIn(nr - 1, nc) && !visited[nr][nc][dir]
							&& map[nr - 1][nc] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					// 아래로 이동
					nr = r + 1;
					if (isIn(nr + 1, nc) && !visited[nr][nc][dir]
							&& map[nr + 1][nc] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 왼쪽으로 이동
					nr = r;
					nc = c - 1;
					if (isIn(nr, nc) && !visited[nr][nc][dir]
							&& map[nr - 1][nc] == 0 && map[nr][nc] == 0 && map[nr + 1][nc] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 오른쪽으로 이동
					nc = c + 1;
					if (isIn(nr, nc) && !visited[nr][nc][dir]
							&& map[nr - 1][nc] == 0 && map[nr][nc] == 0 && map[nr + 1][nc] == 0) {
						q.offer(new Train(nr, nc, dir));
						visited[nr][nc][dir] = true;
					}
					
					// 회전
					int nDir = 0;
					if (isIn(r, c - 1) && isIn(r, c + 1) && !visited[r][c][nDir]) {
						boolean empty = true;
						for (int d = 0; d < 8; d++) {
							if (map[r + dr[d]][c + dc[d]] == 1) {
								empty = false;
								break;
							}
						}
						if (empty) {
							q.offer(new Train(r, c, nDir));
							visited[r][c][nDir] = true;
						}
					}
				}
			}
			time++;
		}
		
		time = 0;
		return time;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
