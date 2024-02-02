import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int nCheck = 0;
	static String answer = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= 10; i++) {
			int[] numbers = new int[i];
			recur(numbers, 0, i);
		}
		
		if (answer.length() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void recur(int[] numbers, int count, int length) {
		if (answer.length() > 0) {
			return;
		}
		
		if (count == length) {
			if (nCheck == N) {
				for (int i = 0; i < numbers.length; i++) {
					answer += numbers[i];
				}
			}
			nCheck++;
			return;
		}
		
		if (count == 0) {
			for (int i = length - 1; i < 10; i++) {
				numbers[count] = i;
				recur(numbers, count + 1, length);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				if (numbers[count - 1] > i) {
					numbers[count] = i;
					recur(numbers, count + 1, length);
				}
			}
		}
	}
}