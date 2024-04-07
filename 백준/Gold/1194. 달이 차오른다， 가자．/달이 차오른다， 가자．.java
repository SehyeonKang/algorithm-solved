import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] map;
	static boolean[][][] visited;
	
	static class Node {
		int r, c, moveCnt, keyInfo;

		public Node(int r, int c, int moveCnt, int keyInfo) {
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
			this.keyInfo = keyInfo;
		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        answer = 1;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][64];
        int startR = -1;
        int startC = -1;
        for (int i = 0; i < N; i++) {
        	map[i] = br.readLine().toCharArray();
        	for (int j = 0; j < M; j++) {
        		if (map[i][j] == '0') {
        			map[i][j] = '.';
        			startR = i;
        			startC = j;
        		}
        	}
        }
        
        bfs(startR, startC);
        System.out.println(answer);
    }
    
    private static void bfs(int startR, int startC) {
    	Queue<Node> q = new ArrayDeque<>();
    	q.offer(new Node(startR, startC, 0, 0));
    	visited[startR][startC][0] = true;
    	
    	while (!q.isEmpty()) {
    		int qSize = q.size();
    		for (int qCnt = 0; qCnt < qSize; qCnt++) {
    			Node cur = q.poll();
    			int r = cur.r;
    			int c = cur.c;
    			int moveCnt = cur.moveCnt;
    			int keyInfo = cur.keyInfo;
    			
    			for (int d = 0; d < 4; d++) {
    				int nr = r + dr[d];
    				int nc = c + dc[d];
    				
    				if (isIn(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc][keyInfo]) {
    					char cond = map[nr][nc];
    					// 이동한 칸이 출구인 경우
    					if  (cond == '1') {
    						return;
    					}
    					// 이동한 칸이 빈 칸인 경우
    					if (cond == '.') {
    						q.offer(new Node(nr, nc, moveCnt + 1, keyInfo));
    						visited[nr][nc][keyInfo] = true;
    					// 이동한 칸이 열쇠인 경우
    					} else if (cond > 96) {
    						int nKeyInfo = keyInfo | (1 << (cond - 97));
    						q.offer(new Node(nr, nc, moveCnt + 1, nKeyInfo));
    						visited[nr][nc][keyInfo] = true;
    						visited[nr][nc][nKeyInfo] = true;
    					// 이동한 칸이 문이고 열쇠가 있는 경우
    					} else if ((keyInfo & (1 << (cond - 65))) == (1 << (cond - 65))) {
    						q.offer(new Node(nr, nc, moveCnt + 1, keyInfo));
    						visited[nr][nc][keyInfo] = true;
    					}
    				}
    			}
    		}
    		answer++;
    	}
    	answer = -1;
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < M;
    }
}