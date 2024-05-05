import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	static class Island {
		int x, y;

		public Island(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class SegmentTree {
		int[] tree;
		int treeSize;
		
		public SegmentTree(int queueSize) {
			int h = (int) Math.ceil(Math.log(queueSize) / Math.log(2));
			treeSize = (int) Math.pow(2, h + 1);
			tree = new int[treeSize];
		}
		
		public void update(int node, int start, int end, int idx) {
			if (idx > end || idx < start) {
				return;
			}
			
			tree[node]++;
			if (start == idx && end == idx) {
				return;
			}
			int mid = (start + end) / 2;
			update(node * 2, start, mid, idx);
			update(node * 2 + 1, mid + 1, end, idx);
		}
		
		public int find(int node, int start, int end, int left, int right) {
			if (left > end || right < start) {
				return 0;
			}
			
			if (left <= start && end <= right) {
				return tree[node];
			}
			
			int mid = (start + end) / 2;
			return find(node * 2, start, mid, left, right) 
					+ find(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			PriorityQueue<Island> islands = new PriorityQueue<>((i1, i2) -> i2.y - i1.y);
			PriorityQueue<Island> islands2 = new PriorityQueue<>((i1, i2) -> i1.x == i2.x ? i1.y - i2.y : i1.x - i2.x);
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				islands.offer(new Island(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// y값이 클수록 높은 우선순위로 좌표 압축
			int yIdx = 1;
			int preY = islands.peek().y;
			for (int i = 0; i < N; i++) {
				Island island = islands.poll();
				if (preY == island.y) {
					island.y = yIdx;
				} else {
					preY = island.y;
					island.y = ++yIdx;
				}
				islands2.offer(island);
			}
			
			SegmentTree tree = new SegmentTree(N);
			long answer = 0;
			while (!islands2.isEmpty()) {
				int y = islands2.poll().y;
				answer += tree.find(1, 1, N, 1, y);
				tree.update(1, 1, N, y);
			}
			
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
	}
	
}
