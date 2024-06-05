import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, R;
	static int[][] map;
	static List<Integer> orders;
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		setMap();
		setOrders();
		play();
		printMap();
		
	}
	

	private static void setMap() throws IOException {
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	
	private static void setOrders() throws IOException {
		st = new StringTokenizer(br.readLine());
		orders = new ArrayList<>();
		while (st.hasMoreTokens()) {
			orders.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	private static void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void play() {
		for (int order : orders) {
			if (order == 1) {
				calculate1();
			} else if (order == 2) {
				calculate2();
			} else if (order == 3) {
				calculate3();
			} else if (order == 4) {
				calculate4();
			} else if (order == 5) {
				calculate5();
			} else {
				calculate6();
			}
		}
	}
	

	private static void calculate1() {
		for (int i = 0; i < map.length / 2; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int tmp = map[i][j];
				map[i][j] = map[map.length - 1 - i][j];
				map[map.length - 1 - i][j] = tmp;
			}
		}
	}
	
	private static void calculate2() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length / 2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][map[0].length - 1 - j];
				map[i][map[0].length - 1 - j] = tmp;
			}
		}
	}
	
	private static void calculate3() {
		int[][] newMap = new int[map[0].length][map.length];
		
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				newMap[i][j] = map[map.length - j - 1][i];
			}
		}
		
		map = newMap;
	}
	
	private static void calculate4() {
		int[][] newMap = new int[map[0].length][map.length];
		
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				newMap[i][j] = map[j][map[0].length - i - 1];
			}
		}
		
		map = newMap;
	}
	
	private static void calculate5() {
		int[] dr = {0, map.length / 2, 0, map.length / 2 * -1};
		int[] dc = {map[0].length / 2, 0, map[0].length / 2 * -1, 0};
		
		for (int i = 0; i < map.length / 2; i++) {
			for (int j = 0; j < map[0].length / 2; j++) {
				int r = i;
				int c = j;
				int tmp = map[r][c];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					int swap = map[nr][nc];
					map[nr][nc] = tmp;
					tmp = swap;
					r = nr;
					c = nc;
				}
			}
		}
	}
	
	private static void calculate6() {
		int[] dr = {map.length / 2, 0, map.length / 2 * -1, 0};
		int[] dc = {0, map[0].length / 2, 0, map[0].length / 2 * -1};
		
		for (int i = 0; i < map.length / 2; i++) {
			for (int j = 0; j < map[0].length / 2; j++) {
				int r = i;
				int c = j;
				int tmp = map[r][c];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					int swap = map[nr][nc];
					map[nr][nc] = tmp;
					tmp = swap;
					r = nr;
					c = nc;
				}
			}
		}
	}
}