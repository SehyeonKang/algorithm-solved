import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Quest {
		int deadline, count;

		public Quest(int deadline, int count) {
			super();
			this.deadline = deadline;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		PriorityQueue<Quest> pq = new PriorityQueue<>(new Comparator<Quest>() {
			@Override
			public int compare(Quest o1, Quest o2) {
				return o1.deadline != o2.deadline ? o1.deadline - o2.deadline : o2.count - o1.count;
			}
		});
		PriorityQueue<Quest> resultPq = new PriorityQueue<>(new Comparator<Quest>() {
			@Override
			public int compare(Quest o1, Quest o2) {
				return o1.count - o2.count;
			}
			
		});
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			pq.offer(new Quest(deadline, count));
		}
		
		while (!pq.isEmpty()) {
			Quest quest = pq.poll();
			int deadline = quest.deadline;
			int count = quest.count;
			
			if (resultPq.size() < deadline) {
				resultPq.offer(new Quest(deadline, count));
			} else {
				if (resultPq.peek().count < count) {
					resultPq.poll();
					resultPq.offer(new Quest(deadline, count));
				}
			}
		}
		
		int answer = 0;
		while (!resultPq.isEmpty()) {
			answer += resultPq.poll().count;
		}
		
		System.out.println(answer);
	}
}

