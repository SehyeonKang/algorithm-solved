import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int end, weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
			int[] minDistance = new int[n + 1];
			boolean[] visited = new boolean[n + 1];
			
			for (int i = 0; i <= n; i++) {
				adjList.add(new ArrayList<>());
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				adjList.get(b).add(new Node(a, s));
			}
			
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			minDistance[c] = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(c, 0));
			int nodeCnt = 0;
			int totalTime = 0;
			
			while (!pq.isEmpty()) {
				Node curNode = pq.poll();
				int cur = curNode.end;
				
				if (visited[cur]) {
					continue;
				}
				visited[cur] = true;
				totalTime = minDistance[cur];
				nodeCnt++;
				
				for (Node node : adjList.get(cur)) {
					if (!visited[node.end] && minDistance[node.end] > minDistance[cur] + node.weight) {
						minDistance[node.end] = minDistance[cur] + node.weight;
						pq.offer(new Node(node.end, minDistance[node.end]));
					}
				}
			}
			
			sb.append(nodeCnt + " " + totalTime + "\n");
		}
		System.out.println(sb);
	}
}
