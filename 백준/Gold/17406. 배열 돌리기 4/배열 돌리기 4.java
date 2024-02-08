import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int minValue = Integer.MAX_VALUE;
	static int N, M, K;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[][] map;
	static boolean[] selected;
	static int[] orders;
	static Calculation[] calcs;
	
	static class Calculation {
		int r, c, s;

		public Calculation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        selected = new boolean[K];
        orders = new int[K];
        calcs = new Calculation[K];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	calcs[i] = new Calculation(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        
        recur(0);
        System.out.println(minValue);
    }
    
    private static void recur(int count) {
    	if (count == K) {
    		int[][] newMap = new int[N][M];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < M; j++) {
    				newMap[i][j] = map[i][j];
    			} 
    		}
    		rotate(newMap);
    		calculateValue(newMap);
    		
    		return;
    	}
    	
    	for (int i = 0; i < K; i++) {
    		if (selected[i]) {
    			continue;
    		}
    		selected[i] = true;
    		orders[count] = i;
    		recur(count + 1);
    		selected[i] = false;
    	}
    }
    
    private static void rotate(int[][] map) {
    	
    	for (int cnt = 0; cnt < K; cnt++) {
    		Calculation calc = calcs[orders[cnt]];
    		int r = calc.r;
    		int c = calc.c;
    		int s = calc.s;
    		
    		for (int rotateCnt = 0; rotateCnt < s; rotateCnt++) {
    			rotateLine(map, r, c, s, rotateCnt);
    		}
    	}
    }
    
    private static void rotateLine(int[][] map, int midR, int midC, int s, int rotateCnt) {
    	int startR = midR - s + rotateCnt;
    	int startC = midC - s + rotateCnt;
    	int r = startR;
		int c = startC;
		int tmp = map[r][c];
		
		for (int d = 0; d < 4; d++) {
			while (true) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (!isIn(nr, nc, midR, midC, s - rotateCnt)) {
					break;
				}
				
				if (nr == startR && nc == startC - rotateCnt) {
					map[nr][nc] = tmp;
					break;
				}
				
				int swap = map[nr][nc];
				map[nr][nc] = tmp;
				tmp = swap;
				r = nr;
				c = nc;
			}
		}
    }
    
    private static boolean isIn(int curR, int curC, int r, int c, int s) {
    	return curR >= r - s && curC >= c - s && curR <= r + s && curC <= c + s;
    }
    
    private static void calculateValue(int[][] map) {
    	int min = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < N; i++) {
    		int sum = 0;
    		for (int j = 0; j < M; j++) {
    			sum += map[i][j];
    		}
    		min = Math.min(min, sum);
    	}
    	
    	minValue = Math.min(minValue, min);
    }
}
