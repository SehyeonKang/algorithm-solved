import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] ceil = new int[N / 2];
        int[] floor = new int[N / 2];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                floor[i / 2] = Integer.parseInt(br.readLine());
            } else {
                ceil[i / 2] = Integer.parseInt(br.readLine());
            }
        }

        Arrays.sort(floor);
        Arrays.sort(ceil);

        int min = N;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int conflict = binarySearch(0, N / 2, i, floor)
                    + binarySearch(0, N / 2, H - i + 1, ceil);

            if (conflict == min) {
                cnt++;
            }

            if (conflict < min) {
                min = conflict;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }

    static int binarySearch(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return arr.length - left;
    }

}
