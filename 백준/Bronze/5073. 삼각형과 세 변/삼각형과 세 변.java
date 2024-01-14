import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int[] lengths = new int[3];
			for (int i = 0; i < 3; i++) {
				lengths[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(lengths);
			int a = lengths[2];
			int b = lengths[1];
			int c = lengths[0];
			
			if (a == 0 && b == 0 && c == 0) {
				break;
			}
			
			if (a == b && b == c) {
				sb.append("Equilateral");
			} else if (a < b + c) {
				if (a != b && b != c && a != c) {
					sb.append("Scalene");
				} else {
					sb.append("Isosceles");
				}
			} else {
				sb.append("Invalid");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}