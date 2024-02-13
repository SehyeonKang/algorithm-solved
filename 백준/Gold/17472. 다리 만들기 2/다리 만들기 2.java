import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
	static boolean noConnectFlag;
    static int minLength = Integer.MAX_VALUE;
    static int N, M, island, bridgeLength;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;
    static int[][] distances;
    static List<List<Point>> islands;
    static boolean[] connectedLand;
    static boolean[] selected;
    static int[] order;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        islands = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 섬 찾고 섬마다 번호 붙이기
        island = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islands.add(new ArrayList<>());
                    bfs(i, j, island);
                    island++;
                }
            }
        }
        
        selected = new boolean[island];
        order = new int[island];
        recur(1);
       
        if (minLength == Integer.MAX_VALUE) {
        	System.out.println(-1);
        } else {
        	System.out.println(minLength);  
        }
    }
    
    private static void bfs(int startR, int startC, int island) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startR, startC));
        visited[startR][startC] = true;
        map[startR][startC] = island;
        islands.get(island - 1).add(new Point(startR, startC));
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                int r = p.x;
                int c = p.y;
                
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    
                    if (isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
                        q.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                        map[nr][nc] = island;
                        islands.get(island - 1).add(new Point(nr, nc));
                    }
                }
            }
        }
    }
    
    private static void recur(int cnt) {
    	if (noConnectFlag) {
    		return;
    	}
    	
    	if (cnt == island) {
    		connectedLand = new boolean[island];
    		bridgeLength = 0;
    		connectedLand[order[1]] = true;
    		distances = new int[island][island];
    		
            for (int i = 1; i < island; i++) {
                buildBridge(order[i]);
            }
            
            boolean[] connectCheck = new boolean[island];
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(1);
            connectCheck[1] = true;
            int islandCnt = 1;
           	while (!q.isEmpty()) {
           		int idx = q.poll();
           		for (int i = 1; i < island; i++) {
           			if (distances[idx][i] > 0 && !connectCheck[i]) {
           				connectCheck[i] = true;
           				q.offer(i);
           				islandCnt++;
           			}
           		}
            }
           	
           	if (islandCnt + 1 == island) {
           		minLength = Math.min(minLength, bridgeLength);
           	}
    		return;
    	}
    	
    	for (int i = 1; i < island; i++) {
    		if (selected[i]) {
    			continue;
    		}
    		selected[i] = true;
    		order[cnt] = i;
    		recur(cnt + 1);
    		selected[i] = false;
    	}
    }
    
    private static void buildBridge(int islandNo) {
        boolean flag = true;
        for (int i = 1; i < island; i++) {
            if (!connectedLand[i]) {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            return;
        }
        
        List<Point> lands = islands.get(islandNo - 1);
        
        for (Point land : lands) {
            int r = land.x;
            int c = land.y;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int length = 0;
                while (true) {
                    if (!isIn(nr, nc)) {
                        break;
                    }
                    
                    if (map[nr][nc] == 0) {
                        length++;
                    } else if (map[nr][nc] > 0) {
                        if (length < 2) {
                            break;
                        } else {
                        	if (connectedLand[map[nr][nc]] && distances[islandNo][map[nr][nc]] > length) {
                        		bridgeLength -= distances[islandNo][map[nr][nc]];
                        		bridgeLength += length;
                        		distances[islandNo][map[nr][nc]] = length;
                        		distances[map[nr][nc]][islandNo] = length;
                        	} else if (!connectedLand[map[nr][nc]]){
                        		distances[islandNo][map[nr][nc]] = length;
                        		distances[map[nr][nc]][islandNo] = length;
                        		connectedLand[map[nr][nc]] = true;
                        		bridgeLength += length;
                        	}
                            break;
                        }
                    }
                    
                    nr += dr[i];
                    nc += dc[i];
                }
            }
        }
    }
    
    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}