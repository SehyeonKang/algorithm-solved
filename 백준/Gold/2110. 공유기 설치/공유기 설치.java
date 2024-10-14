import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] houses = new int[N];
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houses);

		int lt = 0;
		int rt = houses[N - 1] - houses[0];

		while (lt < rt) {
			int mid = (lt + rt) / 2;

			int pre = houses[0];
			int sum = 1;
			for (int i = 0; i < N; i++) {
				if (houses[i] - pre >= mid) {
					pre = houses[i];
					sum++;
				}
			}

			if (sum >= C) {
				lt = mid + 1;
			} else {
				rt = mid;
			}
		}

		if (C == 2) {
			rt++;
		}

		System.out.println(rt - 1);
	}

}
