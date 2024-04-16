import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		if (N == 1) {
			System.out.println("A");
		} else if (N == 2) {
			int num1 = arr[0];
			int num2 = arr[1];
			
			if (num1 == num2) {
				System.out.println(num1);
				return;
			}
			
			System.out.println("A");
		} else {
			int num1 = arr[0];
			int num2 = arr[1];
			int num3 = arr[2];
			
			int a1 = num1 - num2;
			int a2 = num2 - num3;
			
			if (a1 == 0) {
				if (a2 == 0) {
					for (int i = 3; i < N; i++) {
						if (num1 != arr[i]) {
							System.out.println("B");
							return;
						}
					}
					System.out.println(num1);
					return;
				} else {
					System.out.println("B");
					return;
				}
			}
			
			if ((Math.abs(a1) > Math.abs(a2) && a2 != 0) || Math.abs(a2) % Math.abs(a1) != 0) {
				System.out.println("B");
				return;
			}
			
			int a = a2 / a1;
			int b = num2 - num1 * a;
			
			for (int i = 2; i < N - 1; i++) {
				if (arr[i] * a + b != arr[i + 1]) {
					System.out.println("B");
					return;
				}
			}
			
			int result = arr[N - 1] * a + b;
			System.out.println(result);
		}
		
	}
}
