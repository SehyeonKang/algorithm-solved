import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int lt = 1;
		int rt = 2;
		int N = Integer.parseInt(br.readLine());
		
		int answer = 0;
		int sum = 1;
		while (rt <= N + 1) {
			if (sum == N) {
				answer++;
				sum += rt++;
			} else if (sum < N) {
				sum += rt++;
			} else {
				sum -= lt++;
			}
		}
		
		System.out.println(answer);
	}
}