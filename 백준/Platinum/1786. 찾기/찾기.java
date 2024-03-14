import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
	static StringBuilder sb;
	static int answer;
	static int[] failure;
	static String pattern, original;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        original = br.readLine();
        pattern = br.readLine();
        failure = new int[pattern.length()];
        
        getFailure();
        kmp();
        //System.out.println(answer + "\n" + sb);
    }

    private static void getFailure() {
    	int j = 0;
    	for (int i = 1; i < pattern.length(); i++) {
    		while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
    			j = failure[j - 1];
    		}
    		if (pattern.charAt(i) == pattern.charAt(j)) {
    			failure[i] = ++j;
    		}
    	}
    }
    
    private static void kmp() {
    	int cnt = 0;
        int j = 0;
        for (int i = 0; i < original.length(); i++) {
		        // 비교하는 값이 같을 때
            if (original.charAt(i) == pattern.charAt(j)) {
		            // 모든 문자가 같을 때
                if (++j == pattern.length()) {
                    cnt++;
                    sb.append(i - pattern.length() + 2).append(" ");
                    // 어디서 몇번째부터 비교할 지
                    i -= j;
                    j = j > 0 ? failure[j - 1] : 0;
                    i += j + 1;
                }
            } else {
	           // 비교하는 값이 다르면
			         // 어디서 몇번째부터 비교할 지
                i -= j;
                j = j > 0 ? failure[j - 1] : 0;
                i += j;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}
