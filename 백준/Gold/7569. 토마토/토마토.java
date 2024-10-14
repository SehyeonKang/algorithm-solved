import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int M, N, H, count;
	static int[][][] tomatoes;
	static ArrayDeque<Position> queue = new ArrayDeque<>();
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dn = {0, 0, -1, 1, 0, 0};
	static int[] dm = {0, 0, 0, 0, -1, 1};

	public static class Position {
		int h, n, m;

		public Position(int h, int n, int m) {
			this.h = h;
			this.n = n;
			this.m = m;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomatoes = new int[H][N][M];
		int answer = -1;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomatoes[i][j][k] == 0)
						count++;

					if (tomatoes[i][j][k] == 1)
						queue.offer(new Position(i, j, k));
				}
			}
		}

		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) {
				Position p = queue.poll();

				for (int j = 0; j < 6; j++) {
					int nh = p.h + dh[j];
					int nn = p.n + dn[j];
					int nm = p.m + dm[j];

					if (isIn(nh, nn, nm) && tomatoes[nh][nn][nm] == 0) {
						tomatoes[nh][nn][nm] = 1;
						queue.offer(new Position(nh, nn, nm));
						count--;
					}
				}
			}
			answer++;
		}

		if (count > 0) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	static boolean isIn(int h, int n, int m) {
		return h >= 0 && n >= 0 && m >= 0 && h < H && n < N && m < M;
	}

}
