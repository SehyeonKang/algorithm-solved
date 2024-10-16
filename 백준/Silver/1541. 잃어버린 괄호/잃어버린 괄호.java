import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String s = br.readLine();
		int answer = 0;
		boolean isMinus = false;
		boolean isFirst = true;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-') {
				if (isFirst) {
					answer += Integer.parseInt(sb.toString());
					sb = new StringBuilder();
					isFirst = false;
					isMinus = true;
					continue;
				} else {
					if (isMinus) {
						answer -= Integer.parseInt(sb.toString());
						sb = new StringBuilder();
					} else {
						answer += Integer.parseInt(sb.toString());
						sb = new StringBuilder();
					}
					isMinus = true;
				}
			} else if (c == '+') {
				if (isFirst) {
					answer += Integer.parseInt(sb.toString());
					sb = new StringBuilder();
					isFirst = false;
					continue;
				}

				if (isMinus) {
					answer -= Integer.parseInt(sb.toString());
					sb = new StringBuilder();
				} else {
					answer += Integer.parseInt(sb.toString());
					sb = new StringBuilder();
				}
			} else {
				sb.append(c);
			}
		}

		if (isMinus) {
			answer -= Integer.parseInt(sb.toString());
		} else {
			answer += Integer.parseInt(sb.toString());
		}

		System.out.println(answer);
	}

}
