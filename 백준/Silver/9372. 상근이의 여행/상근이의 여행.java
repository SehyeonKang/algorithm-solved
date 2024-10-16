import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Vertex implements Comparable<Vertex> {
        int no, weight;

        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] mat = new int[N][N];
            boolean[] visited = new boolean[N];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                mat[a][b] = 1;
                mat[b][a] = 1;
            }

            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            int result = 0;
            int cnt = 0;
            pq.offer(new Vertex(0, 0));
            while (!pq.isEmpty()) {
                Vertex v = pq.poll();

                if (visited[v.no]) {
                    continue;
                }

                result += v.weight;
                visited[v.no] = true;

                if (++cnt == N)
                    break;

                for (int i = 0; i < N; i++) {
                    if (mat[v.no][i] == 1 && !visited[i]) {
                        pq.offer(new Vertex(i, mat[v.no][i]));
                    }
                }
            }

            sb.append(result + "\n");
        }

        System.out.println(sb);
    }

}
