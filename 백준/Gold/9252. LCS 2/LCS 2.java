import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		char[] arr1 = br.readLine().toCharArray();
		char[] arr2 = br.readLine().toCharArray();
		int[][] dp = new int[arr1.length + 1][arr2.length + 1];
		
		for (int i = 1; i <= arr1.length; i++) {
			for (int j = 1; j <= arr2.length; j++) {
				if (arr1[i - 1] == arr2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		// 최장공통수열의 끝부분부터 탐색
		int r = arr1.length;
		int c = arr2.length;
		while (r > 0 && c > 0) {
			if(arr1[r - 1] == arr2[c - 1]) {
				sb.append(arr1[r - 1]);
				r--;
				c--;
			} else if (dp[r - 1][c] > dp[r][c - 1]) {
				r--;
			} else {
				c--;
			}
		}
		sb.reverse();
		
		System.out.println(dp[arr1.length][arr2.length] + "\n" + sb.toString());
	}
	
}
