import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, R, nationCnt;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] union;
	static boolean[][] unionedCheck;
	static boolean[][] visited;
	static int[][] map;
	static List<List<Point>> nations;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 세팅
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		unionedCheck = new boolean[N][N];
		nations = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = play();
		System.out.println(answer);
	}
	
	private static int play() {
		int day = 0;
		
		while (true) {
			boolean flag = false;
			union = new boolean[N][N];
			nations = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!union[i][j] && !unionedCheck[i][j]) {
						nations.add(new ArrayList<>());
						bfs(i, j);
						
						if (nationCnt > 1) {
							flag = true;
						}
					}
				}
			}
			
			if (!flag) {
				break;
			}
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(unionedCheck[i], true);
			}
			
			for (List<Point> unionNation : nations) {
				if (unionNation.size() > 1) {
					move(unionNation);
				}
			}
			
			day++;
		}
		
		return day;
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		visited = new boolean[N][N];
		nationCnt = 1;
		q.offer(new Point(r, c));
		nations.get(nations.size() - 1).add(new Point(r, c));
		union[r][c] = true;
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			Point nation = q.poll();
			r = nation.x;
			c = nation.y;
			int person = map[r][c];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (isIn(nr, nc) && !union[nr][nc] && !visited[nr][nc]) {
					int person2 = map[nr][nc];
					int gap = Math.abs(person - person2);
					// 국경 열기
					if (gap >= L && gap <= R) {
						q.offer(new Point(nr, nc));
						nations.get(nations.size() - 1).add(new Point(nr ,nc));
						union[nr][nc] = true;
						visited[nr][nc] = true;
						nationCnt++;
					}
				}
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	private static void move(List<Point> unionNation) {
		int personCnt = 0;
		nationCnt = 0;
		for (Point nation : unionNation) {
			personCnt += map[nation.x][nation.y];
			nationCnt++;
		}
		
		for (Point nation : unionNation) {
			map[nation.x][nation.y] = personCnt / nationCnt;
			unionedCheck[nation.x][nation.y] = false;
		}
	}
}