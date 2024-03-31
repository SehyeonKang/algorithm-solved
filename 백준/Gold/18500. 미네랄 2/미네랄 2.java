import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[] order;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] clusterVisited;
	static PriorityQueue<Mineral> cluster;
	
	static class Mineral implements Comparable<Mineral> {
		int r, c;

		public Mineral(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Mineral o) {
			return o.r - this.r;
		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 초기 세팅
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        order = new int[N];
        for (int i = 0; i < N; i++) {
        	order[i] = Integer.parseInt(st.nextToken());
        }
        
        play();
        
        for (int i = 0; i < R; i++) {
        	for (int j = 0; j < C; j++) {
        		sb.append(map[i][j]);
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void play() {
    	for (int i = 0; i < N; i++) {
    		throwStick(i);
    		
    		// 바닥에 있는 클러스터 찾기
    		visited = new boolean[R][C];
    		for (int c = 0; c < C; c++) {
    			if (!visited[R - 1][c] && map[R - 1][c] == 'x') {
    				bfs(R - 1, c, false);
    			}
    		}
    		
    		for (int r = 0; r < R; r++) {
    			for (int c = 0; c < C; c++) {
    				if (!visited[r][c] && map[r][c] == 'x') {
    					// 공중에 있는 클러스터 찾기
    					bfs(r, c, true);
    					drop();
    					break;
    				}
    			}
    		}
     	}
    }
    
    // 막대 던지기
    private static void throwStick(int orderIdx) {
    	int r = R - order[orderIdx];
    	int c = orderIdx % 2 == 0 ? 0 : C - 1;
    	
    	while (isIn(r, c)) {
    		// 미네랄이 있는 경우
    		if (map[r][c] == 'x') {
    			map[r][c] = '.';
    			break;
    		}
    		c = orderIdx % 2 == 0 ? c + 1 : c - 1;
    	}
    }
    
    // 클러스터 탐색하기
    private static void bfs(int startR, int startC, boolean flag) {
    	if (flag) {
    		cluster = new PriorityQueue<>();
    		clusterVisited = new boolean[R][C];
    		cluster.offer(new Mineral(startR, startC));
    		clusterVisited[startR][startC] = true;
    	}
    	Queue<Point> q = new ArrayDeque<>();
    	q.offer(new Point(startR, startC));
    	visited[startR][startC] = true;
    	
    	while (!q.isEmpty()) {
    		int qSize = q.size();
    		for (int i = 0; i < qSize; i++) {
    			Point p = q.poll();
    			int r = p.x;
    			int c = p.y;
    			
    			for (int d = 0; d < 4; d++) {
    				int nr = r + dr[d];
    				int nc = c + dc[d];
    				
    				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'x') {
    					if (flag) {
    						cluster.offer(new Mineral(nr, nc));
    						clusterVisited[nr][nc] = true;
    					}
    					q.offer(new Point(nr, nc));
    					visited[nr][nc] = true;
    				}
    			}
    		}
    	}
    }
    
    // 클러스터 떨어트리기
    private static void drop() {  	
    	// 바닥의 클러스터와 가장 짧은 거리 구하기
    	int minDistance = R;
    	for (Mineral mineral : cluster) {
    		int r = mineral.r;
    		int c = mineral.c;
    		if (r > 0) {
    			r++;
    			while (isIn(r, c)) {
    				if (map[r][c] == 'x') {
    					if (!clusterVisited[r][c]) {
    						minDistance = Math.min(minDistance, r - mineral.r - 1);
    					}
    					break;
    				} else if (r == R - 1) {
    					minDistance = Math.min(minDistance, r - mineral.r);
    					break;
    				}
    				r++;
    			}
    		}
    	}
    	
    	// 클러스터 떨어트리기
    	while (!cluster.isEmpty()) {
    		Mineral mineral = cluster.poll();
    		int r = mineral.r;
    		int c = mineral.c;
    		map[r][c] = '.';
    		map[r + minDistance][c] = 'x';
    		visited[r + minDistance][c] = true;
    	}
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && r < R && c >= 0 && c < C;
    }
}