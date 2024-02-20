import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] in;
	static ArrayList<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        in = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int singerNum = Integer.parseInt(st.nextToken());
        	int[] singers = new int[singerNum];
        	for (int j = 0; j < singerNum; j++) {
        		singers[j] = Integer.parseInt(st.nextToken());
        	}
        	
        	for (int j = 0; j < singerNum - 1; j++) {
        		for (int k = j + 1; k < singerNum; k++) {
        			list[singers[j]].add(singers[k]);
        			in[singers[k]]++;
        		}
        	}
        }
        
        solve();
        
        System.out.println(sb);
    }
    
    private static void solve() {
    	Queue<Integer> q = new ArrayDeque<>();
    	for (int i = 1; i <= N; i++) {
    		if (in[i] == 0) {
    			q.offer(i);
    		}
    	}
    	
    	while (!q.isEmpty()) {
    		int start = q.poll();
    		sb.append(start + "\n");
    		
    		for (int i = 0, size = list[start].size(); i < size; i++) {
    			int end = list[start].get(i);
    			in[end]--;
    			if (in[end] == 0) {
    				q.offer(end);
    			}
    		}
    	}
    	
    	for (int i = 1; i <= N; i++) {
    		if (in[i] > 0) {
    			sb = new StringBuilder();
    			sb.append(0);
    		}
    	}
    }
}