import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int answer = -1;
	static int tomatoCount = 0;
	static int M;
	static int N;
	
	static class Position {
		int row;
		int col;
		
		Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		Queue<Position> queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == 1) {
					queue.offer(new Position(i, j));
					tomatoCount++;
				} else if (n == -1) {
					tomatoCount++;
				}
			}
		}
		
		if (tomatoCount == M * N) {
			answer = 0;
		} else {
			bfs(map, queue);			
		}
		
		if (tomatoCount < M * N) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
	
	static void bfs(int[][] map, Queue<Position> queue) {
		while (!queue.isEmpty()) {
			int count = queue.size();
			
			for (int i = 0; i < count; i++) {
				Position pos = queue.poll();
				int row = pos.row;
				int col = pos.col;
				
				for (int j = 0; j < 4; j++) {
					int nRow = row + dr[j];
					int nCol = col + dc[j];
					
					if (nRow > -1 && nCol > -1 && nRow < N && nCol < M && map[nRow][nCol] == 0) {
						map[nRow][nCol] = 1;
						queue.offer(new Position(nRow, nCol));
						tomatoCount++;
					}
				}
			}
			answer++;
		}
	}
}