import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int[] arrA = new int[5];
			int[] arrB = new int[5];
			
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				arrA[num]++;
			}
			
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				arrB[num]++;
			}
			
			if (arrA[4] != arrB[4]) {
				if (arrA[4] > arrB[4]) {
					System.out.println("A");
				} else {
					System.out.println("B");
				}
			} else if (arrA[3] != arrB[3]) {
				if (arrA[3] > arrB[3]) {
					System.out.println("A");
				} else {
					System.out.println("B");
				}
			} else if (arrA[2] != arrB[2]) {
				if (arrA[2] > arrB[2]) {
					System.out.println("A");
				} else {
					System.out.println("B");
				}
			} else if (arrA[1] != arrB[1]) {
				if (arrA[1] > arrB[1]) {
					System.out.println("A");
				} else {
					System.out.println("B");
				}
			} else {
				System.out.println("D");
			}
		}
	}
}