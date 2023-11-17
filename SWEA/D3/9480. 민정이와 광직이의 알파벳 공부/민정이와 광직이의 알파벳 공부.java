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
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {

    static int N;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb;
        int TC = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= TC; testCase++) {
            N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }
            int[] alphabets = new int[26];

            dfs(0, arr, alphabets);
            System.out.println("#" + testCase + " " + answer);
            answer = 0;
        }
    }

    private static void dfs(int L, String[] arr, int[] alphabets) {
        if (L == N) {
            long count = Arrays.stream(alphabets)
                    .filter(check -> check == 1)
                    .count();

            if (count == 26) {
                answer++;
            }
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        int alphabetIndex;

        for (char c : arr[L].toCharArray()) {
            alphabetIndex = c - 97;
            if (alphabetIndex >= 0 && alphabets[alphabetIndex] == 0) {
                alphabets[alphabetIndex] = 1;
                queue.offer(alphabetIndex);
            }
        }
        dfs(L + 1, arr, alphabets);

        while (!queue.isEmpty()) {
            alphabetIndex = queue.poll();
            alphabets[alphabetIndex] = 0;
        }
        dfs(L + 1, arr, alphabets);
    }
}