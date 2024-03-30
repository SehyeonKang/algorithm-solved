import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	
	static int N, W, H, initStoneCnt, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[] balls;
    static int[][] initMap;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            answer = Integer.MAX_VALUE;
            initStoneCnt = 0;
            balls = new int[N];
            initMap = new int[H][W];
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                	initMap[r][c] = Integer.parseInt(st.nextToken());
                    if (initMap[r][c] > 0) {
                        initStoneCnt++;
                    }
                }
            }
            
            recur(0);
            
            sb.append("#" + tc + " " + answer + "\n");
        }
		
        System.out.println(sb);
	}
	
	// 구슬 떨어트리는 경우의 수 구하기
    private static void recur(int cnt) {
        if (cnt == N) {
            play();
            return;
        }
        
        for (int i = 0; i < W; i++) {
            balls[cnt] = i;
            recur(cnt + 1);
        }
    }
    
    // 구슬 떨어트리기
    private static void play() {
    	int removeCnt = 0;
        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
        	map[i] = initMap[i].clone();
        }
        
        for (int ballIdx = 0; ballIdx < N; ballIdx++) {
        	int r = 0;
            int c = balls[ballIdx];
            while (r < H) {
                // 벽돌을 만난 경우
                if (map[r][c] > 0) {
                    Queue<Point> q = new ArrayDeque<>();
                    boolean[][] visited = new boolean[H][W];
                    q.offer(new Point(r, c));
                    visited[r][c] = true;
                    
                    while (!q.isEmpty()) {
                        int qSize = q.size();
                        
                        for (int i = 0; i < qSize; i++) {
                            Point p = q.poll();
                            int pR = p.x;
                            int pC = p.y;
                            
                            for (int d = 0; d < 4; d++) {
                                int nr = pR + dr[d];
                                int nc = pC + dc[d];
                                
                                for (int k = 1; k < map[pR][pC]; k++) {
                                	if (!isIn(nr, nc)) {
                                		break;
                                	}
                                	if (!visited[nr][nc] && map[nr][nc] > 0) {
                                		q.offer(new Point(nr, nc));
                                		visited[nr][nc] = true;
                                	}
                                	nr += dr[d];
                                	nc += dc[d];
                                }
                            }
                            map[pR][pC] = 0;
                            removeCnt++;
                        }
                    }
                    break;
                }
                r++;
            }
            
            // 벽돌 밑으로 떨어트리기
            for (int i = 0; i < W; i++) {
            	int height = H - 1;
            	for (int j = H - 1; j >= 0; j--) {
            		// 공중에 벽돌이 있을 경우
            		if (map[j][i] > 0) {
            			if (j < height) {
            				map[height][i] = map[j][i];
            				map[j][i] = 0;
            			}
            			height--;
            		}
            	}
            }
        }
        
        answer = Math.min(answer, initStoneCnt - removeCnt);
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && r < H && c >= 0 && c < W;
    }
}