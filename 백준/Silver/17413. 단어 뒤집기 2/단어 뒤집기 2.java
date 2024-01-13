import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), "<> ", true);
		
		boolean tagFlag = false;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.equals("<")) {
				tagFlag = true;
				sb.append("<");
			} else if (s.equals(">")) {
				tagFlag = false;
				sb.append(">");
			} else {
				if (!tagFlag) {
					StringBuilder reverseSb = new StringBuilder(s);
					sb.append(reverseSb.reverse().toString());
				} else {
					sb.append(s);
				}
			}
		}
		
		System.out.println(sb);
	}
}