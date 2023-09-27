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

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        int[][] board = new int[300][300];
        HashMap<Integer, Integer[]> hashMap = new HashMap<>();
        int num;
        for (int i = 1; i < 300; i++) {
            int rowCount = 1;
            int colCount = i - 1;
            for (int j = 1; j < 300; j++) {
                if (i > 1) {
                    num = board[i -1][j] + colCount++;
                } else {
                    num = board[i][j - 1] + rowCount++;
                }
                board[i][j] = num;
                hashMap.put(num, new Integer[] {i, j});
            }
        }

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int x1 = hashMap.get(p)[0];
            int y1 = hashMap.get(p)[1];
            int x2 = hashMap.get(q)[0];
            int y2 = hashMap.get(q)[1];
            int answer = board[x1 + x2][y1 + y2];
            sb.append("#" + test_case + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}