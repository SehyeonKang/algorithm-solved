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
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        List<List<Node>> adjList = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        boolean[] visited;
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Node(b, l));
            adjList.get(b).add(new Node(a, l));
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited = new boolean[n + 1];

            pq.offer(new Node(i, 0));
            dist[i] = 0;

            while (!pq.isEmpty()) {
                Node curNode = pq.poll();
                int cur = curNode.end;

                if (visited[cur])
                    continue;
                visited[cur] = true;

                for (Node node : adjList.get(cur)) {
                    if (!visited[node.end] && dist[node.end] > dist[cur] + node.weight
                    && dist[cur] + node.weight <= m) {
                        dist[node.end] = dist[cur] + node.weight;
                        pq.offer(new Node(node.end, dist[node.end]));
                    }
                }
            }

            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] < Integer.MAX_VALUE)
                    count += items[j];
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

}
