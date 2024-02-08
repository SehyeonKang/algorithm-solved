import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int[] populations;
	static boolean[] visited;
	static boolean[] areas;
	static List<List<Integer>> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		populations = new int[N];
		areas = new boolean[N];
		map = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<>());
			populations[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int connectedCount = Integer.parseInt(st.nextToken());
			for (int j = 0; j < connectedCount; j++) {
				map.get(i).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		recur(0, 0);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	// 부분 집합을 통해 선거구 나누기
	private static void recur(int count, int areaCount) {
		// 두 선거구를 다 나눈 경우
		if (count == N) {
			// 선거구가 하나로만 나눠진 경우
			if (areaCount == N || areaCount == 0) {
				return;
			}
			
			int area1 = 0;
			while (true) {
				if (areas[area1]) {
					break;
				}
				area1++;
			}
			
			int area2 = 0;
			while (true) {
				if (!areas[area2]) {
					break;
				}
				area2++;
			}
			
			// 각 선거구마다 연결된 구역 개수 탐색
			int result1 = bfs(area1);
			int result2 = bfs(area2);
			
			// 탐색 결과, 두 선거구로 나눌 수 있는 경우 인구수 계산
			if (result1 == areaCount && result2 == N - areaCount) {
				int sum1 = 0;
				int sum2 = 0;
				
				for (int i = 0; i < N; i++) {
					if (areas[i]) {
						sum1 += populations[i];
					} else {
						sum2 += populations[i];
					}
				}
				
				answer = Math.min(answer, Math.abs(sum1 - sum2));
			}
			
			return;
		}
		
		areas[count] = true;
		recur(count + 1, areaCount + 1);
		areas[count] = false;
		recur(count + 1, areaCount);
	}
	
	private static int bfs(int startNode) {
		int count = 0;
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N];
		q.offer(startNode);
		visited[startNode] = true;
		count++;
		
		while (!q.isEmpty()) {
			int node = q.poll();
			
			for (int i = 0; i < map.get(node).size(); i++) {
				int newNode = map.get(node).get(i);
				
				if (areas[newNode] == areas[startNode] && !visited[newNode]) {
					visited[newNode] = true;
					q.offer(newNode);
					count++;
				}
			}
		}
		
		return count;
	}
}