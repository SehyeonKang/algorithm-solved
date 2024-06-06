import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] counts = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            counts[arr[i]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int num = q.poll();
            counts[arr[num]]--;
            if (counts[arr[num]] == 0) {
                q.offer(arr[num]);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (counts[i] > 0) {
                q.offer(i);
            }
        }

        System.out.println(q.size());
        for (int n : q) {
            System.out.println(n);
        }
    }
}
