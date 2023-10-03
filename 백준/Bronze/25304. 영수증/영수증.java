import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int sum = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        int check = 0;
        int price, num;

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            price = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            check += price * num;
        }

        if (sum == check) {
            System.out.println("Yes");
        } else
            System.out.println("No");
    }
}