import java.util.*;

class Solution {
    
    static class NumberInfo implements Comparable<NumberInfo> {
        int idx;
        int num;
        
        public NumberInfo(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
        
        @Override
        public int compareTo(NumberInfo o) {
            return this.num - o.num;
        }
    }
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        PriorityQueue<NumberInfo> pq = new PriorityQueue<>();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            pq.offer(new NumberInfo(i, numbers[i]));
            
            while (!pq.isEmpty()) {
                NumberInfo ni = pq.peek();
                if (ni.num >= number)
                    break;
                
                answer[ni.idx] = number;
                pq.poll();
            }
        }
        
        for (int i = 0; i < numbers.length; i++) {
            if (answer[i] == 0) {
                answer[i] = -1;
            }
        }
        
        return answer;
    }
}