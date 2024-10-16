import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.weight, node.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
        int[] minDistance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Node(v, w));
        }

        Arrays.fill(minDistance, Integer.MAX_VALUE);
        minDistance[S] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (visited[cur])
                continue;

            visited[cur] = true;
            for (Node node : adjList.get(cur)) {
                if (!visited[node.end] && minDistance[node.end] > minDistance[cur] + node.weight) {
                    minDistance[node.end] = minDistance[cur] + node.weight;
                    pq.offer(new Node(node.end, minDistance[node.end]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (minDistance[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(minDistance[i] + "\n");
            }
        }

        System.out.println(sb);

    }

}
