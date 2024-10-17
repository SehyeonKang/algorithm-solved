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
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int lt = 0;
        int rt = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[rt++];
        }
        max = Math.max(max, sum);

        while (rt < N) {
            sum += arr[rt++] - arr[lt++];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

}
