import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

       int[] dp = new int[10001];
       dp[1] = 1;
       dp[2] = 2;
       dp[3] = 3;
       for (int i = 4; i <= 10000; i++){
           dp[i] = dp[i - 1] + dp[i - 2] - dp[i - 3];
           if (i % 3 == 0) {
               dp[i]++;
           }
       }

       int tc = Integer.parseInt(br.readLine());
       for (int i = 0; i < tc; i++){
           sb.append(dp[Integer.parseInt(br.readLine())] + "\n");
       }
        System.out.println(sb);
    }
}
