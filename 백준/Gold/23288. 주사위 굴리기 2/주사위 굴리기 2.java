import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[][] map;
	static Dice dice;
	
	static class Dice {
		int[] verticalNumbers = {1, 5, 6, 2};
		int[] horizonNumbers = {1, 3, 6, 4};
		
		public void move(int flag) {
			if (flag == 0) {
				moveEast();
			} else if (flag == 1) {
				moveSouth();
			} else if (flag == 2) {
				moveWest();
			} else {
				moveNorth();
			}
		}
		
		private void moveEast() {
			int tmp = horizonNumbers[3];
			for (int i = 3; i > 0; i--) {
				horizonNumbers[i] = horizonNumbers[i - 1];
			}
			horizonNumbers[0] = tmp;
			verticalNumbers[0] = horizonNumbers[0];
			verticalNumbers[2] = horizonNumbers[2];
		}
		
		private void moveSouth() {
			int tmp = verticalNumbers[3];
			for (int i = 3; i > 0; i--) {
				verticalNumbers[i] = verticalNumbers[i - 1];
			}
			verticalNumbers[0] = tmp;
			horizonNumbers[0] = verticalNumbers[0];
			horizonNumbers[2] = verticalNumbers[2];
		}
		
		private void moveWest() {
			int tmp = horizonNumbers[0];
			for (int i = 0; i < 3; i++) {
				horizonNumbers[i] = horizonNumbers[i + 1];
			}
			horizonNumbers[3] = tmp;
			verticalNumbers[0] = horizonNumbers[0];
			verticalNumbers[2] = horizonNumbers[2];
		}
		
		private void moveNorth() {
			int tmp = verticalNumbers[0];
			for (int i = 0; i < 3; i++) {
				verticalNumbers[i] = verticalNumbers[i + 1];
			}
			verticalNumbers[3] = tmp;
			horizonNumbers[0] = verticalNumbers[0];
			horizonNumbers[2] = verticalNumbers[2];
		}
		
		private int getBottom() {
			return verticalNumbers[2];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int score = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new Dice();
		int dir = 0;
		int r = 0;
		int c = 0;
		for (int time = 0; time < K; time++) {
			// 1. 주사위가 이동방향으로 굴러간다.
			if (isIn(r + dr[dir], c + dc[dir])) {
				r += dr[dir];
				c += dc[dir];
			} else {
				dir = (dir + 2) % 4;
				r += dr[dir];
				c += dc[dir];
			}
			dice.move(dir);
			
			// 2. 주사위가 도착한 칸에 대한 점수를 획득한다.
			score += addScore(r, c);
			
			// 3. 이동 방향을 결정한다.
			int diceNum = dice.getBottom();
			int mapNum = map[r][c];
			if (diceNum > mapNum) {
				dir = (dir + 1) % 4;
			} else if (diceNum < mapNum) {
				dir = (dir + 3) % 4;
			}
		}
		
		System.out.println(score);
	}
	
	private static int addScore(int r, int c) {
		int num = map[r][c];
		int cnt = 1;
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new Point(r, c));
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Point p = q.poll();
				r = p.x;
				c = p.y;
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == num) {
						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
						cnt++;
					}
				}
			}
		}
		
		
		return num * cnt;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
