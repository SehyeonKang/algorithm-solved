
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] W, V;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];
        dp = new Integer[N][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recur(N - 1, K));

    }

    private static int recur(int n, int k) {

        if (n < 0)
            return 0;

        if (dp[n][k] == null) {
            if (k < W[n]) {
                dp[n][k] = recur(n - 1, k);
            } else {
                dp[n][k] = Math.max(recur(n - 1, k), recur(n - 1, k - W[n]) + V[n]);
            }
        }

        return dp[n][k];
    }
}