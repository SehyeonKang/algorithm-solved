import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int end;
        long weight;

        public Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
        long[] minDistance = new long[N + 1];
        boolean[] visited;

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Node(b, i));
            adjList.get(b).add(new Node(a, i));
        }

        Arrays.fill(minDistance, Long.MAX_VALUE);
        minDistance[1] = 0;
        visited = new boolean[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
            long curWeight = curNode.weight;

            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;

            for (Node node : adjList.get(cur)) {
                if (visited[node.end]) {
                    continue;
                }
                long weight = node.weight;
                long nextWeight = curWeight + ((weight - curWeight) % M + M) % M + 1;

                if (minDistance[node.end] > nextWeight) {
                    minDistance[node.end] = nextWeight;
                    pq.offer(new Node(node.end, nextWeight));
                }
            }
        }

        System.out.println(minDistance[N]);
    }

}
