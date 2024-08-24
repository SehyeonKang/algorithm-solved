import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[] counts = new int[n + 1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            list.get(a).add(b);
        }
        
        for (int i = 1; i <= n; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 1];
            queue.offer(i);
            visited[i] = true;
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int num : list.get(cur)) {
                    if (!visited[num]) {
                        queue.offer(num);
                        visited[num] = true;
                        counts[num]++;
                        counts[i]++;
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (counts[i] == n - 1)
                answer++;
        }
        
        return answer;
    }
}