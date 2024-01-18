import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String s = br.readLine();
		int[] arr = new int[s.length()];
		int idx = 0;
		for (char c : s.toCharArray()) {
			arr[idx++] = Character.getNumericValue(c);
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			int max = 0;
			int maxIdx = -1;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] > max) {
					max = arr[j];
					maxIdx = j;
				}
			}
			
			if (maxIdx > -1 && maxIdx != i) {
				int tmp = arr[i];
				arr[i] = arr[maxIdx];
				arr[maxIdx] = tmp;
			}
		}
		
		for (int num : arr) {
			sb.append(num);
		}
		
		System.out.println(sb.toString());
	}
}