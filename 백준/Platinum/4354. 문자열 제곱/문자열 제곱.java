import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int[] failure;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String s = br.readLine();
			if (s.equals(".")) {
				break;
			}
			getFailure(s);
		}
	}
	
	private static void getFailure(String s) {
		int j = 0;
		int idxCheck = 0;
		failure = new int[s.length()];
		
		for (int i = 1; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = failure[j - 1];
				idxCheck = 0;
			}
			if (s.charAt(i) == s.charAt(j)) {
				failure[i] = ++j;
				if (idxCheck == 0) {
					idxCheck = i;
				}
			}
		}
		
		if (idxCheck > 0 && s.length() % idxCheck == 0) {
			System.out.println(s.length() / idxCheck);
		} else {
			System.out.println(1);
		}
	}
}