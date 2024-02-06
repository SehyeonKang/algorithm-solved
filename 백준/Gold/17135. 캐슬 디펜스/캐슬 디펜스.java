import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, D, enemyCount, maxKill;
	static int[] dx = {0, -1, 0}; // 좌, 상, 우
	static int[] dy = {-1, 0, 1}; // 좌, 상, 우
	static int[] selected; // 궁수의 위치를 저장하는 배열
	static int[][] map;
	
	static class Enemy {
		int r, c;

		public Enemy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		selected = new int[3];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0);
		System.out.println(maxKill);
	}
	
	private static void recur(int count, int start) {
		if (count == 3) {
			play();
			return;
		}
		
		for (int i = start; i < M; i++) {
			selected[count] = i;
			recur(count + 1, start + 1);
		}
	}
	
	private static void play() {
		// 맵 깊은 복사
		int[][] battleMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				battleMap[i][j] = map[i][j];
			}
		}
		
		int killCount = 0;
		
		// 행의 수만큼 진행
		for (int time = 0; time < N; time++) {
			Point[] attacked = new Point[3]; // 공격받는 적의 위치를 저장하는 배열
			
			// 각 궁수마다 공격하는 적을 bfs로 찾음
			for (int archor = 0; archor < 3; archor++) {
				// 만약 궁수 바로 위에 적이 있는 경우, 시간이 지날 때마다 성의 행을 한 칸씩 올리기 위해 -time
				if (battleMap[N - 1 - time][selected[archor]] == 1) {
					attacked[archor] = new Point(N - 1 - time, selected[archor]);
					continue;
				}
				
				Queue<Point> q = new ArrayDeque<>();
				q.offer(new Point(N - 1 - time, selected[archor]));
				int distance = 1;
				boolean flag = false;
				
				while (!q.isEmpty() && distance < D) {
					int size = q.size();
					for (int i = 0; i < size; i++) {
						Point p = q.poll();
						int x = p.x;
						int y = p.y;
						
						for (int j = 0; j < 3; j++) {
							int nx = x + dx[j];
							int ny = y + dy[j];
							
							// 가장 가까운 적을 찾은 경우 (우, 상, 좌 순으로 탐색하므로 항상 가장 왼쪽)
							if (isIn(nx, ny) && battleMap[nx][ny] == 1) {
								attacked[archor] = new Point(nx, ny);
								flag = true;
								break;
							}
							
							if (isIn(nx, ny)) {
								q.offer(new Point(nx, ny));
							}
						}
						
						if (flag) {
							break;
						}
					}
					
					if (flag) {
						break;
					}
					
					distance++;
				}
			}
			
			// 적을 제거하고 킬 수 증가
			for (Point p : attacked) {
				if (p == null) {
					continue;
				}
				
				if (battleMap[p.x][p.y] == 1) {
					battleMap[p.x][p.y] = 0;
					killCount++;
				}
			}
		}
		
		maxKill = Math.max(maxKill, killCount);
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && y < M;
	}
}