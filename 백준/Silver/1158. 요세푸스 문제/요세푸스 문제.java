import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		int count = 1;
		List<Integer> answer = new ArrayList<>();
		while (!q.isEmpty()) {
			if (count == K) {
				answer.add(q.poll());
				count = 1;
				continue;
			}
			
			q.offer(q.poll());
			count++;
		}
		
		sb.append("<" + answer.get(0));
		for (int i = 1; i < N; i++) {
			sb.append(", " + answer.get(i)); 
		}
		sb.append(">");
		System.out.println(sb);
	}
}