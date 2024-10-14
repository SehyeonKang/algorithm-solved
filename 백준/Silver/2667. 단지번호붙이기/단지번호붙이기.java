import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Integer> counts;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		counts = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(new Point(i, j));
					count++;
				}
			}
		}

		System.out.println(count);
		Collections.sort(counts);
		for (int c : counts) {
			System.out.println(c);
		}
	}

	static void bfs(Point start) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.add(new Point(start.x, start.y));
		visited[start.x][start.y] = true;
		int sum = 1;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p.x + dr[i];
				int nc = p.y + dc[i];

				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
					sum++;
				}
			}
		}

		counts.add(sum);
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

}
