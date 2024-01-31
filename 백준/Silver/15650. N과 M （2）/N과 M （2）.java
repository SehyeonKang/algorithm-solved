
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visit = new boolean[N];

        dfs(0);

        System.out.println(sb);
    }

    private static void dfs(int depth) {

        if (depth == M) {
            for (int num : arr) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                if (depth == 0) {
                    arr[depth] = i + 1;
                    visit[i] = true;
                    dfs(depth + 1);
                    visit[i] = false;

                } else if (arr[depth - 1] < i + 1) {
                    arr[depth] = i + 1;
                    visit[i] = true;
                    dfs(depth + 1);
                    visit[i] = false;
                }
            }
        }
    }
}