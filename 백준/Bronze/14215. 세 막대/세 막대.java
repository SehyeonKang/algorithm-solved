import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] lenghts = new int[3];
		for (int i = 0; i < 3; i++) {
			lenghts[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lenghts);
		int a = lenghts[2];
		int b = lenghts[1];
		int c = lenghts[0];
		
		if (a < b + c) {
			sb.append(a + b + c);
		} else {
			while (a >= b + c) {
				a--;
			}
			sb.append(a + b + c);
		}
		
		System.out.println(sb);
	}
}