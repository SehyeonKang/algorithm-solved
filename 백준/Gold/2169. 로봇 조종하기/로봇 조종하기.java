import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dp;
	static int[][] map;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 초기 세팅
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 첫번째 줄 dp 초기화
        dp[0][0] = map[0][0];
        for (int i = 1; i < M; i++) {
        	dp[0][i] = dp[0][i - 1] + map[0][i];
        }
        
        // 두번째~ N번째 줄 dp 초기화
        for (int i = 1; i < N; i++) {
        	int[] leftDp = new int[M];
        	int[] rightDp = new int[M];
        	
        	// 좌측 탐색
        	for (int j = 0; j < M; j++) {
        		if (j == 0) {
        			leftDp[j] = map[i][j] + dp[i - 1][j];
        		// 자신 바로 위칸의 최댓값과 좌측에서 올 때의 최댓값 비교
        		} else {
        			leftDp[j] = Math.max(leftDp[j - 1], dp[i - 1][j]) + map[i][j];
        		}
        	}
        	// 우측 탐색
        	for (int j = M - 1; j >= 0; j--) {
        		if (j == M - 1) {
        			rightDp[j] = map[i][j] + dp[i - 1][j];
        		// 자신 바로 위칸의 최댓값과 우측에서 올 때의 최댓값 비교
        		} else {
        			rightDp[j] = Math.max(rightDp[j + 1], dp[i - 1][j]) + map[i][j];
        		}
         	}
        	
        	// 좌,우측에서 올 때의 최댓값중 더 큰 값을 dp에 저장
        	for (int j = 0; j < M; j++) {
        		dp[i][j] = Math.max(rightDp[j], leftDp[j]);
        	}
        }
        
        System.out.println(dp[N - 1][M - 1]);
    }
}