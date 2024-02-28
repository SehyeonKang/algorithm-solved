import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, T;
	static int[] aircon;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 세팅
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		aircon = new int[2];
		int airconIdx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					aircon[airconIdx++] = i;
				}
			}
		}
		
		play();
		int answer = calculate();
		System.out.println(answer);
	}
	
	private static void play() {
		for (int time = 0; time < T; time++) {
			diffusion();
			operate();
		}
	}
	
	// 미세먼지가 확산된다
	private static void diffusion() {
		int[][] newMap = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// 미세먼지가 있는 칸인 경우
				if (map[r][c] > 0) {
					int cnt = 0;
					int dust = map[r][c] / 5;
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						
						// 인접한 방향에 칸이 있고, 공기청정기가 없는 경우
						if (isIn(nr, nc) && map[nr][nc] > -1) {
							newMap[nr][nc] += dust;
							cnt++;
						}
					}
					newMap[r][c] += map[r][c] - dust * cnt;
				}
			}
		}
		newMap[aircon[0]][0] = -1;
		newMap[aircon[1]][0] = -1;
		map = newMap;
	}
	
	// 공기청정기가 작동한다
	private static void operate() {
		for (int idx = 0; idx < 2; idx++) {
			int r = aircon[idx];

			// 공기청정기 위쪽 작동
			if (idx == 0) {
				int dir = 0;
				int nr = r + dr[dir];
				int nc = dc[dir];
				while (true) {
					// 공기청정기로 돌아올 경우
					if (nr == r && nc == 0) {
						break;
					}
					
					// 맵 밖으로 방향을 벗어나는 경우 방향 전환
					if (!isIn(nr, nc) || nr > r) {
						nr -= dr[dir];
						nc -= dc[dir];
						dir += 1;
						nr += dr[dir];
						nc += dc[dir];
						continue;
					}
					
					// 먼지의 이동 방향이 공기청정기가 아닌 경우
					if (!(nr - dr[dir] == r && nc - dc[dir] == 0)) {
						map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
					}
					map[nr][nc] = 0;
					
					// 먼지 탐색 이동
					nr += dr[dir];
					nc += dc[dir];
				}
			
			// 공기청정기 아래쪽 작동
			} else {
				int dir = 2;
				int nr = r + dr[dir];
				int nc = dc[dir];
				while (true) {
					// 공기청정기로 돌아올 경우
					if (nr == r && nc == 0) {
						break;
					}
					
					// 맵 밖으로 방향을 벗어나는 경우 방향 전환
					if (!isIn(nr, nc) || nr < r) {
						nr -= dr[dir];
						nc -= dc[dir];
						dir = (dir + 3) % 4;
						nr += dr[dir];
						nc += dc[dir];
						continue;
					}
					
					// 먼지의 이동 방향이 공기청정기가 아닌 경우
					if (!(nr - dr[dir] == r && nc - dc[dir] == 0)) {
						map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
					}
					map[nr][nc] = 0;
					
					// 먼지 탐색 이동
					nr += dr[dir];
					nc += dc[dir];
				}
			}
		}
	}
	
	private static int calculate() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}