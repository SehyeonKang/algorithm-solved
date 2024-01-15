import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		int[][] partSumMap = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				int partSum = 0;
				
				if (j > 1) {
					partSum = partSumMap[i][j - 1];
				}
				
				for (int k = 1; k <= i; k++) {
					partSum += map[k][j];
				}
				
				partSumMap[i][j] = partSum;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = partSumMap[x2][y2] - partSumMap[x1 - 1][y2] - partSumMap[x2][y1 - 1] + partSumMap[x1 - 1][y1 - 1];
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
	}
}