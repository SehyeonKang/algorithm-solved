import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int N, answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Node[][] adjList;
	static int[][] minDistance;
	static boolean[][] visited;
	
	static class Node {
		int r, c, weight;
		Node next;
		
		public Node(int r, int c, int weight, Node next) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = 0;

		while (true) {
			tc++;
			
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			adjList = new Node[N][N];
			minDistance = new int[N][N];
			visited = new boolean[N][N];
			int startMoney = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int weight = Integer.parseInt(st.nextToken());
					if (i == 0 && j == 0) {
						startMoney = weight;
					}
					for (int d = 0; d < 4; d++) {
						int r = i + dr[d];
						int c = j + dc[d];
						if (isIn(r, c)) {
							adjList[r][c] = new Node(i, j, weight, adjList[r][c]);
						}
					}
				}
				Arrays.fill(minDistance[i], Integer.MAX_VALUE);
			}
			
			minDistance[0][0] = startMoney;
			
			dijkstra();
			
			sb.append("Problem " + tc + ": " + answer + "\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dijkstra() {
		int min = 0;
		int currentR = 0;
		int currentC = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				min = INF;
				currentR = -1;
				currentC = -1;
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (!visited[r][c] && min > minDistance[r][c]) {
							min = minDistance[r][c];
							currentR = r;
							currentC = c;
						}
					}
				}
				
				visited[currentR][currentC] = true;
				
				if (currentR == N - 1 && currentC == N - 1) {
					break;
				}
				
				for (Node temp = adjList[currentR][currentC]; temp != null; temp = temp.next) {
					if (minDistance[temp.r][temp.c] > min + temp.weight) {
						minDistance[temp.r][temp.c] = min + temp.weight;
					}
				}
			}
		}
		
		answer = minDistance[N - 1][N - 1];
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}