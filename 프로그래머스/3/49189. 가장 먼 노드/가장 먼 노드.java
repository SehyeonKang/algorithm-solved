import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            answer = qSize;
            for (int i = 0; i < qSize; i++) {
                int cur = queue.poll();
                
                for (int num : adjList.get(cur)) {
                    if (!visited[num]) {
                        queue.offer(num);
                        visited[num] = true;
                    }
                }
            }
        }
        
        return answer;
    }
}