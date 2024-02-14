import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    
    static int N, M, K;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static MapInfo[][] map;
    static Queue<Cell> activeQueue;
    static Queue<Cell> inactiveQueue;
    
    // 맵에 번식된 세포의 시간과 생명력 수치를 저장하는 클래스
    static class MapInfo {
        int time, x;

        public MapInfo(int time, int x) {
            this.time = time;
            this.x = x;
        }
    }
    
    // 세포의 위치와 생명력 수치, 번식된 시간을 저장하는 클래스
    static class Cell {
        int r, c, x, hour;

        public Cell(int r, int c, int x, int hour) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.hour = hour;
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            map = new MapInfo[351][351];
            activeQueue = new ArrayDeque<>();
            inactiveQueue = new ArrayDeque<>();
            for (int i = 150; i < N + 150; i++) {
                st = new StringTokenizer(br.readLine());
                
                for (int j = 150; j < M + 150; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x > 0) {
                        map[i][j] = new MapInfo(1, x);
                        inactiveQueue.offer(new Cell(i, j, x, 0));
                    }
                }
            }
            
            play();
            
            int answer = activeQueue.size() + inactiveQueue.size();
            sb.append("#" + tc + " " + answer + "\n");
        }
        
        System.out.println(sb);
    }
    
    private static void play() {
        // K시간동안 반복
        for (int time = 2; time <= K + 1; time++) {
            int iqSize = inactiveQueue.size();
            int aqSize = activeQueue.size();
            
            // 비활성 상태인 세포들 번식, 활성 여부 확인
            for (int i = 0; i < iqSize; i++) {
                Cell cell = inactiveQueue.poll();
                
                // 활성화 상태에서 1시간 지났을 경우 번식함
                if (cell.x == cell.hour) {
                    breed(cell, time);
                    if (cell.x > 1) {
                    	cell.hour++;
                        activeQueue.offer(cell);
                    }
                } else {
                    cell.hour++;
                    inactiveQueue.offer(cell);
                }
            }
            
            // 활성 상태인 세포들 생존 여부 확인
            for (int i = 0; i < aqSize; i++) {
                Cell cell = activeQueue.poll();
                
                // 죽지 않는 세포는 다시 활성 세포 큐에 넣음
                if (cell.x * 2 - 1 > cell.hour) {
                	cell.hour++;
                    activeQueue.offer(cell);
                }
            }
        }
    }
    
    // 세포가 번식하는 메서드
    private static void breed(Cell cell, int time) {
        int r = cell.r;
        int c = cell.c;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 맵이 비어있는 경우
            if (map[nr][nc] == null) {
                map[nr][nc] = new MapInfo(time, cell.x);
                inactiveQueue.offer(new Cell(nr, nc, cell.x, 0));
                
            // 번식하려는 위치에 동시간대에 먼저 번식한 생명력 수치가 낮은 세포가 있는 경우
            } else if (map[nr][nc].time == time && map[nr][nc].x < cell.x) {
                map[nr][nc].x = cell.x;

                // 비활성화 세포 큐에서 동일 위치에 있던 생명력이 낮은 세포 삭제
                for (int j = 0; j <= inactiveQueue.size(); j++) {
                    Cell preCell = inactiveQueue.poll();
                    
                    if (preCell.r != nr || preCell.c != nc) {
                        inactiveQueue.offer(preCell);
                    }
                }
                
                inactiveQueue.offer(new Cell(nr, nc, cell.x, 0));
            }
        }
    }
}