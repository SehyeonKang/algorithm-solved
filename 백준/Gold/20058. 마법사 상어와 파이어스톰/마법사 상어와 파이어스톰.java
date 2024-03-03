import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, Q, L;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	static int[] magics;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 세팅
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		magics = new int[Q];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			magics[i] = Integer.parseInt(st.nextToken());
		}
		
		play();
		
		int answer1 = calculateIceSum();
		int answer2 = calculateMaximumIce();
		System.out.println(answer1 + "\n" + answer2);
	}
	
	private static void play() {
		for (int i = 0; i < Q; i++) {
			L = (int) Math.pow(2, magics[i]);
			
			for (int r = 0; r < N; r += L) {
				for (int c = 0; c < N; c += L) {
					rotatePartition(r, c);
				}
			}
			
			reduceIce();
		}
	}
	
	// 시작점 (r, c), 크기 L의 부분 격자를 회전시키기
	private static void rotatePartition(int r, int c) {
		int[][] partition = new int[L][L];
		for (int i = r; i < r + L; i++) {
			for (int j = c; j < c + L; j++) {
				partition[i - r][j - c] = map[r + L - 1 - j + c][c + i - r];
			}
		}
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				map[r + i][c + j] = partition[i][j];
			}
		}
	}
	
	// 얼음의 양 감소시키기
	private static void reduceIce() {
		int[][] newMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 4;
				for (int d = 0; d < 4; d++) {
					int r = i + dr[d];
					int c = j + dc[d];
					
					if (!isIn(r, c)) {
						cnt--;
						continue;
					}
					
					if (map[r][c] == 0) {
						cnt--;
					}
					
					if (cnt < 3) {
						break;
					}
				}
				
				if (cnt < 3 && map[i][j] > 0) {
					newMap[i][j] = map[i][j] - 1;
				} else {
					newMap[i][j] = map[i][j];
				}
			}
		}
		
		map = newMap;
	}
	
	// 남아있는 얼음의 합 구하기
	private static int calculateIceSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	// 남아있는 얼음 중 가장 큰 덩어리의 칸 개수 구하기
	private static int calculateMaximumIce() {
		int iceSize = 0;
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					int size = bfs(i, j);
					iceSize = Math.max(iceSize, size);
				}
			}
		}
		
		return iceSize;
	}
	
	private static int bfs(int r, int c) {
		int size = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		size++;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			r = p.x;
			c = p.y;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					size++;
				}
			}
		}
		
		return size;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}

