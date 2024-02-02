import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int HORIZON = 0;
	static final int DIAGONAL = 1;
	static final int VERTICAL = 2;
	
	static int answer = 0;
	static int N;
	static int[][] map;
	
	// 가로, 대각선, 세로 이동
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 초기값 세팅
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파이프 끝 부분이 (0, 1) 위치에서 가로 방향으로 시작
		recur(0, 1, HORIZON);
		System.out.println(answer);
		
	}
	
	/*
	 *  r, c: N, N칸에 들어가야 하는 파이프 끝쪽 좌표
	 *  dir: 파이프 방향. HORIZON: 가로, VERTICAL: 세로, DIAGONAL: 대각선
	 */
	private static void recur(int r, int c, int dir) {
		// 파이프가 N, N칸에 도착했을 경우
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}
		
		// 끝 부분이 맵 끝쪽이고 회전할 수 없어서 더 이상 이동할 수 없는 경우
		if ((r == N - 1 && dir == VERTICAL) || (c == N - 1 && dir == HORIZON)) {
			return;
		}
		
		// 현재 방향에 따라 다음 동작 결정
		if (dir == HORIZON) {
			for (int i = 0; i < 2; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 다음에 움직일 공간이 맵 안이고, 움직일 공간에 벽이 없다면 이동
				if (isIn(nr, nc) && canMove(r, c, i)) {
					recur(nr, nc, i);
				}
			}
		} else if (dir == DIAGONAL) {
			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 다음에 움직일 공간이 맵 안이고, 움직일 공간에 벽이 없다면 이동
				if (isIn(nr, nc) && canMove(r, c, i)) {
					recur(nr, nc, i);
				}
			}
		} else {
			for (int i = 1; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 다음에 움직일 공간이 맵 안이고, 움직일 공간에 벽이 없다면 이동
				if (isIn(nr, nc) && canMove(r, c, i)) {
					recur(nr, nc, i);
				}
			}
		}
	}
	
	// 움직일 수 있는지 가로, 대각선, 세로의 경우로 나눠서 확인
	private static boolean canMove(int r, int c, int moveDir) {
		if (moveDir == HORIZON) {
			if (map[r + dr[0]][c + dc[0]] == 0) {
				return true;
			}
			return false;
		} else if (moveDir == VERTICAL) {
			if (map[r + dr[2]][c + dc[2]] == 0) {
				return true;
			}
			return false;
		} else {
			if (map[r + dr[0]][c + dc[0]] == 0
					&& map[r + dr[2]][c + dc[2]] == 0
					&& map[r + dr[1]][c + dc[1]] == 0) {
				return true;
			}
			return false;
		}
	}
	
	// r, c 좌표가 맵 안에 있는지 확인
	private static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N) {
			return true;
		}
		return false;
	}
}