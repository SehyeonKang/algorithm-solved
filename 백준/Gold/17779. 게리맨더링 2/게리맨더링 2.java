import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	static int[][] map;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        makeRegion();
        System.out.println(answer);
    }
    
    private static void makeRegion() {
    	for (int r = 0; r < N - 2; r++) {
    		for (int c = 1; c < N - 1; c++) {
    			for (int d1 = 1; d1 < N - 1; d1++) {
    				if (c - d1 < 0 || r + d1 == N - 1) {
    					break;
    				}
    				
    				for (int d2 = 1; d2 < N - 1; d2++) {
    					if (c + d2 == N || r + d1 + d2 == N) {
    						break;
    					}
    					
    					calculate(r, c, d1, d2);
    				}
    			}
    		}
    	}
    }
    
    private static void calculate(int r, int c, int d1, int d2) {
    	int max = 0;
    	int min = Integer.MAX_VALUE;
    	int[] sum = new int[6];
    	boolean[][] visited = new boolean[N][N];
    	
    	for (int i = r; i <= r + d1 + d2; i++) {
    		int start = i <= r + d1 ? c - i + r : c - 2 * d1 + i - r;
    		int end = i <= r + d2 ? c + i - r : c + 2 * d2 - i + r;
    		for (int j = start; j <= end; j++) {
    			sum[5] += map[i][j];
    			visited[i][j] = true;
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			int num = map[i][j];
    			if (!visited[i][j]) {
    				if (i < r + d1 && j <= c) {
    					sum[1] += num;
    				} else if (i <= r + d2 && j > c) {
    					sum[2] += num;
    				} else if (i >= r + d1 && j < c - d1 + d2) {
    					sum[3] += num;
    				} else if (i > r + d2 && j >= c - d1 + d2) {
    					sum[4] += num;
    				}
    			}
    		}
    	}
    	
    	for (int i = 1; i <= 5; i++) {
    		max = Math.max(max, sum[i]);
    		min = Math.min(min, sum[i]);
    	}
    	
    	answer = Math.min(answer, max - min);
    }
}

// 1h 0m