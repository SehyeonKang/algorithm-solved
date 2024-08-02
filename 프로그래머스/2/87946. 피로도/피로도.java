import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[] numbers;
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        visited = new boolean[dungeons.length];
        numbers = new int[dungeons.length];
        
        recur(0, k, dungeons);
        
        return answer;
    }
    
    private void recur(int cnt, int k, int[][] dungeons) {
        if (cnt == dungeons.length) {
            calculate(k, dungeons);
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers[cnt] = i; 
                recur(cnt + 1, k, dungeons);
                visited[i] = false;
            }
        }
    }
    
    private void calculate(int k, int[][] dungeons) {
        int count = 0;
        for (int i = 0; i < dungeons.length; i++) {
            int min = dungeons[numbers[i]][0];
            int consume = dungeons[numbers[i]][1];
            
            if (k >= min) {
                k -= consume;
                count++;
            } else {
                break;
            }
        }
        
        answer = Math.max(answer, count);
    }
}