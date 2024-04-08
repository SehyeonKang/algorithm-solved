import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		
		int sum = pq.poll();
		if (sum > 1) {
			System.out.println(1);
			return;
		}
		
		for (int i = 1; i < N; i++) {
			int num = pq.poll();
			if (sum + 1 < num) {
				answer = sum + 1;
				System.out.println(answer);
				return;
			}
			sum += num;
		}
		
		System.out.println(sum + 1);
	}
}
