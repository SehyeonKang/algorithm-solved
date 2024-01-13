import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] cake = new int[L + 1];
		int max = 0;
		int maxAnswer = 0;
		
		for (int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			if (max < K - P) {
				max = K - P;
				maxAnswer = i;
			}
			
			for (int j = P; j <= K; j++) {
				if (cake[j] == 0) {
					cake[j] = i;
				}
			}
		}
		
		int[] audiences = new int[N + 1];
		for (int i = 1; i <= L; i++) {
			audiences[cake[i]]++;
		}
		
		int realMax= 0;
		int realMaxAnswer = 0;
		for (int i = 1; i <= N; i++) {
			if (realMax < audiences[i]) {
				realMax = audiences[i];
				realMaxAnswer = i;
			}
		}
		
		System.out.println(maxAnswer + "\n" + realMaxAnswer);
	}
}