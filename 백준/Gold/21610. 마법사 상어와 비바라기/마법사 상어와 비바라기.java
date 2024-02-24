import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][] map;
	static Queue<Cloud> clouds;
	static Move[] moves;
	
	static class Cloud {
		int r, c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Move {
		int d, s;

		public Move(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 초기 세팅
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        moves = new Move[M];
        clouds = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	moves[i] = new Move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        
        play();
        
        // 바구니에 들어있는 물의 양 합 구하기
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		answer += map[i][j];
        	}
        }
        System.out.println(answer);
    }
    
    private static void play() {
    	boolean[][] visited;
    	// 비바라기 시전
    	for (int i = N - 2; i < N; i++) {
    		for (int j = 0; j < 2; j++) {
    			clouds.offer(new Cloud(i, j));
    		}
    	}
    	
    	// M번 명령 반복
    	for (int time = 0; time < M; time++) {
    		int d = moves[time].d;
    		int s = moves[time].s;
    		int cloudsSize = clouds.size();
    		visited = new boolean[N][N];
    		
    		// 생성된 구름 개수만큼 반복
    		for (int idx = 0; idx < cloudsSize; idx++) {
    			Cloud cloud = clouds.poll();
    			
    			// 1. 모든 구름이 d방향으로 s칸 이동한다.
    			int nr = cloud.r + dr[d] * (s % N);
    			int nc = cloud.c + dc[d] * (s % N);
    			
    			if (nr > 0) {
    				nr %= N;
    			} else if (nr < 0) {
    				nr += N;
    			}
    			if (nc > 0) {
    				nc %= N;
    			} else if (nc < 0) {
    				nc += N;
    			}
    			
    			cloud.r = nr;
    			cloud.c = nc;
    			
    			// 2~3. 구름에서 비가 내려 바구니의 물이 1 증가하고 구름이 사라진다.
    			map[nr][nc]++;
    			visited[nr][nc] = true;
    		}
    		
    		// 4. 물복사버그 마법을 시전한다.
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (visited[i][j]) {
    					for (int dir = 1; dir < 8; dir += 2) {
    	    				if (isIn(i + dr[dir], j + dc[dir]) && map[i + dr[dir]][j + dc[dir]] > 0) {
    	    					map[i][j]++;
    	    				}
    	    			}
    				}
    			}
    		}
    		
    		// 5. 구름이 생기고 물의 양이 줄어든다.
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (map[i][j] >= 2 && !visited[i][j]) {
    					clouds.offer(new Cloud(i, j));
    					map[i][j] -= 2;
    				}
    			}
    		}
    	}
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}