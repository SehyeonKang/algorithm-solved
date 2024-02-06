import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, wallCount, virusCount;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] map;
	static List<Pos> inputs;
	static List<Pos> empties;
	static Pos[] walls;
	static int maxArea = 0;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
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
		inputs = new ArrayList<>();
		empties = new ArrayList<>();
		walls = new Pos[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					empties.add(new Pos(i, j));
				} else if (map[i][j] == 1) {
					wallCount++;
				} else if (map[i][j] == 2) {
					inputs.add(new Pos(i, j));
				}
			}
		}
		
		virusCount = inputs.size();
		recur(0, 0);
		System.out.println(maxArea);
	}
	
	private static void recur(int count, int start) {
		if (count == 3) {
			for (int i = 0; i < count; i++) {
				Pos pos = walls[i];
				map[pos.r][pos.c] = 1;
			}
			
			bfs();
			
			for (int i = 0; i < count; i++) {
				Pos pos = walls[i];
				map[pos.r][pos.c] = 0;
			}
			
			return;
		}
		
		for (int i = start; i < empties.size(); i++) {
			walls[count] = empties.get(i);
			recur(count + 1, i + 1);
		}
	}
	
	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		for (Pos pos : inputs) {
			q.offer(pos);
		}
		
		int[][] bfsMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bfsMap[i][j] = map[i][j];
			}
		}
		
		int virusNum = virusCount;
		int wallNum = wallCount + 3;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos pos = q.poll();
				int r = pos.r;
				int c = pos.c;
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if (isIn(nr, nc) && bfsMap[nr][nc] == 0) {
						bfsMap[nr][nc] = 2;
						q.offer(new Pos(nr, nc));
						virusNum++;
					}
				}
			}
		}
		
		int area = N * M - virusNum - wallNum;
		maxArea = Math.max(maxArea, area);
	}
	
	
	private static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M) {
			return true;
		}
		return false;
	}
}