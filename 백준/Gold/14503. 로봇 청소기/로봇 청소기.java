import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = solution(map, r, c, d);
		System.out.println(answer);
	}
	
	static int solution(int[][] map, int r, int c, int d) {
		int cleanCount = 0;
		
		while (true) {
			// 현재 칸 청소
			if (map[r][c] == 0) {
				map[r][c] = 2;
				cleanCount++;
			}
			
			boolean cleanCheck = true;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (map[nr][nc] == 0) {
					cleanCheck = false;
					break;
				}
			}
			
			// 주변 4칸중 청소되지 않은 빈 칸이 없는 경우
			if (cleanCheck) {
				int reverseDir = d + 2;
				if (reverseDir > 3) {
					reverseDir -= 4;
				}
				
				int nr = r + dr[reverseDir];
				int nc = c + dc[reverseDir];
				
				if (map[nr][nc] != 1) {
					r = nr;
					c = nc;
					continue;
				} else {
					// 뒤쪽 칸이 벽이라 후진할 수 없으면 작동 멈춤
					return cleanCount;
				}
			} else { // 주변 4칸중 청소되지 않은 빈 칸이 있는 경우
				d -= 1;
				if (d == -1) {
					d = 3;
				}
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (map[nr][nc] == 0) {
					r = nr;
					c = nc;
				}
			}
		}
	}
}