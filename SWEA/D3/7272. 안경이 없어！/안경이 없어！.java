/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String answer = "SAME";
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			
			if (s1.length() == s2.length()) {
				Set<Character> set0 = new HashSet<>();
				Set<Character> set1 = new HashSet<>();
				Set<Character> set2 = new HashSet<>();
				
				for (int i = 65; i < 91; i++) {
					char c = (char) i;
					if (c == 'B') {
						set2.add(c);
					} else if (c == 'A' || c == 'D' || c == 'O' || c == 'P' || c == 'Q' || c == 'R') {
						set1.add(c);
					} else {
						set0.add(c);
					}
				}
				
				for (int i = 0; i < s1.length(); i++) {
					char c1 = s1.charAt(i);
					char c2 = s2.charAt(i);
					int check1 = -1;
					int check2 = -1;
					
					if (set0.contains(c1)) {
						check1 = 0;
					} else if (set1.contains(c1)) {
						check1 = 1;
					} else {
						check1 = 2;
					}
					
					if (set0.contains(c2)) {
						check2 = 0;
					} else if (set1.contains(c2)) {
						check2 = 1;
					} else {
						check2 = 2;
					}
					
					if (check1 != check2) {
						answer = "DIFF";
						break;
					}
				}
			} else {
				answer = "DIFF";
			}
			
			sb.append("#" + testCase + " " + answer + "\n");
 		}
		
		System.out.println(sb.toString());
	}
}