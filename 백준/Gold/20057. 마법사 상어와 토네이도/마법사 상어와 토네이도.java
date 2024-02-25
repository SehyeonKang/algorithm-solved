import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	// 9시 방향부터 반시계 방향
	static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        play();
        System.out.println(answer);
    }
    
    // 토네이도 시전
    private static void play() {
    	// 토네이도의 위치와 방향
    	int r = N / 2;
    	int c = N / 2;
    	int dir = 0;
    	int s;
    	
    	for (s = 1; s < N; s++) {
    		// 이동해야하는 칸수만큼 반복
    		for (int i = 0; i < s * 2; i++) {
    			// 방향 전환
    			if (i == s) {
    				dir = (dir + 2) % 8;
    			}
    			
    			r += dr[dir];
    			c += dc[dir];
    			blowSand(r, c, dir);
    		}
    		
    		// 방향 전환
    		dir = (dir + 2) % 8;
    	}
    	
    	for (int i = 0; i < s - 1; i++) {
    		r += dr[dir];
			c += dc[dir];
			blowSand(r, c, dir);
    	}
    }
    
    // 모래를 흩날리게 하는 기능을 가진 메서드
    private static void blowSand(int r, int c, int dir) {
    	int sand = map[r][c];
    	int blowedSand = 0;
    	int nr;
    	int nc;
    	int nDir;
    	
    	for (int i = 0; i < 8; i++) {
    		nDir = (dir + i) % 8;
    		if (i == 0) {
    			nr = r + 2 * dr[nDir];
    			nc = c + 2 * dc[nDir];
    			if (!isIn(nr, nc)) {
    				answer += sand * 0.05;
    			} else {
    				map[nr][nc] += sand * 0.05;
    			}
    			blowedSand += sand * 0.05;
    		} else if (i == 1 || i == 7) {
    			nr = r + dr[nDir];
    			nc = c + dc[nDir];
    			if (!isIn(nr, nc)) {
    				answer += sand * 0.1;
    			} else {
    				map[nr][nc] += sand * 0.1;
    			}
    			blowedSand += sand * 0.1;
    		} else if (i == 2 || i == 6) {
    			nr = r + dr[nDir];
    			nc = c + dc[nDir];
    			if (!isIn(nr, nc)) {
    				answer += sand * 0.07;
    			} else {
    				map[nr][nc] += sand * 0.07;
    			}
    			blowedSand += sand * 0.07;
    			
    			nr = r + 2 * dr[nDir];
    			nc = c + 2 * dc[nDir];
    			if (!isIn(nr, nc)) {
    				answer += sand * 0.02;
    			} else {
    				map[nr][nc] += sand * 0.02;
    			}
    			blowedSand += sand * 0.02;
    		} else if (i == 3 || i == 5) {
    			nr = r + dr[nDir];
    			nc = c + dc[nDir];
    			if (!isIn(nr, nc)) {
    				answer += sand * 0.01;
    			} else {
    				map[nr][nc] += sand * 0.01;
    			}
    			blowedSand += sand * 0.01;
    		}
    	}
    	
    	nr = r + dr[dir];
    	nc = c + dc[dir];
    	if (!isIn(nr, nc)) {
    		answer += sand - blowedSand;
    	} else {
    		map[nr][nc] += sand - blowedSand;
    	}
    	map[r][c] = 0;
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}