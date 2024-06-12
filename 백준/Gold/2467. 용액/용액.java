import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer1 = 0, answer2 = 0;
        int lt = 0;
        int rt = N - 1;
        int gap = Integer.MAX_VALUE;
        while (lt < rt) {
            int tmp = Math.abs(arr[lt] + arr[rt]);
            if (tmp < gap) {
                gap = tmp;
                answer1 = arr[lt];
                answer2 = arr[rt];
                if (gap == 0) {
                    break;
                }
            }

            if (Math.abs(arr[lt]) < Math.abs(arr[rt])) {
                rt--;
            } else {
                lt++;
            }
        }

        System.out.println(answer1 + " " + answer2);
    }
}
