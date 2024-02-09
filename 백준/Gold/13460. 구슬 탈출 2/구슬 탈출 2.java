import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer = 11;
	static int N, M;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static char[][] map;
	static Point hole;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		Point red = null;
		Point blue = null;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					red = new Point(i, j);
				} else if (map[i][j] == 'B') {
					blue = new Point(i, j);
				} else if (map[i][j] == 'O') {
					hole = new Point(i, j);
				}
			}
		}
		
		recur(red, blue, 1);
		
		if (answer == 11) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void recur(Point red, Point blue, int count) {
		if (count == 11) {
			return;
		}
		
		// 4방향 탐색
		int rRed = red.x;
		int cRed = red.y;
		int rBlue = blue.x;
		int cBlue = blue.y;
		
		for (int dir = 0; dir < 4; dir++) {
			int nrRed = rRed + dr[dir];
			int ncRed = cRed + dc[dir];
			int nrBlue = rBlue + dr[dir];
			int ncBlue = cBlue + dc[dir];
			boolean redOutCheck = false;
			boolean blueOutCheck = false;
			boolean canMoveRed = true;
			boolean canMoveBlue = true;
			
			while (true) {
				if ((!canMoveRed && !canMoveBlue) || (redOutCheck && !canMoveBlue) || (blueOutCheck && !canMoveRed)) {
					break;
				}
				
				if (canMoveRed && (map[nrRed][ncRed] == '#' || (map[nrRed][ncRed] == 'B' && map[nrRed + dr[dir]][ncRed + dc[dir]] == '#'))) {
					canMoveRed = false;
					nrRed -= dr[dir];
					ncRed -= dc[dir];
					continue;
				}
				
				if (canMoveBlue && (map[nrBlue][ncBlue] == '#' || (map[nrBlue][ncBlue] == 'R' && map[nrBlue + dr[dir]][ncBlue + dc[dir]] == '#'))) {
					canMoveBlue = false;
					nrBlue -= dr[dir];
					ncBlue -= dc[dir];
					continue;
				}
				
				if (canMoveRed) {
					if (map[nrRed][ncRed] == '.') {
						map[nrRed][ncRed] = 'R';
						map[nrRed - dr[dir]][ncRed - dc[dir]] = '.';
						nrRed += dr[dir];
						ncRed += dc[dir];
					} else if (map[nrRed][ncRed] == 'O') {
						map[nrRed - dr[dir]][ncRed - dc[dir]] = '.';
						canMoveRed = false;
						redOutCheck = true;
					}
				}
				if (canMoveBlue) {
					if (map[nrBlue][ncBlue] == '.') {
						map[nrBlue][ncBlue] = 'B';
						map[nrBlue - dr[dir]][ncBlue - dc[dir]] = '.';
						nrBlue += dr[dir];
						ncBlue += dc[dir];
					} else if (map[nrBlue][ncBlue] == 'O') {
						map[nrBlue - dr[dir]][ncBlue - dc[dir]] = '.';
						canMoveBlue = false;
						blueOutCheck = true;
					}
				}
			}
			
			if (!redOutCheck && !blueOutCheck) {
				recur(new Point(nrRed, ncRed), new Point(nrBlue, ncBlue), count + 1);
			} else if (redOutCheck && !blueOutCheck) {
				answer = Math.min(answer, count);
			}
			
			if (redOutCheck) {
				map[nrRed - dr[dir]][ncRed - dc[dir]] = '.';
			} else {
				map[nrRed][ncRed] = '.';
			}
			if (blueOutCheck) {
				map[nrBlue - dr[dir]][ncBlue - dc[dir]] = '.';
			} else {
				map[nrBlue][ncBlue] = '.';
			}
			map[rRed][cRed] = 'R';
			map[rBlue][cBlue] = 'B';
		}
	}
}