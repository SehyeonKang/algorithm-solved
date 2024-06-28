import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if (n > s) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        
        int avg = s / n;
        int mod = s % n;
        for (int i = 0; i < n; i++) {
            if (i >= n - mod) {
                answer[i] = avg + 1;
            } else {
                answer[i] = avg;
            }
        }
        
        return answer;
    }
}