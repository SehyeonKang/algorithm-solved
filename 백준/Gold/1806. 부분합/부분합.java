import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int lt = 0;
		int rt = 1;
		int partSum = arr[0];
		int answer = Integer.MAX_VALUE;
		while (rt < N) {
			if (partSum == S) {
				answer = Math.min(answer, rt - lt);
				partSum += arr[rt];
				rt++;
			} else if (partSum > S) {
				answer = Math.min(answer, rt - lt);
				partSum -= arr[lt];
				lt++;
			} else {
				partSum += arr[rt];
				rt++;
			}
		}

		while (lt < N) {
			if (partSum >= S) {
				answer = Math.min(answer, rt - lt);
			}
			partSum -= arr[lt++];
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}

}
