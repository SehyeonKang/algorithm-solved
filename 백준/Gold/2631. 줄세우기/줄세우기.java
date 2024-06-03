import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        memo = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int len = 0;
        for (int i = 0; i < N; i++) {
            if (memo[len] < arr[i]) {
                memo[++len] = arr[i];
            } else {
                int idx = binarySearch(0, len, arr[i]);
                memo[idx] = arr[i];
            }
        }

        System.out.println(N - len);
    }

    private static int binarySearch(int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (memo[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

}
