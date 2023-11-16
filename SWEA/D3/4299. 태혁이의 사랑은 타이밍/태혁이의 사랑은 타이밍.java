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

import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        int T = Integer.parseInt(sc.nextLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(sc.nextLine());
            int D = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int time = 0;

            if (M - 11 >= 0) {
                time += M - 11;
            } else {
                time += 49 + M;
                H--;
                if (H == -1) {
                    H = 23;
                    D--;
                }
            }

            if (H - 11 >= 0) {
                time += (H - 11) * 60;
            } else {
                time += (13 + H) * 60;
                D--;
            }

            if (D - 11 >= 0) {
                time += (D - 11) * 1440;
            } else {
                time = -1;
            }

            System.out.println("#" + testCase + " " + time);
        }
    }
}