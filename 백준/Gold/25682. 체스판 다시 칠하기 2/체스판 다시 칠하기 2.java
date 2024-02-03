import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static char[][] map;
	static int[][] blackMap;
	static int[][] whiteMap;
	static int[][] blackSum;
	static int[][] whiteSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		blackMap = new int[N][M];
		whiteMap = new int[N][M];
		blackSum = new int[N][M];
		whiteSum = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		setMap();
		int answer = findMinimum();
		System.out.println(answer);
	}
	
	private static void setMap() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (M % 2 == 1) {
					if (count % 2 == 0 && map[i][j] != 'B') {
						blackMap[i][j] = 1;
					} else if (count % 2 == 1 && map[i][j] != 'W') {
						blackMap[i][j] = 1;
					}
					
					if (count % 2 == 0 && map[i][j] != 'W') {
						whiteMap[i][j] = 1;
					} else if (count % 2 == 1 && map[i][j] != 'B') {
						whiteMap[i][j] = 1;
					}
				} else {
					if ((count - i) % 2 == 0 && map[i][j] != 'B') {
						blackMap[i][j] = 1;
					} else if ((count - i) % 2 == 1 && map[i][j] != 'W') {
						blackMap[i][j] = 1;
					}
					
					if ((count - i) % 2 == 0 && map[i][j] != 'W') {
						whiteMap[i][j] = 1;
					} else if ((count - i) % 2 == 1 && map[i][j] != 'B') {
						whiteMap[i][j] = 1;
					}
				}
				
				count++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				blackSum[i][j] += blackMap[i][j];
				whiteSum[i][j] += whiteMap[i][j];
				if (isIn(i - 1, j)) {
					blackSum[i][j] += blackSum[i - 1][j];
					whiteSum[i][j] += whiteSum[i - 1][j];
				}
				
				if (isIn(i, j - 1)) {
					blackSum[i][j] += blackSum[i][j - 1];
					whiteSum[i][j] += whiteSum[i][j - 1];
				}
				
				if (isIn(i - 1, j - 1)) {
					blackSum[i][j] -= blackSum[i - 1][j - 1];
					whiteSum[i][j] -= whiteSum[i - 1][j - 1];
				}
			}
		}
	}
	
	private static int findMinimum() {
		int minSum = Integer.MAX_VALUE;
		
		for (int i = K - 1; i < N; i++) {
			for (int j = K - 1; j < M; j++) {
				int black = blackSum[i][j];
				int white = whiteSum[i][j];
				
				if (isIn(i - K, j)) {
					black -= blackSum[i - K][j];
					white -= whiteSum[i - K][j];
				}
				
				if (isIn(i, j - K)) {
					black -= blackSum[i][j - K];
					white -= whiteSum[i][j - K];
				}
				
				if (isIn(i - K, j - K)) {
					black += blackSum[i - K][j - K];
					white += whiteSum[i - K][j - K];
				}
				
				minSum = Math.min(minSum, black);
				minSum = Math.min(minSum, white);
			}
		}
		
		return minSum;
	}
	
	private static boolean isIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M) {
			return true;
		}
		return false;
	}
}