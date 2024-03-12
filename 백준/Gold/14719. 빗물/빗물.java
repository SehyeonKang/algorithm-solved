import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[H][W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			int blocks = Integer.parseInt(st.nextToken());
			for (int j = 0; j < blocks; j++) {
				map[j][i] = true;
			}
		}
		
		int answer = 0;
		for (int x = 1; x < W; x++) {
			int y = 0;
			while (isIn(x, y) && map[y][x]) {
				int nx = x - 1;
				
				while (isIn(nx, y) && !map[y][nx]) {
					nx--;
					if (nx < 0) {
						break;
					}
				}
				if (nx >= 0) {
					answer += x - nx - 1;
				}
				y++;
			}
		}
		
		System.out.println(answer);
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < W && y >= 0 && y < H;
	}
}