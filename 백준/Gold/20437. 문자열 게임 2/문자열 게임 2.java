import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int[] alphabets;
    static String pattern;
    static int answer1 = Integer.MAX_VALUE;
    static int answer2, K;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
  
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            pattern = br.readLine();
            K = Integer.parseInt(br.readLine());
            answer1 = Integer.MAX_VALUE;
            answer2 = 0;
            
            if (K == 1) {
                answer1 = 1;
                answer2 = 1;
            } else {
                solution();
                if (answer1 == Integer.MAX_VALUE) {
                	sb.append(-1 + "\n");
                	continue;
                }
            }
            sb.append(answer1 + " " + answer2 + "\n");
        }
        System.out.println(sb);
    }
    
    private static void solution() {
        for (int i = 0; i < 26; i++) {
        	int lt = 0;
            int rt = 0;
            int cnt = 0;
            
        	while (true) {
                if(pattern.charAt(rt++) - 'a' == i) {
                	cnt++;
                }
                
                if (cnt == K) {
                	while (pattern.charAt(lt) - 'a' != i) {
                		lt++;
                	}
                	answer1 = Math.min(answer1, rt - lt);
                	answer2 = Math.max(answer2, rt - lt);
                	lt++;
                	cnt--;
                }
                
                if (rt == pattern.length()) {
                	break;
                }
            }
        }
    }
}