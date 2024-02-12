import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static CurveInfo[] infos;
	static boolean[][] map;
	
	static class CurveInfo {
		int r, c, d, g;

		public CurveInfo(int x, int y, int d, int g) {
			this.r = y;
			this.c = x;
			this.d = d;
			this.g = g;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		infos = new CurveInfo[N];
		map = new boolean[101][101];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i] = new CurveInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		makeDragonCurve();
		
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void makeDragonCurve() {
		for (int tc = 0; tc < N; tc++) {
			CurveInfo info = infos[tc];
			ArrayList<Integer> list = new ArrayList<>();
			list.add(info.d);
			map[info.r][info.c] = true;
			map[info.r + dr[info.d]][info.c + dc[info.d]] = true;
			int r = info.r + dr[info.d];
			int c = info.c + dc[info.d];
			
			for (int age = 0; age < info.g; age++) {
				int size = list.size();
				for (int i = size - 1; i >= 0; i--) {
					int dir = list.get(i);
					int nd = (dir + 1) % 4;
					int nr = r + dr[nd];
					int nc = c + dc[nd];
					
					if (isIn(nr, nc)) {
						list.add(nd);
						r = nr;
						c = nc;
						if (!map[nr][nc]) {
							map[nr][nc] = true;
						}
					}
				}
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < 101 && c < 101;
	}
}