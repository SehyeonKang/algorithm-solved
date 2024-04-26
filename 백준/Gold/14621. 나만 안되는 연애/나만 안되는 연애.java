import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer;
	static int[] parents;
	static char[] universities;
	static List<Edge> edges;
	
	static class Edge implements Comparable<Edge> {
		int u, v, d;

		public Edge(int u, int v, int d) {
			this.u = u;
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.d, o.d);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		universities = new char[N + 1];
		edges = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			universities[i] = st.nextToken().charAt(0);
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(edges);
		kruskal();
		System.out.println(answer);
	}
	
	private static void kruskal() {
		int cnt = 1;
		init();
		
		for (int i = 0; i < M; i++) {
			Edge edge = edges.get(i);
			// 1번 조건 체크
			if (universities[edge.u] == universities[edge.v]) {
				continue;
			}
			
			// 연결되어있는지 확인하고 경로 추가
			if (union(edge.u, edge.v)) {
				answer += edge.d;
				cnt++;
			}
			
			if (cnt == N) {
				return;
			}
		}
		
		answer = -1;
	}
	
	private static void init() {
		for (int i = 0; i <= N; i++) {
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
