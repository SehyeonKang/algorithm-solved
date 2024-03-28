import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<Point> sQ = new ArrayDeque<>();
        Queue<Point> wQ = new ArrayDeque<>();
        boolean[][] sVisited = new boolean[R][C];
        boolean[][] wVisited = new boolean[R][C];
        
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
        	String s = br.readLine();
        	for (int j = 0; j < C; j++) {
        		map[i][j] = s.charAt(j);
        		if (map[i][j] == 'S') {
        			sQ.offer(new Point(i, j));
        			sVisited[i][j] = true;
        		} else if (map[i][j] == '*') {
        			wQ.offer(new Point(i, j));
        			wVisited[i][j] = true;
        		}
        	}
        }
        
        int time = 1;
        while (!sQ.isEmpty()) {
        	int sQSize = sQ.size();
        	int wQSize = wQ.size();
        	
        	for (int i = 0; i < sQSize; i++) {
        		Point p = sQ.poll();
        		int r = p.x;
        		int c = p.y;
        		
        		for (int d = 0; d < 4; d++) {
        			int nr = r + dr[d];
        			int nc = c + dc[d];
        			
        			if (isIn(nr, nc) && !sVisited[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != '*') {
        				boolean check = false;
        				for (int dir = 0; dir < 4; dir++) {
        					if (isIn(nr + dr[dir], nc + dc[dir]) && map[nr + dr[dir]][nc + dc[dir]] == '*') {
        						check = true;
        						break;
        					}
        				}
        				
        				if (!check) {
        					sQ.offer(new Point(nr, nc));
        					sVisited[nr][nc] = true;
        				}
        				
        				if(map[nr][nc] == 'D') {
        					System.out.println(time);
        					System.exit(0);
        				}
        			}
        		}
        	}
        	
        	for (int i = 0; i < wQSize; i++) {
        		Point p = wQ.poll();
        		int r = p.x;
        		int c = p.y;
        		
        		for (int d = 0; d < 4; d++) {
        			int nr = r + dr[d];
        			int nc = c + dc[d];
        			
        			if (isIn(nr, nc) && !wVisited[nr][nc] && map[nr][nc] != 'D' && map[nr][nc] != 'X') {
        				wQ.offer(new Point(nr, nc));
        				wVisited[nr][nc] = true;
        				map[nr][nc] = '*';
        			}
        		}
        	}
        	
        	time++;
        }
        
        System.out.println("KAKTUS");
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && r < R && c >= 0 && c < C;
    }
}