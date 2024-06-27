import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int length = words.length;
        int count = 0;
        boolean[] visited = new boolean[length];
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);
        
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                String s = q.poll();

                if (target.equals(s)) {
                    return count;
                }
                
                for (int j = 0; j < length; j++) {
                    if (!visited[j]) {
                        int checkCnt = 0;
                        for (int k = 0; k < s.length(); k++) {
                            if (s.charAt(k) != words[j].charAt(k)) {
                                checkCnt++;
                            }
                        }
                        
                        if (checkCnt == 1) {
                            q.offer(words[j]);
                            visited[j] = true;
                        }
                    }
                }
            }
            count++;
        }
        
        return answer;
    }
}