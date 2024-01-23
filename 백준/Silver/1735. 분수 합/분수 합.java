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
		long a1 = Long.parseLong(st.nextToken());
		long b1 = Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());
		long a2 = Long.parseLong(st.nextToken());
		long b2 = Long.parseLong(st.nextToken());
		
		long num = 0;
		for (long i = 1; i <= Math.min(b1, b2); i++) {
			if (b1 % i == 0 && b2 % i == 0) {
				num = i;
			}
		}
		
		long a3 = a1 * b2 / num + a2 * b1 / num;
		long b3 = b1 * b2 / num;
		
		num = 0;
		for (long i = 1; i <= Math.min(a3, b3); i++) {
			if (a3 % i == 0 && b3 % i == 0) {
				num = i;
			}
		}
		
		a3 /= num;
		b3 /= num;
		
		System.out.println(a3 + " " + b3);
	}
}