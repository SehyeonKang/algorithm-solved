import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : scoville) {
            pq.offer(n);
        }

        while (true) {
            int min = pq.poll();
            
            if (min >= K)
                break;
            
            if (pq.isEmpty()) {
                answer = -1;
                break;
            }
            
            int tmp = pq.poll();
            pq.offer(min + tmp * 2);
            answer++;
        }
        
        return answer;
    }
}