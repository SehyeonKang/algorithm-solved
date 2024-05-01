import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Node>> adjList;
    static int[] minDistance;
    static boolean[] visited;
    static class Node implements Comparable<Node> {
        int to, cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        adjList = new ArrayList<>();
        minDistance = new int[n + 1];
        int[] results = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        // 인접리스트 생성
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            adjList.get(from).add(new Node(to, cost));
            adjList.get(to).add(new Node(from, cost));
        }
        
        // 각 만남지점을 경유할 때의 최단 거리 구하기
        for (int meetNode = 1; meetNode <= n; meetNode++) {
            int aCost = dijkstra(a, meetNode, n);
            int bCost = dijkstra(b, meetNode, n);
            int shareCost = dijkstra(meetNode, s, n);
            results[meetNode] = aCost + bCost + shareCost;
        }     
        Arrays.sort(results);  
        return results[1];
    }
    
    private static int dijkstra(int start, int end, int n) {
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        minDistance[start] = 0;
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.to;

            if (visited[cur]) {
                continue;
            }

            // 목표 지점에 도착했을 경우
            if (cur == end) {
                break;
            }

            visited[cur] = true;
            for (Node node : adjList.get(cur)) {
                if (!visited[node.to] && minDistance[node.to] > minDistance[cur] + node.cost) {
                    minDistance[node.to] = minDistance[cur] + node.cost;

                    pq.offer(new Node(node.to, minDistance[node.to]));
                }
            }
        }
        
        return minDistance[end];
    }
}