import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer = Integer.MIN_VALUE;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		recur(arr, arr[0] - '0', 1);
		
		System.out.println(answer);
	}
	
	static void recur(char[] arr, int sum, int idx) {
		if (idx == N) {
			answer = Math.max(sum, answer);
			return;
		}
		
		char c = arr[idx];
		int num = arr[idx + 1] - '0';
		if (idx + 2 < N) {
			char c2 = arr[idx + 2];
			int num2 = arr[idx + 3] - '0';
			
			recur(arr, calculate2(sum, c, num, c2, num2), idx + 4);
		}
		recur(arr, calculate1(sum, c, num), idx + 2);
	}
	
	static int calculate1(int num1, char c, int num2) {
		if (c == '+') {
			return num1 + num2;
		} else if (c == '-') {
			return num1 - num2;
		} else {
			return num1 * num2;
		}
	}
	
	static int calculate2(int sum, char c1, int num1, char c2, int num2) {
		return calculate1(sum, c1, calculate1(num1, c2, num2));
	}
}