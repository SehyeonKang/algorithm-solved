import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] arr = new int[30];
        int n;
        
        for (int i = 0; i < 28; i++) {
            n = Integer.parseInt(br.readLine());
            arr[n - 1]++;
        }

        for (int i = 0; i < 30; i++) {
            if (arr[i] == 0) {
                System.out.println(i + 1);
            }
        }
    }
}