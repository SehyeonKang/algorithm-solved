import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < operations.length; i++) {
            String s = operations[i];
            StringTokenizer st = new StringTokenizer(s);
            if (st.nextToken().equals("I")) {
                int num = Integer.parseInt(st.nextToken());
                pq1.offer(num);
                pq2.offer(num);
            } else {
                if (pq1.isEmpty()) {
                    continue;
                }
                
                int num;
                if (st.nextToken().equals("1")) {
                    num = pq2.poll();
                    pq1.remove(num);
                } else {
                    num = pq1.poll();
                    pq2.remove(num);
                }
            }
        }
        
        if (!pq1.isEmpty()) {
            answer[0] = pq2.peek();
            answer[1] = pq1.peek();
        }
        
        return answer;
    }
}