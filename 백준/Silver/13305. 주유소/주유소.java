
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long [] distance = new long[N - 1];
        long[] cost = new long[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        long sum = distance[0] * cost[0];
        long temp = cost[0];

        for (int i = 1; i < N - 1; i++) {
            if (temp > cost[i])
                temp = cost[i];

            sum += distance[i] * temp;
        }

        System.out.println(sum);
    }
}