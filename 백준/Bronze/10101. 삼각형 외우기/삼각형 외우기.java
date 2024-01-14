import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		if (a == b && b == c && c == 60) {
			sb.append("Equilateral");
		} else if (a + b + c == 180) {
			if (a != b && b != c && a != c) {
				sb.append("Scalene");
			} else {
				sb.append("Isosceles");
			}
		} else {
			sb.append("Error");
		}
		
		System.out.println(sb);
	}
}