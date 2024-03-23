import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, L, K;
	
	private static class Star implements Comparable<Star>{
		int r, c;

		public Star(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Star o) {
			if (this.r == o.r) {
				return this.c - o.c; 
			}
			return this.r - o.r;
		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Star[] stars = new Star[K];
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	stars[i] = new Star(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }
        Arrays.sort(stars);

        int max = 0;
        for (Star starA : stars) {
        	for (Star starB : stars) {
        		int r = starA.r;
        		int c = starB.c;
        		int cnt = 0;
        		
        		for (Star star : stars) {
        			if (star.r > r + L && star.c > c + L) {
        				break;
        			}
        			if (isIn(r, c, star.r, star.c)) {
        				cnt++;
        			}
        		}
        		max = Math.max(max, cnt);
        	}
        }
        
        System.out.println(K - max);
    }
    
    private static boolean isIn(int r, int c, int starR, int starC) {
    	return starR >= r && starR <= r + L && starC >= c && starC <= c + L;
    }
}