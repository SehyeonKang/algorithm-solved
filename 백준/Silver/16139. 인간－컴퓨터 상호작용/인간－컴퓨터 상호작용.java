import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String S = br.readLine();
		int[] counts = new int[26];
		int[][] sumArr = new int[S.length()][26];
		for (int i = 0; i < S.length(); i++) {
			if (i > 0) {
				for (int j = 0; j < 26; j++) {
					sumArr[i][j] = sumArr[i - 1][j];
				}
			}
			
			int index = S.charAt(i) - 'a';
			counts[index]++;
			sumArr[i][index] = counts[index];
		}
		
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int index = st.nextToken().charAt(0) - 'a';
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			if (l == 0) {
				answer = sumArr[r][index];
			} else {
				answer = sumArr[r][index] - sumArr[l - 1][index];
			}
			
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
	}
}