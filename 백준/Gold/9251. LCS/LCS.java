import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] s1, s2;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();

        dp = new Integer[s1.length][s2.length];

        System.out.println(recur(s1.length - 1, s2.length - 1));

    }

    private static int recur(int x, int y) {

        if (x == -1 || y == -1)
            return 0;

        if (dp[x][y] == null) {
            if (s1[x] == s2[y]) {
                dp[x][y] = recur(x - 1, y - 1) + 1;
            } else
                dp[x][y] = Math.max(recur(x - 1, y), recur(x, y - 1));
        }

        return dp[x][y];
    }
}