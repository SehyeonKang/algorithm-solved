import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String answer = "";
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		while (true) {
			if (N < B) {
				stack.push(N);
				break;
			}
			
			stack.push(N % B);
			N /= B;
		}
		
		while (!stack.isEmpty()) {
			int num = stack.pop();
			
			if (num < 10) {
				answer += num;
			} else {
				answer += (char) (num + 55);
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}