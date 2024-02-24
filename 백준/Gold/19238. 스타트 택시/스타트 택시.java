import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, fuel, userCnt;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited;
	static int[][] map;
	static User[][] userMap;
	
	static class User {
		int endR, endC;

		public User(int endR, int endC) {
			this.endR = endR;
			this.endC = endC;
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        userCnt = M;
        
        map = new int[N][N];
        userMap = new User[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken()) - 1;
        int startC = Integer.parseInt(st.nextToken()) - 1;
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken()) - 1;
        	int c = Integer.parseInt(st.nextToken()) - 1;
        	int endR = Integer.parseInt(st.nextToken()) - 1;
        	int endC = Integer.parseInt(st.nextToken()) - 1;
        	userMap[r][c] = new User(endR, endC);
        }
        
        play(startR, startC);
        System.out.println(fuel);
    }
    
    private static void play(int startR, int startC) {
    	int r = startR;
    	int c = startC;
    	Queue<Point> q = new ArrayDeque<>();
    	q.offer(new Point(r, c));
    	
    	while (userCnt > 0) {
    		int userR = N;
        	int userC = N;
        	int distance = 0;
        	boolean findUserFlag = false;
        	visited = new boolean[N][N];
        	
    		while (!q.isEmpty()) {
    			int qSize = q.size();
    			for (int i = 0; i < qSize; i++) {
    				Point p = q.poll();
    				r = p.x;
    				c = p.y;
    				
    				// 택시 시작 위치에 승객이 있을 경우
    				if (userMap[r][c] != null && distance == 0) {
    					distance--;
    					userR = r;
    					userC = c;
    					findUserFlag = true;
    					break;
    				}
    				
    				for (int dir = 0; dir < 4; dir++) {
    					int nr = r + dr[dir];
    					int nc = c + dc[dir];
    					
    					// 맵 안이고 이전에 탐색하지 않았고 벽이 아니라면
    					if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
    						q.offer(new Point(nr, nc));
    						visited[nr][nc] = true;
    						
    						// 승객이 있는 위치라면
    						if (userMap[nr][nc] != null) {
    							if (nr < userR || (nr == userR && nc < userC)) {
    								userR = nr;
    								userC = nc;
    								findUserFlag = true;
    							}
    						}
    					}
    				}
    			}
    			distance++;
    			
    			if (findUserFlag) {
    				break;
    			}
    		}
    		
    		// 최단 거리의 승객을 찾았을 경우
			if (findUserFlag) {
				User user = userMap[userR][userC];
				int endDistance = 0;
				
				// 출발 위치와 도착 위치가 같지 않을 경우
				if (!(userR == user.endR && userC == user.endC)) {
					// 목적지까지 bfs탐색을 통해 가야함
					boolean endFindFlag = false;
					Queue<Point> q2 = new ArrayDeque<>();
					boolean[][] visited2 = new boolean[N][N];
					q2.offer(new Point(userR, userC));
					visited[userR][userC] = true;
					
					while (!q2.isEmpty()) {
		    			int qSize2 = q2.size();
		    			for (int i = 0; i < qSize2; i++) {
		    				Point p = q2.poll();
		    				r = p.x;
		    				c = p.y;
		    				
		    				for (int dir = 0; dir < 4; dir++) {
		    					int nr = r + dr[dir];
		    					int nc = c + dc[dir];
		    					
		    					if (nr == user.endR && nc == user.endC) {
		    						endFindFlag = true;
		    						break;
		    					}
		    					
		    					// 맵 안이고 이전에 탐색하지 않았고 벽이 아니라면
		    					if (isIn(nr, nc) && !visited2[nr][nc] && map[nr][nc] == 0) {
		    						q2.offer(new Point(nr, nc));
		    						visited2[nr][nc] = true;
		    					}
		    				}
		    				
		    				if (endFindFlag) {
		    					break;
		    				}
		    			}
		    			endDistance++;
		    			
		    			if (endFindFlag) {
		    				break;
		    			}
					}
					
					// 도착지를 찾지 못한 경우
					if (!endFindFlag) {
						System.out.println(-1);
						System.exit(0);
					}
				}
				
				
				// 손님까지 가는 거리 + 손님 태우고 목적지까지 가는 거리가 기름보다 많을 경우
				if (distance + endDistance > fuel) {
					System.out.println(-1);
					System.exit(0);
				}
				
				// 손님 태우고 목적지까지 이동
				userMap[userR][userC] = null;
				q.clear();
				q.offer(new Point(user.endR, user.endC));
				fuel = fuel - distance + endDistance;
				userCnt--;
			// 승객을 찾지 못했을 경우
			} else {
				System.out.println(-1);
				System.exit(0);
			}
    	}
    	
	}

	private static boolean isIn(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
   
}