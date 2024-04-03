import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int M = Integer.parseInt(br.readLine());
        	
        	ArrayList<Integer>[] edges = new ArrayList[N + 1];
        	int[] knowledge = new int[N + 1];
        	for (int i = 0; i <= N; i++) {
        		edges[i] = new ArrayList<>();
        	}
        	for (int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken());
        		int e = Integer.parseInt(st.nextToken());
        		edges[s].add(e);
        	}
        	
        	for (int i = 1; i <= N; i++) {
        		Queue<Integer> q = new ArrayDeque<>();
        		boolean[] visited = new boolean[N + 1];
        		int knowCnt = 0;
        		q.offer(i);
        		visited[i] = true;
        		
        		while (!q.isEmpty()) {
        			int no = q.poll();
        			for (int e : edges[no]) {
        				if (!visited[e]) {
        					visited[e] = true;
        					q.offer(e);
        					knowledge[e]++;
        					knowCnt++;
        				}
        			}
        		}
        		
        		knowledge[i] += knowCnt;
        	}
        	
        	int answer = 0;
        	for (int cnt : knowledge) {
        		if (cnt == N - 1) {
        			answer++;
        		}
        	}
        	sb.append("#" + tc + " " + answer + "\n");
        }
   
        System.out.println(sb);
    }
}
