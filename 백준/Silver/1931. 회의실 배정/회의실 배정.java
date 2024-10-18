import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int start, end;

        public Node (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.end == o.end) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int currentTime = 0;
        int answer = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.start >= currentTime) {
                answer++;
                currentTime = node.end;
            }
        }

        System.out.println(answer);
    }

}
