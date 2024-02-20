import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] blindMap;
	static char[][] nonBlindMap;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        blindMap = new char[N][N];
        nonBlindMap = new char[N][N];
        for (int i = 0; i < N; i++) {
        	String s = br.readLine();
        	for (int j = 0; j < N; j++) {
        		char color = s.charAt(j);
        		nonBlindMap[i][j] = color;
        		if (color == 'G') {
        			blindMap[i][j] = 'R';
        		} else {
        			blindMap[i][j] = color;
        		}
        	}
        }
        
        int nonBlindCount = 0;
        int blindCount = 0;
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if (nonBlindMap[i][j] != '0') {
        			dfs(nonBlindMap, i, j, nonBlindMap[i][j]);
        			nonBlindCount++;
        		}
        		
        		if (blindMap[i][j] != '0') {
        			dfs(blindMap, i, j, blindMap[i][j]);
        			blindCount++;
        		}
        	}
        }
        
        System.out.println(nonBlindCount + " " + blindCount);
    }
    
    private static void dfs(char[][] map, int r, int c, char color) {
    	for (int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		
    		if (isIn(nr, nc) && map[nr][nc] == color) {
    			map[nr][nc] = '0';
    			dfs(map, nr, nc, color);
    		}
    	}
    }
    
    private static boolean isIn(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}