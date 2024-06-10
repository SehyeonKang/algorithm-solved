import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());
            recur(1, N, new char[N]);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void recur(int cnt, int N, char[] arr) {
        if (cnt == N) {
            calculate(N, arr);
            return;
        }

        arr[cnt] = ' ';
        recur(cnt + 1, N, arr);
        arr[cnt] = '+';
        recur(cnt + 1, N, arr);
        arr[cnt] = '-';
        recur(cnt + 1, N, arr);
    }

    private static void calculate(int N, char[] arr) {
        int result = 0;
        int tmp = 0;
        for (int i = N - 1; i > 0; i--) {
            char operator = arr[i];
            if (operator == ' ') {
                if (tmp == 0) {
                    tmp = 10 * i + i + 1;
                } else {
                    tmp += (int)Math.pow(10, String.valueOf(tmp).length()) * i;
                }
            } else if (operator == '+') {
                if (tmp > 0) {
                    result += tmp;
                    tmp = 0;
                } else {
                    result += i + 1;
                }
            } else {
                if (tmp > 0) {
                    result -= tmp;
                    tmp = 0;
                } else {
                    result -= i + 1;
                }
            }
        }

        if (tmp > 0) {
            result += tmp;
        } else {
            result += 1;
        }

        if (result == 0) {
            for (int i = 1; i < N; i++) {
                sb.append(String.valueOf(i) + arr[i]);
            }
            sb.append(N + "\n");
        }
    }
}
