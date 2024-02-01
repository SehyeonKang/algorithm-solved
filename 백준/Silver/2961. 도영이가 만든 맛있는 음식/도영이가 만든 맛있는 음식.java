import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static boolean[] isSelected;
	static Item[] items;
	static int answer = Integer.MAX_VALUE;
	
	static class Item {
		int num1;
		int num2;
		
		Item(int num1, int num2) {
			this.num1 = num1;
			this.num2 = num2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		items = new Item[N];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		recur(0);
		System.out.println(answer);
	}
	
	private static void recur(int count) {
		if (count == N) {
			int sum1 = 1;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sum1 *= items[i].num1;
					sum2 += items[i].num2;
				}
			}
			if (sum2 > 0) {
				answer = Math.min(answer, Math.abs(sum1 - sum2));
			}
			
			return;
		}
		
		isSelected[count] = true;
		recur(count + 1);
		isSelected[count] = false;
		recur(count + 1);
	}
}