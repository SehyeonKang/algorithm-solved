import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		long main = Long.parseLong(st.nextToken());
		long sub = Long.parseLong(st.nextToken());
		
		long sum = 0;
		for (int i = 0; i < N; i++) {
			long num = arr[i] - main;
			if (num < 0) {
				num = 0;
			}
			
			if (num % sub == 0) {
				sum += num / sub + 1;
			} else {
				sum += num / sub + 2;
			}
		}
		
		System.out.println(sum);
	}
	
}