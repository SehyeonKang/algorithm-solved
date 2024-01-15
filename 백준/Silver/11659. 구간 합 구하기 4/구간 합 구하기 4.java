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
		int[] arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		int partSum = 0;
		for (int i = 1; i <= N; i++) {
			partSum += Integer.parseInt(st.nextToken());
			arr[i] = partSum;
		}
		
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int sum = arr[j] - arr[i - 1];
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
	}
}