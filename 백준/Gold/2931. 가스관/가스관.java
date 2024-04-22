import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, dirIdx;
	static Point emptyPos;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] dirs;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Point m = new Point(-1, -1);
		Point z = new Point(-1, -1);
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		dirs = new int[2];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'M') {
					m = new Point(i, j);
				} else if (map[i][j] == 'Z') {
					z = new Point(i, j);
				}
			}
		}
		
		int mDir = -1;
		int zDir = -1;
		for (int i = 0; i < 4; i++) {
			if (isIn(m.x + dr[i], m.y + dc[i]) && map[m.x + dr[i]][m.y + dc[i]] != '.' && map[m.x + dr[i]][m.y + dc[i]] != 'Z') {
				mDir = i;
			}
			if (isIn(z.x + dr[i], z.y + dc[i]) && map[z.x + dr[i]][z.y + dc[i]] != '.' && map[z.x + dr[i]][z.y + dc[i]] != 'M') {
				zDir = i;
			}
		}
		
		move(m.x, m.y, mDir);
		move(z.x, z.y, zDir);
		char block = findBlock();
		
		System.out.println((emptyPos.x + 1) + " " + (emptyPos.y + 1) + " " + block);
	}
	
	private static void move(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		boolean check = false;
		
		if (map[nr][nc] == '.') {
			dirs[dirIdx++] = dir;
			emptyPos = new Point(nr, nc);
			return;
		}
		char block = map[nr][nc];
		if (block == '|' || block == '-' || block == '+') {
			move(nr, nc, dir);
		} else if (block == '1') {
			if (dir == 3) {
				move(nr, nc, 2);
			} else {
				move(nr, nc, 1);
			}
		} else if (block == '2') {
			if (dir == 3) {
				move(nr, nc, 0);
			} else {
				move(nr, nc, 1);
			}
		} else if (block == '3') {
			if (dir == 1) {
				move(nr, nc, 0);
			} else {
				move(nr, nc, 3);
			}
		} else {
			if (dir == 1) {
				move(nr, nc, 2);
			} else {
				move(nr, nc, 3);
			}
		}
	}
	
	private static char findBlock() {
		Arrays.sort(dirs);
		int dir1 = dirs[0];
		int dir2 = dirs[1];
		int r = emptyPos.x;
		int c = emptyPos.y;
		
		if (dir1 == 0 && dir2 == 2) {
			return '|';
		} else if (dir1 == 1 && dir2 == 3) {
			return '-';
		} else if (dir1 == 0 && dir2 == 1) {
			if (isIn(r - 1, c) && isIn(r, c + 1)
					&& (map[r - 1][c] ==  '|' || map[r - 1][c] ==  '+' || map[r - 1][c] ==  '1' || map[r - 1][c] ==  '4')
					&& (map[r][c + 1] == '-' || map[r][c + 1] == '+' || map[r][c + 1] == '3' || map[r][c + 1] == '4')) {
				return '+';
			}
			return '4';
		} else if (dir1 == 0 && dir2 == 3) {
			if (isIn(r - 1, c) && isIn(r, c - 1)
					&& (map[r - 1][c] ==  '|' || map[r - 1][c] ==  '+' || map[r - 1][c] ==  '1' || map[r - 1][c] ==  '4')
					&& (map[r][c - 1] == '-' || map[r][c - 1] == '+' || map[r][c - 1] == '1' || map[r][c - 1] == '2')) {
				return '+';
			}
			return '1';
		} else if (dir1 == 1 && dir2 == 2) {
			if (isIn(r + 1, c) && isIn(r, c + 1)
					&& (map[r + 1][c] ==  '|' || map[r + 1][c] ==  '+' || map[r + 1][c] ==  '2' || map[r + 1][c] ==  '3')
					&& (map[r][c + 1] == '-' || map[r][c + 1] == '+' || map[r][c + 1] == '3' || map[r][c + 1] == '4')) {
				return '+';
			}
			return '3';
		} else {
			if (isIn(r + 1, c) && isIn(r, c - 1)
					&& (map[r + 1][c] ==  '|' || map[r + 1][c] ==  '+' || map[r + 1][c] ==  '2' || map[r + 1][c] ==  '3')
					&& (map[r][c - 1] == '-' || map[r][c - 1] == '+' || map[r][c - 1] == '1' || map[r][c - 1] == '2')) {
				return '+';
			}
			return '2';
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
