import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
        int[] minDistance = new int[N + 1];
        boolean[]  visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, weight));
            adjList.get(to).add(new Node(from, weight));
        }

        Arrays.fill(minDistance, Integer.MAX_VALUE);
        minDistance[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (cur == N) {
                break;
            }

            if (!visited[cur]) {
                visited[cur] = true;

                for (Node node : adjList.get(cur)) {
                    if (!visited[node.end] && minDistance[node.end] > minDistance[cur] + node.weight) {
                        minDistance[node.end] = minDistance[cur] + node.weight;
                        pq.offer(new Node(node.end, minDistance[node.end]));
                    }
                }
            }
        }

        System.out.println(minDistance[N]);
    }
}
