import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		ArrayDeque<Character> stack = new ArrayDeque<>();
		Map<Character, Integer> priorityMap = new HashMap<>();
		priorityMap.put('(', 0);
		priorityMap.put('+', 1);
		priorityMap.put('-', 1);
		priorityMap.put('*', 2);
		priorityMap.put('/', 2);
		
		String s = br.readLine();
		for (char c : s.toCharArray()) {
			if (c >= 65) {
				sb.append(c);
				continue;
			}
			
			if (c == '(') {
				stack.push(c);
				continue;
			}
			
			if (c == ')') {
				while (true) {
					char top = stack.pop();
					if (top == '(') {
						break;
					}
					sb.append(top);
				}
				continue;
			}
			
			if (stack.isEmpty()) {
				stack.push(c);
				continue;
			}
			
			if (priorityMap.get(stack.peek()) < priorityMap.get(c)) {
				stack.push(c);
				continue;
			}
			
			while (!stack.isEmpty() && priorityMap.get(stack.peek()) >= priorityMap.get(c)) {
				sb.append(stack.pop());
			}
			stack.push(c);
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
}
