import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        recur(0, 1);
        System.out.println(sb);
    }

    static void recur(int cnt, int num) {
        if (cnt == M) {
            for (int n : arr) {
                sb.append(n + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = num; i <= N; i++) {
            arr[cnt] = i;
            recur(cnt + 1, i);
        }
    }

}
