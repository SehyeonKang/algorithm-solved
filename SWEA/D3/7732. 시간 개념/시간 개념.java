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
            String time1 = br.readLine();
            String time2 = br.readLine();
            StringTokenizer st = new StringTokenizer(time1, ":");
            int totalTime1 = Integer.parseInt(st.nextToken()) * 3600
                    + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            st = new StringTokenizer(time2, ":");
            int totalTime2 = Integer.parseInt(st.nextToken()) * 3600
                    + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

            int resultTime = totalTime2 - totalTime1;
            if (resultTime < 0) {
                resultTime = 24 * 3600 + resultTime;
            }
            int hour = resultTime / 3600;
            int minute = resultTime % 3600 / 60;
            int second = resultTime % 60;
            String formattedHour = String.format("%02d", hour);
            String formattedMinute = String.format("%02d", minute);
            String formattedSecond = String.format("%02d", second);

            System.out.println("#" + testCase + " " + formattedHour + ":" + formattedMinute + ":" + formattedSecond);
        }
    }
}