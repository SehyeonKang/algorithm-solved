import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 컵의 위치를 맞바꾸기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int cup1 = Integer.parseInt(st.nextToken());
			int cup2 = Integer.parseInt(st.nextToken());
			
			if (X == cup1) {
				X = cup2;
			} else if (X == cup2) {
				X = cup1;
			}
		}
		
		System.out.println(X);
	}
}