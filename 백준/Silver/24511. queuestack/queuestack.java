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
		Stack<Integer> stack = new Stack<>();
		boolean[] check = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(st.nextToken()) == 0) {
				check[i] = true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (check[i]) {
				stack.push(n);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		int cnt = M - stack.size();
		for (int i = 0; i < M; i++) {
			if (stack.isEmpty()) {
				break;
			}
			
			sb.append(stack.pop() + " ");
		}
		
		int[] nums = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < cnt; i++) {
			sb.append(nums[i] + " ");
		}
		
		System.out.println(sb);
	}
}