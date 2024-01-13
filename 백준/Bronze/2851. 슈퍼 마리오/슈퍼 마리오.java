import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int max = 0;
		for (int i = 0; i < 10; i++) {
			int n = Integer.parseInt(br.readLine());
			sum += n;
			
			if (sum >= 100) {
				if (100 - max >= sum - 100) {
					max = sum;
				}
				break;
			}
			max = sum;
		}
		
		System.out.println(max);
	}
}