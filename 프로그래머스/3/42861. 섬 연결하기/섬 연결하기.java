import java.util.*;

class Solution {
    
    static int[] parents;
    
    static class Edge implements Comparable<Edge> {
        int a, b, cost;
        
        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Edge[] edgeList = new Edge[costs.length];
        
        int idx = 0;
        for (int[] cost : costs) {
            edgeList[idx++] = new Edge(cost[0], cost[1], cost[2]);    
        }   
        
        makeSet(n);
        Arrays.sort(edgeList);
        
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (!union(edge.a, edge.b)) {
                continue;
            }
            
            cnt++;
            answer += edge.cost;
            
            if (cnt == n)
                break;
        }
        
        return answer;
    }
    
    private void makeSet(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }
    
    private boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        parents[rootA] = rootB;
        return true;
    }
    
    private int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}