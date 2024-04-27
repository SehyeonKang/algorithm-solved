import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int option = Integer.parseInt(st.nextToken());
			
			if (option == 1) {
				int x = Integer.parseInt(st.nextToken());
				deque.addFirst(x);
			} else if (option == 2) {
				int x = Integer.parseInt(st.nextToken());
				deque.add(x);
			} else if (option == 3) {
				if (deque.isEmpty()) {
					sb.append(-1 + "\n");
					continue;
				}

				sb.append(deque.remove() + "\n");
			} else if (option == 4) {
				if (deque.isEmpty()) {
					sb.append(-1 + "\n");
					continue;
				}

				sb.append(deque.removeLast() + "\n");
			} else if (option == 5) {
				sb.append(deque.size() + "\n");
			} else if (option == 6) {
				if (deque.isEmpty()) {
					sb.append(1 + "\n");
				} else {
					sb.append(0 + "\n");
				}
			} else if (option == 7) {
				if (deque.isEmpty()) {
					
					sb.append(-1 + "\n");
					continue;
				}

				sb.append(deque.peekFirst() + "\n");
			} else {
				if (deque.isEmpty()) {
					sb.append(-1 + "\n");
					continue;
				}

				sb.append(deque.peekLast() + "\n");
			}
		}
		System.out.println(sb);
	}
}