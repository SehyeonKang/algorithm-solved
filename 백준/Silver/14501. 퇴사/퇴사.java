import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int maxBenefit = 0;
	static int N;
	static Consult[] consults;
	
	private static class Consult {
		int t, p;

		public Consult(int t, int p) {
			this.t = t;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		consults = new Consult[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			consults[i] = new Consult(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		recur(1, 0);
		System.out.println(maxBenefit);
	}
	
	private static void recur(int currentDay, int money) {
		if (currentDay > N) {
			maxBenefit = Math.max(maxBenefit, money);
			return;
		}
		
		if (currentDay + consults[currentDay].t - 1 <= N) {
			recur(currentDay + consults[currentDay].t, money + consults[currentDay].p);
		}
		recur(currentDay + 1, money);
	}
	
}