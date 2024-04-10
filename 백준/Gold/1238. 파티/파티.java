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
			super();
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
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
		int[] attendMinDistance = new int[N + 1];
		int[] minDistance = new int[N + 1];
		boolean[] visited;
		
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// 인접리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Node(to, weight));
		}
		
		// 각 집에서 파티 장소로 가는 최단 거리 구하기
		for (int start = 1; start <= N; start++) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			minDistance[start] = 0;
			visited = new boolean[N + 1];
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(start, 0));
			
			while (!pq.isEmpty()) {
				Node curNode = pq.poll();
				int cur = curNode.end;
				
				if (cur == X) {
					break;
				}
				
				if (!visited[cur]) {
					visited[cur] = true;
					
					for (Node node : adjList.get(cur)) {
						if (!visited[node.end] && minDistance[node.end] > minDistance[cur] + node.weight) {
							minDistance[node.end] = minDistance[cur] + node.weight;
							pq.offer(new Node(node.end, minDistance[node.end]));
						}
					}
				}
			}
			
			attendMinDistance[start] = minDistance[X];
		}
		
		// 파티 장소에서 각 집의 최단 거리 구하기
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		minDistance[X] = 0;
		visited = new boolean[N + 1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0));
		
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Node node : adjList.get(cur)) {
					if (!visited[node.end] && minDistance[node.end] > minDistance[cur] + node.weight) {
						minDistance[node.end] = minDistance[cur] + node.weight;
						pq.offer(new Node(node.end, minDistance[node.end]));
					}
				}
			}
		}
		
		int maxTime = 0;
		for (int i = 1; i <= N; i++) {
			maxTime = Math.max(maxTime, attendMinDistance[i] + minDistance[i]);
		}
		System.out.println(maxTime);
	}
}
