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
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            boolean[] S = new boolean[14];
            boolean[] D = new boolean[14];
            boolean[] H = new boolean[14];
            boolean[] C = new boolean[14];
            String answer = "";
            String cards = br.readLine();

            for (int i = 0; i < cards.length(); i += 3) {
                String card = cards.substring(i, i + 3);
                int index = Integer.parseInt(card.substring(1, 3));

                if (card.charAt(0) == 'S') {
                    if (S[index]) {
                        answer = "ERROR";
                        break;
                    }
                    S[index] = true;
                } else if (card.charAt(0) == 'D') {
                    if (D[index]) {
                        answer = "ERROR";
                        break;
                    }
                    D[index] = true;
                } else if (card.charAt(0) == 'H') {
                    if (H[index]) {
                        answer = "ERROR";
                        break;
                    }
                    H[index] = true;
                } else if (card.charAt(0) == 'C') {
                    if (C[index]) {
                        answer = "ERROR";
                        break;
                    }
                    C[index] = true;
                }
            }

            if (answer.equals("ERROR")) {
                System.out.println("#" + testCase + " " + answer);
                continue;
            }

            int[] result = new int[4];

            for (int i = 1; i < 14; i++) {
                if (!S[i]) {
                    result[0]++;
                }
                if (!D[i]) {
                    result[1]++;
                }
                if (!H[i]) {
                    result[2]++;
                }
                if (!C[i]) {
                    result[3]++;
                }
            }

            System.out.println("#" + testCase + " "
                    + result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
        }
    }
}