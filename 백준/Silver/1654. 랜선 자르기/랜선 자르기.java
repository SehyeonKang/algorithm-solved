import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long lt = 0;
        long rt = 0;
        int[] arr = new int[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            rt = Math.max(rt, arr[i]);
        }

        while (lt < rt) {
            long mid = (lt + rt) / 2;
            if (mid == 0)
                mid++;
            
            long sum = 0;
            for (int i = 0; i < K; i++) {
                sum += arr[i] / mid;
            }

            if (sum < N) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        long sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i] / lt;
        }

        if (sum >= N) {
            System.out.println(lt);
        } else {
            System.out.println(lt - 1);
        }
    }

}
