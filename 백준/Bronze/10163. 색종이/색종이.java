import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[1001][1001];
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < x + width; j++) {
				for (int k = y; k < y + height; k++) {
					map[j][k] = i;
				}
			}
		}
		
		int[] answer = new int[N + 1];
		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 1001; j++) {
				if (map[i][j] > 0) {
					answer[map[i][j]]++;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}
	}
}