import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] answers = new int[N];
		int index = N - 1;
		Stack<Integer> stack = new Stack<>();
		stack.push(arr[N - 1]);
		answers[index--] = -1;
		for (int i = N - 2; i >= 0; i--) {
			if (stack.peek() > arr[i]) {
				answers[index--] = stack.peek();
				stack.push(arr[i]);
			} else {
				while (!stack.isEmpty() && stack.peek() <= arr[i]) {
					stack.pop();
				}
				
				if (stack.isEmpty()) {
					answers[index--] = -1;
				} else {
					answers[index--] = stack.peek();
				}
				
				stack.push(arr[i]);
			}
		}
		
		for (int answer : answers) {
			sb.append(answer + " ");
		}
		
		System.out.println(sb);
	}
}