import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] isSelected;
	static int[] numbers;
	static int[] inputs;
	static int N;
	static int M;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		isSelected = new boolean[9];
		numbers = new int[7];
		inputs = new int[9];
		for (int i = 0; i < 9; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		
		recur(0, 0);
	}
	
	static void recur(int count, int start) {
		if (flag) {
			return;
		}
		
		if (count == 7) {
			int sum = 0;
			for (int number : numbers) {
				sum += inputs[number];
			}
			
			if (sum == 100) {
				for (int number : numbers) {
					System.out.println(inputs[number]);
				}
			}
			
			return;
		}
		
		for (int i = start; i < 9; i++) {
			numbers[count] = i;
			recur(count + 1, i + 1);
		}
	}
}