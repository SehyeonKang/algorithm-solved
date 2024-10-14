import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int lt = 0;
		int rt = L;
		while (lt < rt) {
			int mid = (lt + rt) / 2;

			if (mid == 0)
				break;

			int pre = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += (arr[i] - pre) / mid;
				if ((arr[i] - pre) % mid == 0)
					sum--;
				pre = arr[i];

			}
			sum += (L - pre) / mid;
			if ((L - pre) % mid == 0)
				sum--;

			if (sum > M) {
				lt = mid + 1;
			} else {
				rt = mid;
			}
		}

		System.out.println(rt);
	}

}
