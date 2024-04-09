import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int lt = 0;
		int rt = N - 1;
		int minNum = Integer.MIN_VALUE;
		int maxNum = Integer.MAX_VALUE;
		int value = Integer.MAX_VALUE;
		while (lt < rt) {
			int sum = Math.abs(arr[lt] + arr[rt]);
			if (sum < value) {
				value = sum;
				minNum = arr[lt];
				maxNum = arr[rt];
				if (value == 0) {
					break;
				}
			}
			if (Math.abs(arr[lt]) > Math.abs(arr[rt])) {
				lt++;
			} else {
				rt--;
			}
		}
		System.out.println(minNum + " " + maxNum);
	}
}
