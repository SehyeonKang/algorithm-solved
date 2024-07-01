import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = w * 2 + 1;
        
        int start = 1;
        for (int i = 0; i < stations.length; i++) {
            int lt = stations[i] - w;
            int rt = stations[i] + w;
            
            if (start >= lt) {
                start = rt + 1;
                continue;
            }
            
            int cnt = (lt - start) / range;
            int mod = (lt - start) % range;
            
            answer += cnt;
            if (mod > 0)
                answer++;
            start = rt + 1;
        }
        
        if (start <= n) {
            int cnt = (n + 1 - start) / range;
            int mod = (n + 1 - start) % range;
            
            answer += cnt;
            if (mod > 0)
                answer++;
        }
        
        return answer;
    }
}