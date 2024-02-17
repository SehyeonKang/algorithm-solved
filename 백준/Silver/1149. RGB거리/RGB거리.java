
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        int result = Math.min(paint(N - 1, RED), Math.min(paint(N - 1, GREEN), paint(N - 1, BLUE)));
        System.out.println(result);

    }

    private static int paint(int n, int color) {

        if (dp[n][color] == 0) {
            if (color == RED) {
                return dp[n][RED] = Math.min(paint(n - 1, GREEN), paint(n - 1, BLUE)) + cost[n][RED];
            } else if (color == GREEN) {
                return dp[n][GREEN] += Math.min(paint(n - 1, RED), paint(n - 1, BLUE)) + cost[n][GREEN];
            } else {
                return dp[n][BLUE] += Math.min(paint(n - 1, RED), paint(n - 1, GREEN)) + cost[n][BLUE];
            }
        }

        return dp[n][color];
    }
}