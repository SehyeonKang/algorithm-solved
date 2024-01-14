import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long n = Long.parseLong(br.readLine());
		long answer = n * 4;
		
		sb.append(answer);
		System.out.println(sb);
	}
}