import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] gapArr = new int[N - 1];
		arr[0] = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			gapArr[i - 1] = arr[i] - arr[i - 1];
		}
		
		Arrays.sort(gapArr);
		
		int gcd = gapArr[0];
		for (int i = 1; i < N - 1; i++) {
			int tmp = 0;
			for (int j = 1; j <= gcd; j++) {
				if (gcd % j == 0 && gapArr[i] % j == 0) {
					tmp = j;
				}
			}
			gcd = Math.min(gcd, tmp);
		}
		
		int sum = 0;
		for (int gap : gapArr) {
			sum += gap / gcd - 1;
		}
		
		System.out.println(sum);
	}
}