import java.util.*;

class Solution {
    static int userCnt;
    static boolean[] check;
    static int[] idxs;
    static int answer = 0;
    static boolean[] visited;
    static int[] numbers;
    static String[] user_ids;
    static String[] banned_ids;
    
    public int solution(String[] user_id, String[] banned_id) {
        userCnt = user_id.length;
        check = new boolean[userCnt];
        
        user_ids = user_id;
        banned_ids = banned_id;
        
        recur(0, 0, banned_id.length);
        return answer;
    }
    
    private void recur(int cnt, int start, int n) {
        if (cnt == n) {
            banCheck(n);
            return;
        }
        
        for (int i = start; i < userCnt; i++) {
            check[i] = true;
            recur(cnt + 1, i + 1, n);
            check[i] = false;
        }
    }
    
    private void banCheck(int n) {
        idxs = new int[n];
        visited = new boolean[n];
        numbers = new int[n];
        int idx = 0;
        for (int i = 0; i < userCnt; i++) {
            if (check[i]) {
                idxs[idx++] = i;
            }
        }
        
        boolean flag = recur2(0, n);
        if (flag) {
            answer++;
        }
    }
    
    private boolean recur2(int cnt, int n) {
        if (cnt == n) {
            for (int i = 0; i < n; i++) {
                int number = numbers[i];
                if (user_ids[number].length() != banned_ids[i].length()) {
                    return false;
                }
                
                for (int j = 0; j < user_ids[number].length(); j++) {
                    if (banned_ids[i].charAt(j) == '*') {
                        continue;
                    }
                    if (banned_ids[i].charAt(j) != user_ids[number].charAt(j)) {
                        return false;
                    }
                }
                
            }
            
            return true;
        }
        
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            if (!visited[i]) {
                visited[i] = true;
                numbers[cnt] = idxs[i];
                flag = recur2(cnt + 1, n);
                visited[i] = false;
            }
            
            if (flag) {
                return true;
            }
        }
        
        return false;
    }
}