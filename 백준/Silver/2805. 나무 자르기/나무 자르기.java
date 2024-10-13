import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int lt = 0;
        int rt = 0;
        int mid = 0;
        int[] tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            rt = Math.max(rt, tree[i]);
        }

        while (lt < rt) {
            mid = (lt + rt) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) {
                    sum += tree[i] - mid;
                }
            }

            if (sum < M) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        System.out.println(lt - 1);
    }

}
