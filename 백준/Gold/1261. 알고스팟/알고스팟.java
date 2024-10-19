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
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<List<Node>> adjList = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N * M];
        boolean[] visited = new boolean[N * M];
        for (int i = 0; i < N * M; i++) {
            adjList.add(new ArrayList<>());
        }

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = M * i + j;
                if (isIn(i - 1, j, N, M)) {
                    adjList.get(num).add(new Node(num - M, map[i - 1][j]));
                }
                if (isIn(i + 1, j, N, M)) {
                    adjList.get(num).add(new Node(num + M, map[i + 1][j]));
                }
                if (isIn(i, j - 1, N, M)) {
                    adjList.get(num).add(new Node(num - 1, map[i][j - 1]));
                }
                if (isIn(i, j + 1, N, M)) {
                    adjList.get(num).add(new Node(num + 1, map[i][j + 1]));
                }
            }
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (visited[cur])
                continue;

            if (cur == N * M - 1)
                break;

            visited[cur] = true;
            for (Node node : adjList.get(cur)) {
                if (!visited[node.end] && dist[node.end] > node.weight + dist[cur]) {
                    dist[node.end] = node.weight + dist[cur];
                    pq.offer(new Node(node.end, dist[node.end]));
                }
            }
        }

        System.out.println(dist[N * M - 1]);

    }

    private static boolean isIn(int r, int c, int N, int M) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}
