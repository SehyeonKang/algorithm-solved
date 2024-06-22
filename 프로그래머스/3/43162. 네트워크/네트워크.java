import java.util.*;

class Solution {
    
    static int[][] mat;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        mat = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = computers[i][j];
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, n);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void bfs(int start, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int i = 0; i < n; i++) {
                if (!visited[i] && mat[cur][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}