import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean[][] map = new boolean[101][101];
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					if (!map[j][k]) {
						map[j][k] = true;
					}
				}
			}
		}
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if (map[i][j]) {
					for (int k = 0; k < 4; k++) {
						if (i + dx[k] < 0 || j + dy[k] < 0 || i + dx[k] > 100 || i + dy[k] > 100) {
							answer++;
							continue;
						}
						
						if (!map[i + dx[k]][j + dy[k]]) {
							answer++;
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}