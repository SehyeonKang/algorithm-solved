import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int M;
	static int[][] map;
	static Pos[] selected;
	static List<Pos> stores = new ArrayList<>();
	static List<Pos> houses = new ArrayList<>();
	
	static class Pos{
		int r;
		int c;
		
		Pos(int r, int c) {
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
		selected = new Pos[M];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					houses.add(new Pos(i, j));
				}
				if (map[i][j] == 2) {
					stores.add(new Pos(i, j));
				}
			}
		}
		
		recur(0, 0);
		
		System.out.println(answer);
	}
	
	static void recur(int cnt, int start) {
		if (cnt == M) {
			int sum = 0;
			
			for (Pos house : houses) {
				int min = Integer.MAX_VALUE;
				
				for (Pos store : selected) {
					int distance = Math.abs(store.r - house.r) + Math.abs(store.c - house.c);
					min = Math.min(min, distance);
				}
				sum += min;
			}
			
			answer = Math.min(answer, sum);
			return;
		}
		
		for (int i = start; i < stores.size(); i++) {
			selected[cnt] = stores.get(i);
			recur(cnt + 1, i + 1);
		}
	}
}