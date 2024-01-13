import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String answer = "";
		String S = br.readLine();
		boolean tagFlag = false;
		String word = "";
		
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			
			if (tagFlag) {
				if (c == '>') {
					tagFlag = false;
				}
				answer += c;
				continue;
			}
			
			if (c == '<') {
				if (word.length() > 0) {
					answer += reverseString(word);
					word = "";
				}
				tagFlag = true;
				answer += c;
				continue;
			}
			
			if (c == ' ') {
				answer += reverseString(word) + c;
				word = "";
			} else {
				word += c;
			}
		}
		
		if (word.length() > 0) {
			answer += reverseString(word);
		}
		
		System.out.println(answer);
	}
	
	static String reverseString(String s) {
		StringBuffer sb = new StringBuffer(s);
		return sb.reverse().toString();
	}
}