import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String answer = "";
		char[][] words = new char[5][];
		int max = 0;
		for (int i = 0; i < 5; i++) {
			String word = br.readLine();
			words[i] = new char[word.length()];
			max = Math.max(max, word.length());
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				words[i][j] = c;
			}
		}
		
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < 5; j++) {
				if (words[j].length > i) {
					answer += words[j][i];
				}
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}