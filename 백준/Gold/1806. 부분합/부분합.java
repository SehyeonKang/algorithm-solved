import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 0;
        int rt = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        while (rt <= N) {
            if (sum >= S) {
                answer = Math.min(answer, rt - lt);
                lt++;
                sum -= arr[lt];
                continue;
            }
            rt++;
            if (rt == N + 1) {
                break;
            }
            sum += arr[rt];
        }

        if (sum >= S) {
            answer = Math.min(answer, rt - lt);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
