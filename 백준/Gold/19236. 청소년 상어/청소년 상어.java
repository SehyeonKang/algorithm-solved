import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int maxScore;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] map;
    static boolean[] aliveFishes;
    static Fish[] fishes;
    
    static class Fish {
        int no, direction, r, c;
        
        public Fish(int no, int direction, int r, int c) {
            this.no = no;
            this.direction = direction;
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 초기 세팅
        map = new int[4][4];
        aliveFishes = new boolean[17];
        fishes = new Fish[17];
        for (int i = 0 ; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = a;
                fishes[a] = new Fish(a, b, i, j);
            }
        }
        
        Arrays.fill(aliveFishes, true);
        
        // 상어가 공간에 들어갔을 때
        Fish initFish = fishes[map[0][0]];
        fishes[0] = new Fish(0, initFish.direction, 0, 0);
        aliveFishes[initFish.no] = false;
        map[0][0] = 0;
        maxScore += initFish.no;
        
        play(map, maxScore, fishes, 0);
        
        System.out.println(maxScore);
    }
    private static void play(int[][] map, int score, Fish[] fishes, int depth) {
        moveFish(map, score, fishes, depth);
        moveShark(map, score, fishes, depth);
    }
    
    private static void moveFish(int[][] map, int score, Fish[] fishes, int depth) {
        for (int i = 1; i <= 16; i++) {
            if (!aliveFishes[i]) {
                continue;
            }
            
            Fish fish = fishes[i];
            int r = fish.r;
            int c = fish.c;
            int dir = fish.direction;
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            // 현재 이동할 수 없다면 이동할 수 있는 칸 찾기
            int turnCnt = 0;
            while ((!isIn(nr, nc) || map[nr][nc] == 0) && turnCnt < 8) {
                dir = (dir + 1) % 8;
                nr = r + dr[dir];
                nc = c + dc[dir];
                turnCnt++;
            }
            
            // 이동할 수 있는 칸이 있는 경우
            if (turnCnt < 8) {
                // 빈 칸인 경우
                if (map[nr][nc] == -1) {
                    map[nr][nc] = fish.no;
                    map[r][c] = -1;
                    fish.r = nr;
                    fish.c = nc;
                    fish.direction = dir;
                    
                // 다른 물고기가 있는 경우
                } else {
                    int tmp = map[nr][nc];
                    map[nr][nc] = map[r][c];
                    map[r][c] = tmp;
                    fish.r = nr;
                    fish.c = nc;
                    fish.direction = dir;
                    
                    Fish switchedFish = fishes[map[r][c]];
                    switchedFish.r = r;
                    switchedFish.c = c;
                }
            }
        }
    }
    
    private static void moveShark(int[][] map, int score, Fish[] fishes, int depth) {
        boolean flag = false;
        Fish shark = fishes[0];
        int r = shark.r;
        int c = shark.c;
        int dir = shark.direction;
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        while(isIn(nr, nc)) {
            // 먹을 수 있는 물고기가 있는 경우
            if (map[nr][nc] > 0) {
                flag = true;
                Fish target = fishes[map[nr][nc]];
                shark.direction = target.direction;
                shark.r = nr;
                shark.c = nc;
                aliveFishes[target.no] = false;
                map[nr][nc] = 0;
                map[r][c] = -1;
                int[][] newMap = new int[4][4];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        newMap[i][j] = map[i][j];
                    }
                }
                
                Fish[] newFishes = new Fish[17];
                for (int i = 0; i <= 16; i++) {
                	newFishes[i] = new Fish(fishes[i].no, fishes[i].direction, fishes[i].r, fishes[i].c);
                }
                
                play(newMap, score + target.no, newFishes, depth + 1);
                
                shark.direction = dir;
                shark.r = r;
                shark.c = c;
                aliveFishes[target.no] = true;
                map[nr][nc] = target.no;
                map[r][c] = 0;
            }
            
            nr += dr[dir];
            nc += dc[dir];
        }
        
        if (!flag) {
            maxScore = Math.max(maxScore, score);
        }
    }
    
    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < 4 && c < 4;
    }
}
// 2h 30m