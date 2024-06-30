import java.util.*;

class Solution {
    
    static class Route implements Comparable<Route> {
        int start, end;
        
        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Route o) {
            return this.end - o.end;
        }
    }
    
    public int solution(int[][] routes) {
        int answer = 1;
        
        PriorityQueue<Route> routePQ = new PriorityQueue<>();
        for (int[] route : routes) {
            routePQ.offer(new Route(route[0], route[1]));
        }
        
        int curEnd = routePQ.poll().end;
        while (!routePQ.isEmpty()) {
            Route route = routePQ.poll();
            int start = route.start;
            int end = route.end;
            
            if (start > curEnd) {
                answer++;
                curEnd = end;
            }
        }
        
        return answer;
    }
}