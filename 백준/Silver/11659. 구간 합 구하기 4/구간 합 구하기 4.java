import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] partSum = new int[N];
        st = new StringTokenizer(br.readLine());
        partSum[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            partSum[i] = partSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken()) - 1;
            int J = Integer.parseInt(st.nextToken()) - 1;

            if (I == 0) {
                sb.append(partSum[J]).append("\n");
            } else {
                sb.append(partSum[J] - partSum[I - 1]).append("\n");
            }
        }

        System.out.println(sb);
    }

}
