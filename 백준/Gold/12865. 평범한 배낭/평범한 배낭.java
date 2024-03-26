import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Item {
		int w, v;

		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 1; i <= N; i++) {
        	for (int j = 1; j <= K; j++) {
        		if (j >= items[i].w) {
        			dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i].w] + items[i].v);
        		} else {
        			dp[i][j] = dp[i - 1][j];
        		}
        	}
        }
        
        System.out.println(dp[N][K]);
    }
}