import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> mat  = new ArrayList<>();
    static List<Integer> dfsResults = new ArrayList<>();
    static List<Integer> bfsResults = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            mat.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            mat.get(from).add(to);
            mat.get(to).add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(mat.get(i));
        }

        dfsResults.add(V);
        visited[V] = true;
        dfs(V);
        bfsResults.add(V);
        visited = new boolean[N + 1];
        visited[V] = true;
        bfs(V);

        for (int dfsResult : dfsResults) {
            sb.append(dfsResult + " ");
        }
        sb.append("\n");

        for (int bfsResult : bfsResults) {
            sb.append(bfsResult + " ");
        }

        System.out.println(sb);
    }

    static void dfs(int from) {
        for (int to : mat.get(from)) {
            if (!visited[to]) {
                visited[to] = true;
                dfsResults.add(to);
                dfs(to);
            }
        }
    }

    static void bfs(int v) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(v);

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int cur = queue.poll();

                for (int to : mat.get(cur)) {
                    if (!visited[to]) {
                        bfsResults.add(to);
                        visited[to] = true;
                        queue.add(to);
                    }
                }
            }
        }
    }
}
