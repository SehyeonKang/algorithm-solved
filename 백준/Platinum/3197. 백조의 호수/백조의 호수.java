import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, answer, startR, startC;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] personVisited;
	static Queue<Point> iceQ;
	static Queue<Point> bufferIceQ;
	static Queue<Point> personQ;
	static Queue<Point> bufferPersonQ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 세팅
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				
				if (c == 'L') {
					startR = i;
					startC = j;
				}
			}
		}
		
		play();
		System.out.println(answer);
	}
	
	private static void play() {
		iceQ = new ArrayDeque<>();
		bufferIceQ = new ArrayDeque<>();
		personQ = new ArrayDeque<>();
		bufferPersonQ = new ArrayDeque<>();
		
		visited = new boolean[R][C];
		personVisited = new boolean[R][C];
		
		// 처음에 바로 두 사람이 만날 수 있는지 확인하기
		personVisited = new boolean[R][C];
		if (findPerson(startR, startC)) {
			return;
		}
		
		personVisited = new boolean[R][C];
		// 두 사람이 만날 수 있을 때까지 반복
		while (true) {
			answer++;
			
			if (answer > 1) {
				while (!bufferIceQ.isEmpty()) {
					iceQ.offer(bufferIceQ.poll());
				}
				while (!bufferPersonQ.isEmpty()) {
					personQ.offer(bufferPersonQ.poll());
				}
				
				int qSize = iceQ.size();
				for (int i = 0; i < qSize; i++) {
					Point p = iceQ.poll();
					bfs(p.x, p.y);
				}
				
				int pqSize = personQ.size();
				for (int i = 0; i < pqSize; i++) {
					Point p = personQ.poll();
					if (findPerson(p.x, p.y)) {
						return;
					}
				}
			} else {
				// 빙하 녹이기
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						// 방문한 적이 없고, 일반 땅이라면
						if (!visited[i][j] && (map[i][j] == '.' || map[i][j] == 'L')) {
							bfs(i, j);
						}
					}
				}
				
				// 두 사람이 만날 수 있는지 확인하기
				if (findPerson(startR, startC)) {
					return;
				}
			}
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			
			Point p = q.poll();
			r = p.x;
			c = p.y;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if (isIn(nr, nc) && !visited[nr][nc]) {
					// 방문한 곳이 빙하라면 녹이고, 해당 방향 진행 X
					if (map[nr][nc] == 'X') {
						map[nr][nc] = '.';
						bufferIceQ.offer(new Point(nr, nc));
					// 방문한 곳이 일반 땅이라면 해당 방향 진행
					} else {
						q.offer(new Point(nr, nc));
					}
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	private static boolean findPerson(int r, int c) {
		boolean findFlag = false;
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		personVisited[r][c] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			r = p.x;
			c = p.y;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if (isIn(nr, nc) && !personVisited[nr][nc]) {
					// 방문한 곳이 일반 땅이라면 진행
					if (map[nr][nc] == '.') {
						q.offer(new Point(nr, nc));
					} else if (map[nr][nc] == 'L') {
						findFlag = true;
					} else if (map[nr][nc] == 'X') {
						bufferPersonQ.offer(new Point(nr, nc));
					}
					personVisited[nr][nc] = true;
				}
				
				if (findFlag) {
					break;
				}
			}
			
			if (findFlag) {
				break;
			}
		}
		
		return findFlag;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}