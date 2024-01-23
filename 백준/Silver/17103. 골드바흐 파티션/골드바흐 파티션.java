import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean[] primes = new boolean[1_000_001];
		for (int i = 2; i < primes.length; i++) {
			if (!primes[i]) {
				for (int j = 2; i * j < primes.length; j++) {
					if (!primes[i * j]) {
						primes[i * j] = true;
					}
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int answer = 0;
			
			for (int j = 2; j <= N / 2; j++) {
				if (!primes[j] && !primes[N - j]) {
					answer++;
				}
			}
			
			sb.append(answer + "\n");
		}
		
		
		
		System.out.println(sb);
	}
}