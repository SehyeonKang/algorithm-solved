import java.util.*;

class Solution {
    
    // 아래, 왼쪽, 오른쪽, 위 - 사전순
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static char[] moveInfo = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        x--;
        y--;
        r--;
        c--;
        int minDistance = Math.abs(x - r) + Math.abs(y - c);
        
        // 최소 거리와 k의 짝/홀수 여부가 다른 경우, 이동 횟수가 최소 거리보다 짧은 경우 impossible
        if ((minDistance % 2 == 0 && k % 2 == 1) || (minDistance % 2 == 1 && k % 2 == 0) || minDistance > k) {
            return "impossible";
        }
        
        // 여유 횟수만큼 움직이기
        int nx = x;
        int ny = y;
        while (true) {
            int distance = Math.abs(nx - r) + Math.abs(ny - c);
            if (distance == k) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                if (isIn(nx + dr[d], ny + dc[d], n, m)) {
                    nx += dr[d];
                    ny += dc[d];
                    k--;
                    sb.append(moveInfo[d]);
                    break;
                }
            }
        }
        // 탈출 지점으로 이동하기
        int rMove = r - nx;
        int cMove = c - ny;
        if (rMove >= 0) {
            for (int i = 0; i < rMove; i++) {
                sb.append("d");
            }
            if (cMove >= 0) {
                for (int i = 0; i < cMove; i++) {
                    sb.append("r");
                }
            } else {
                for (int i = 0; i < cMove * -1; i++) {
                    sb.append("l");
                }
            }
        } else {
            if (cMove >= 0) {
                for (int i = 0; i < cMove; i++) {
                    sb.append("r");
                }
            } else {
                for (int i = 0; i < cMove * -1; i++) {
                    sb.append("l");
                }
            }
            for (int i = 0; i < rMove * -1; i++) {
                sb.append("u");
            }
        }
        answer = sb.toString();
        return answer;
    }
    
    private static boolean isIn(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}