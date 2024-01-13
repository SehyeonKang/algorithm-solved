import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		int num = Integer.parseInt(br.readLine());
		for (int i = 1; i <= num; i++) {
			int j = i;
			if (j * j > num) {
				break;
			}
			
			while (i * j <= num) {
				answer++;
				j++;
			}
		}
		
		System.out.println(answer);
	}
}