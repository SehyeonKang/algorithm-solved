import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 1;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int r = maps.length;
        int c = maps[0].length;
        boolean[][] visited = new boolean[r][c];
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point p = q.poll();
                int curR = p.x;
                int curC = p.y;
                
                if (curR == r - 1 && curC == c - 1) {
                    return answer;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];
                    if (isIn(nr, nc, r, c) && !visited[nr][nc] && maps[nr][nc] == 1) {
                        q.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            answer++;
        }
        
        
        return -1;
    }
    
    public boolean isIn(int r, int c, int mapR, int mapC) {
        return r >= 0 && r < mapR && c >= 0 && c < mapC;
    }
}