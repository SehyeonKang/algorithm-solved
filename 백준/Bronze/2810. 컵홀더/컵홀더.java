import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		String seats = br.readLine();
		
		stack.push('*');
		for (int i = 0; i < seats.length(); i++) {
			char seat = seats.charAt(i);
			boolean coupleCheck = false;
			if (seat == 'L' && stack.peek() == 'L') {
				coupleCheck = true;
			}
			stack.push(seat);
			
			if (seat == 'S') {
				stack.push('*');
			} else if (seat == 'L') {
				if (coupleCheck) {
					stack.push('*');
				}
			}
		}
		
		while (!stack.isEmpty()) {
			char c = stack.pop();
			if (stack.isEmpty()) {
				break;
			}
			
			if (c == '*') {
				answer++;
				stack.pop();
			} else {
				if (stack.peek() == '*') {
					answer++;
					stack.pop();
				}
			}
		}
		
		System.out.println(answer);
	}
}