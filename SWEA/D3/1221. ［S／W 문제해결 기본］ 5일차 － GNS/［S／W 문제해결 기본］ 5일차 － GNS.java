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
    static class CodeNumber implements Comparable<CodeNumber> {
        String code;
        int num;

        public CodeNumber(String code, int num) {
            this.code = code;
            this.num = num;
        }

        @Override
        public int compareTo(CodeNumber o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/swea/res/input.txt"));
        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        /*int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

        }*/

        int total = sc.nextInt();
        int test_case = 1;
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("ZRO", 0);
        hashMap.put("ONE", 1);
        hashMap.put("TWO", 2);
        hashMap.put("THR", 3);
        hashMap.put("FOR", 4);
        hashMap.put("FIV", 5);
        hashMap.put("SIX", 6);
        hashMap.put("SVN", 7);
        hashMap.put("EGT", 8);
        hashMap.put("NIN", 9);

        while (sc.hasNext()) {
            String testString = sc.next();
            int n = sc.nextInt();
            ArrayList<CodeNumber> arrayList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                arrayList.add(new CodeNumber(s, hashMap.get(s)));
            }
            Collections.sort(arrayList);

            sb.append("#" + test_case + "\n");
            for (int i = 0; i < n; i++) {
                sb.append(arrayList.get(i).code + " ");
            }
            sb.append("\n");
            test_case++;
        }

        bw.write(sb.toString());
        bw.flush();
    }
}