import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static String S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.println(0);
    }

    private static void dfs(String s) {
        if (S.length() == s.length()) {
            if (S.equals(s)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (s.charAt(0) == 'B') {
            dfs(new StringBuilder(s.substring(1)).reverse().toString());
        }
        if (s.charAt(s.length() - 1) == 'A') {
            dfs(s.substring(0, s.length() - 1));
        }
    }
}
