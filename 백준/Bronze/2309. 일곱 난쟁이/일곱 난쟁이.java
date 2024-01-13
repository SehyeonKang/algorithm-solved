import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr = new int[9];
	static List<Integer> heights = new ArrayList<>();
	static boolean flag;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		recur(0, true, 0);
		recur(0, false, 0);
		
		Collections.sort(heights);
		for (int height : heights) {
			System.out.println(height);
		}
	}
	
	static void recur(int idx, boolean check, int sum) {
		if (flag || idx > 8) {
			return;
		}
		
		if (check) {
			sum += arr[idx];
			
			if (sum < 100) {
				heights.add(arr[idx]);
			} else if (sum == 100 && heights.size() == 6) {
				heights.add(arr[idx]);
				flag = true;
				return;
			} else {
				sum -= arr[idx];
			}
		}
		
		recur(idx + 1, true, sum);
		recur(idx + 1, false, sum);
		
		if (!flag && heights.contains(arr[idx])) {
			heights.remove(Integer.valueOf(arr[idx]));
		}
	}
}