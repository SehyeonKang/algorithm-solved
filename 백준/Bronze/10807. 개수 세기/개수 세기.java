import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());
        int[] arr = new int[201];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[n + 100]++;
        }

        int num = Integer.parseInt(br.readLine());
        System.out.println(arr[num + 100]);
    }
}