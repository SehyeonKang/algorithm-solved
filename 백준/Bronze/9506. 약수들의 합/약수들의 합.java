import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == -1) {
				break;
			}
			
			int sum = 0;
			List<Integer> numbers = new ArrayList<>();
			for (int i = 1; i < n; i++) {
				if (n % i == 0) {
					sum += i;
					numbers.add(i);
				}
			}
			
			if (n == sum) {
				sb.append(n + " = ");
				for (int i = 0; i < numbers.size(); i++) {
					if (i > 0) {
						sb.append(" + ");
					}
					sb.append(numbers.get(i));
				}
				sb.append("\n");
			} else {
				sb.append(n + " is NOT perfect.\n");
			}
		}
		
		System.out.println(sb);
	}
}