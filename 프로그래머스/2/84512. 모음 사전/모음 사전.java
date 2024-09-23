import java.util.*;
import java.io.*;

class Solution {
    
    char[] sArr = {'A', 'E', 'I', 'O', 'U'};
    PriorityQueue<String> pq = new PriorityQueue<>();
    
    public int solution(String word) {
        int answer = 1;
        
        for (int i = 1; i <= 5; i++) {
            recur(i, 0, "");
        }
        
        while (!pq.isEmpty()) {
            if (pq.poll().equals(word))
                break;
            
            answer++;
        }

        return answer;
    }
    
    public void recur(int cnt, int cur, String s) {
        if (cur == cnt) {
            pq.offer(s);
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            recur(cnt, cur + 1, s + sArr[i]);
        }
    }
}