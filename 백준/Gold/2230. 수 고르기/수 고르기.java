import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        int lt = 0;
        int rt = 0;
        while (rt < N) {
            int gap = arr[rt] - arr[lt];

            if (gap >= M) {
                answer = Math.min(answer, gap);
                lt++;
            } else {
                rt++;
            }
        }

        System.out.println(answer);
    }
}
