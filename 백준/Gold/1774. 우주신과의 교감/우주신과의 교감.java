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
	
	static int N, M;
	static List<Edge> edgeList;
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        Point[] points = new Point[N + 1];
        
        // 간선 리스트 생성
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	long x1 = (long) points[i].x;
        	long y1 = (long) points[i].y;
        	
        	for (int j = i - 1; j > 0; j--) {
        		long x2 = (long) points[j].x;
        		long y2 = (long) points[j].y;
        		double weight = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
        		edgeList.add(new Edge(i, j, weight));
        		edgeList.add(new Edge(j, i, weight));
        	}
        }
        
        Collections.sort(edgeList);
        makeSet();
        
        // 이미 연결된 통로 생성
        int connectedCnt = 0;
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
        		connectedCnt++;
        	}
        }

        double weight = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
        	// 사이클이 발생했을 경우
        	if (!union(edge.from, edge.to)) {
        		continue;
        	}
        	cnt++;
        	weight += edge.weight;
        	// 최소 신장 트리가 만들어졌을 경우
        	if (cnt == N - connectedCnt - 1) {
        		break;
        	}
        }
        
        System.out.println(String.format("%.2f", weight));
    }
    
    private static void makeSet() {
    	parents = new int[N + 1];
    	for (int i = 0; i <= N; i++) {
    		parents[i] = i;
    	}
    }
    
    private static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	if (aRoot == bRoot) {
    		return false;
    	}
    	parents[bRoot] = aRoot;
    	return true;
    }
    
    private static int find(int a) {
    	if(a == parents[a]) {
    		return a;
    	}
    	return parents[a] = find(parents[a]);
    }
}