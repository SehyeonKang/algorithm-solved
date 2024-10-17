import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] partSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (i == 0 && j == 0) {
                    partSum[i][j] = num;
                } else if (i == 0) {
                    partSum[i][j] = partSum[i][j - 1] + num;
                } else if (j == 0) {
                    partSum[i][j] = partSum[i - 1][j] + num;
                } else {
                    partSum[i][j] = partSum[i - 1][j] + partSum[i][j - 1] - partSum[i - 1][j - 1] + num;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int sum = 0;
            if (x1 == 0 && y1 == 0) {
                sum = partSum[x2][y2];
            } else if (x1 == 0) {
                sum = partSum[x2][y2] - partSum[x2][y1 - 1];
            } else if (y1 == 0) {
                sum = partSum[x2][y2] - partSum[x1 - 1][y2];
            } else {
                sum = partSum[x2][y2] - partSum[x2][y1 - 1] - partSum[x1 - 1][y2] + partSum[x1 - 1][y1 - 1];
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

}
