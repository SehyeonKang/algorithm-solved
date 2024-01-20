import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 2; i < 8; i++) {
				dfs(0, i);
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int num, int numPlus) {
		int newNum = num * 10 + numPlus;
		
		if (isPrime(newNum)) {
			if (newNum > (int) Math.pow(10, N - 1)) {
				sb.append(newNum + "\n");
				return;
			}
			
			for (int i = 0; i < 10; i++) {
				dfs(newNum, i);
			}
		}
	}
	
	static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}