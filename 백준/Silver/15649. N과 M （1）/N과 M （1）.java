import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static boolean[] isSelected;
	static int[] numbers;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isSelected = new boolean[N + 1];
		numbers = new int[M];
		
		recur(0);
		
		System.out.println(sb);
		
	}
	
	static void recur(int count) {
		if (count == M) {
			for (int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			isSelected[i] = true;
			numbers[count] = i;
			recur(count + 1);
			isSelected[i] = false;
		}
	}
}