import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		boolean[] check = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				answer += binarySearch(arr, check, i, j);
			}
		}
		
		System.out.println(answer);
	}
	
	static int binarySearch(int[] arr, boolean[] check, int num1, int num2) {
		int count = 0;
		int findNum = arr[num1] + arr[num2];
		int lt = 0;
		int rt = arr.length - 1;
		
		while (lt <= rt) {
			int mid = (lt + rt) / 2;
			
			if (arr[mid] < findNum) {
				lt = mid + 1;
			} else if (arr[mid] > findNum) {
				rt = mid - 1;
			} else {
				if (((lt + rt) / 2) != num1 && ((lt + rt) / 2) != num2 && !check[(lt + rt) / 2]) {
					check[(lt + rt) / 2] = true;
					count = 1;
				}
				
				for (int i = (lt + rt) / 2 - 1; i >= 0; i--) {
					if (arr[i] == findNum && i != num1 && i != num2 && !check[i]) {
						check[i] = true;
						count++;
					}
				}
				
				for (int i = (lt + rt) / 2 + 1; i < arr.length; i++) {
					if (arr[i] == findNum && i != num1 && i != num2 && !check[i]) {
						check[i] = true;
						count++;
					}
				}
				break;
			}
		}
		
		
		return count;
	}
}