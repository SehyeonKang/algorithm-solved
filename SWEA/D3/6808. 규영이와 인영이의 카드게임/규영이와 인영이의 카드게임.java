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
    static int kyuWin;
    static int inWin;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            boolean[] card = new boolean[19];
            int[] kyu = new int[9];
            int[] in = new int[9];
            boolean[] check = new boolean[9];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                int n = Integer.parseInt(st.nextToken());
                card[n] = true;
            }

            int cnt1 = 0, cnt2 = 0;
            for (int i = 1; i < 19; i++) {
                if (card[i]) {
                    kyu[cnt1++] = i;
                } else
                    in[cnt2++] = i;
            }

            kyuWin = 0;
            inWin = 0;
            for (int i = 0; i < 9; i++) {
                check[i] = true;
                if (kyu[0] > in[i]) {
                    dfs(kyu, in, check, kyu[0] + in[i], 0, 1);
                } else
                    dfs(kyu, in, check, 0, kyu[0] + in[i], 1);
                check[i] = false;
            }
            sb.append("#" + test_case + " " + kyuWin + " " + inWin + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int[] kyu, int[] in, boolean[] check, int kyuScore, int inScore, int L) {
        if (kyuScore > 85) {
            int sum = 1;
            for (int i = 1; i <= 9 - L; i++) {
                sum *= i;
            }
            kyuWin += sum;
            return;
        } else if (inScore > 85) {
            int sum = 1;
            for (int i = 1; i <= 9 - L; i++) {
                sum *= i;
            }
            inWin += sum;
            return;
        }

        // 무승부
        if (L == 9)
            return;

        for (int i = 0; i < 9; i++) {
            if (!check[i]) {
                check[i] = true;
                if (kyu[L] > in[i]) {
                    dfs(kyu, in, check, kyuScore + kyu[L] + in[i], inScore, L + 1);
                } else
                    dfs(kyu, in, check, kyuScore, inScore + kyu[L] + in[i], L + 1);
                check[i] = false;
            }
        }
    }
}