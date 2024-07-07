import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        int jobIdx = 0;
        int count = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        while (count < jobs.length) {
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= time) {
                pq.offer(jobs[jobIdx++]);
            }
            
            if (pq.isEmpty()) {
                time = jobs[jobIdx][0];
            } else {
                int[] job = pq.poll();
                answer += job[1] + time - job[0];
                time += job[1];
                count++;
            }
        }
        
        return answer / jobs.length;
    }
}