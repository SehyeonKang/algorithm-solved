import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> queue = new ArrayDeque<>();
        
        for (String city : cities) {
            city = city.toLowerCase();
            if (queue.contains(city)) {
                answer++;
                queue.remove(city);
            } else {
                answer += 5;
                if (queue.size() == cacheSize)
                    queue.poll();
            }
            
            if (queue.size() < cacheSize)
                queue.offer(city);
        }
        
        return answer;
    }
}