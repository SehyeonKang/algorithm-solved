
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i < 2 * n; i++) {
            if (i < n) {
                for (int j = 0; j < n - i; j++) {
                    sb.append(" ");
                }
                for (int j = 0; j < 2 * i - 1; j++) {
                    sb.append("*");
                }
                sb.append("\n");
            } else if (i > n) {
                for (int j = 0; j < i - n; j++) {
                    sb.append(" ");
                }
                for (int j = 0; j < 2 * (2 * n - i) - 1; j++) {
                    sb.append("*");
                }
                sb.append("\n");
            } else {
                for (int j = 0; j < 2 * n - 1; j++) {
                    sb.append("*");
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}