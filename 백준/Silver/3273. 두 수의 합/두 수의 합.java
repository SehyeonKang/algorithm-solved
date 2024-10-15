import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int x = Integer.parseInt(br.readLine());

		int answer = 0;
		int lt = 0;
		int rt = arr.length - 1;
		while (lt < rt) {
			int sum = arr[lt] + arr[rt];
			if (sum == x) {
				answer++;
				rt--;
			} else if (sum < x) {
				lt++;
			} else {
				rt--;
			}
		}

		System.out.println(answer);

	}

}
