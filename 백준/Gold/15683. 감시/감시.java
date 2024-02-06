import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int minArea = Integer.MAX_VALUE;
	static int N, M, cctvCount, emptyCount;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] selected;
	static int[][] map;
	static List<Cctv> cctvs;
	
	private static class Cctv {
		int r, c, num;

		public Cctv(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
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
		cctvs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctvs.add(new Cctv(i, j, map[i][j]));
				}
				
				if (map[i][j] == 0) {
					emptyCount++;
				}
			}
		}
		cctvCount = cctvs.size();
		selected = new int[cctvs.size()];
		
		recur(0);
		System.out.println(minArea);
		
	}
	
	private static void recur(int count) {
		if (count == cctvCount) {
			int area = operate();
			minArea = Math.min(minArea, area);
			return;
		}
		
		// 각 CCTV의 회전 경우에 수에 맞춰 반복
		Cctv cctv = cctvs.get(count);
		if (cctv.num == 1 || cctv.num == 3 || cctv.num == 4) {
			for (int i = 0; i < 4; i++) {
				selected[count] = i;
				recur(count + 1);
			}
		} else if (cctv.num == 2) {
			for (int i = 0; i < 2; i++) {
				selected[count] = i;
				recur(count + 1);
			}
		} else {
			selected[count] = 0;
			recur(count + 1);
		}
	}
	
	private static int operate() {
		int count = 0;
		int[][] newMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < cctvs.size(); i++) {
			Cctv cctv = cctvs.get(i);
			int r = cctv.r;
			int c = cctv.c;
			int num = cctv.num;
			int direction = selected[i];
			
			// 1번 CCTV 작동
			if (num == 1) {
				int nr = r;
				int nc = c;
				while (true) {
					nr += dr[direction];
					nc += dc[direction];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
			} else if (num == 2) { // 2번 CCTV 작동
				int nr = r;
				int nc = c;
				while (true) {
					nr += dr[direction];
					nc += dc[direction];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
				
				nr = r;
				nc = c;
				while (true) {
					nr += dr[direction + 2];
					nc += dc[direction + 2];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
			} else if (num == 3) { // 3번 CCTV 작동
				int nr = r;
				int nc = c;
				while (true) {
					nr += dr[direction];
					nc += dc[direction];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
				
				nr = r;
				nc = c;
				while (true) {
					nr += dr[(direction + 1) % 4];
					nc += dc[(direction + 1) % 4];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
			} else if (num == 4) { // 4번 CCTV 작동
				int nr = r;
				int nc = c;
				while (true) {
					nr += dr[direction];
					nc += dc[direction];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
				
				nr = r;
				nc = c;
				while (true) {
					nr += dr[(direction + 1) % 4];
					nc += dc[(direction + 1) % 4];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
				
				nr = r;
				nc = c;
				while (true) {
					nr += dr[(direction + 2) % 4];
					nc += dc[(direction + 2) % 4];
					
					// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
					if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
						break;
					}
					
					if (newMap[nr][nc] == 0) {
						newMap[nr][nc] = 7;
						count++;
					}
				}
			} else if (num == 5) { // 5번 CCTV 작동
				for (int j = 0; j < 4; j++) {
					int nr = r;
					int nc = c;
					while (true) {
						nr += dr[j];
						nc += dc[j];
						
						// 맵 밖으로 나가거나 벽이 있을 경우 시야 차단
						if (!isIn(nr, nc) || newMap[nr][nc] == 6) {
							break;
						}
						
						if (newMap[nr][nc] == 0) {
							newMap[nr][nc] = 7;
							count++;
						}
					}
				}
			}
		}
		
		return emptyCount - count;
	}
	
	private static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M) {
			return true;
		}
		return false;
	}
}