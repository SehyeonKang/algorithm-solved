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
        int mod = 0;
        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            mod = (mod + Integer.parseInt(st.nextToken())) % M;
            arr[mod]++;
        }

        long result = arr[0];
        for (int i = 0; i < M; i++) {
            result += ((long) arr[i] * (arr[i] - 1)) / 2;
        }
        System.out.println(result);
    }

}
