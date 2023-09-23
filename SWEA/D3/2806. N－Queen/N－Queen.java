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
    static int sum = 0;
    static int n;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            int[] board = new int[n];
            boolean[] checked = new boolean[n];

            for (int i = 0; i < n; i++) {
                board[0] = i;
                checked[i] = true;
                dfs(board, checked, 1);
                board[0] = 0;
                checked[i] = false;
            }

            sb.append("#" + test_case + " " + sum + "\n");
            sum = 0;
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int[] board, boolean[] checked, int L) {
        if (L == n) {
            sum++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (queenCheck(board, checked, i, L)) {
                board[L] = i;
                checked[i] = true;
                dfs(board, checked, L + 1);
                board[L] = 0;
                checked[i] = false;
            }
        }
    }

    private static boolean queenCheck(int[] board, boolean[] checked, int row, int col) {
        if (checked[row])
            return false;

        for (int i = 0; i < col; i++) {
            if (Math.abs(row - board[i]) == Math.abs(col - i))
                return false;
        }

        return true;
    }
}