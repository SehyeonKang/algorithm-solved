import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] parents;
	static Queue<Edge> edges;
	static Queue<Edge> connectedEdges;
	static Queue<Edge> unConnectedEdges;
	
	static class Edge {
		int x, y;

		public Edge(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N];
		edges = new ArrayDeque<>();
		connectedEdges = new ArrayDeque<>();
		unConnectedEdges = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = i + 1; j < N; j++) {
				if (s.charAt(j) == 'Y') {
					edges.offer(new Edge(i, j));
				}
			}
		}
		
		boolean connectCheck = kruskal();

		// 도시가 1개인지 확인
		if (N == 1) {
			System.out.println(0);
			return;
		}
		
		// 도로가 모두 연결되어있는지 확인
		if (!connectCheck) {
			System.out.println(-1);
			return;
		}
		
		int edgeCount = N - 1;
		// 이미 연결되어서 연결하지 않은 도로들 연결
		while (!unConnectedEdges.isEmpty() && edgeCount < M) {
			connectedEdges.offer(unConnectedEdges.poll());
			edgeCount++;
		}
		
		// 연결 시도를 해보지 않은 도로들 연결
		while (!edges.isEmpty() && edgeCount < M) {
			connectedEdges.offer(edges.poll());
			edgeCount++;
		}
		
		if (edgeCount < M) {
			System.out.println(-1);
		} else {
			int[] answer = new int[N];
			while (!connectedEdges.isEmpty()) {
				Edge edge = connectedEdges.poll();
				answer[edge.x]++;
				answer[edge.y]++;
			}
			
			for (int i = 0; i < N; i++) {
				sb.append(answer[i] + " ");
			}
			System.out.println(sb);
		}
	}
	
	private static boolean kruskal() {
		int cnt = 1;
		init();
		
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			// 연결되어있는지 확인하고 경로 추가
			if (union(edge.x, edge.y)) {
				connectedEdges.offer(edge);
				cnt++;
			// 이미 연결되어있다면 연결시키지 않는 도로 큐에 추가
			} else {
				unConnectedEdges.offer(edge);
			}
			
			if (cnt == N) {
				return true;
			}
		}
		return false;
	}
	
	private static void init() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		
		parents[aRoot] = bRoot;
		return true;
	}
	
	private static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
}
