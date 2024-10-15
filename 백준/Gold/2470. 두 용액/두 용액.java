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

		int lt = 0;
		int rt = arr.length - 1;
		int min = Integer.MAX_VALUE;
		int[] answer = new int[2];
		boolean check = false;
		while (lt < rt) {
			int abs = Math.abs(arr[lt] + arr[rt]);
			if (abs < min) {
				answer[0] = arr[lt];
				answer[1] = arr[rt];
				min = abs;
			}

			if (!check) {
				if (Math.abs(arr[lt]) <= Math.abs(arr[rt])) {
					rt--;
				} else {
					lt++;
					check = !check;
				}
			} else {
				if (Math.abs(arr[rt]) <= Math.abs(arr[lt])) {
					lt++;
				} else {
					rt--;
					check = !check;
				}
			}

		}

		System.out.println(answer[0] + " " + answer[1]);
	}

}
