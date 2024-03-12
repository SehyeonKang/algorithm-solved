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
        System.out.println(answer + "\n" + sb);
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
    	int j = 0;
    	for (int i = 0; i < original.length(); i++) {
    		while (j > 0 && original.charAt(i) != pattern.charAt(j)) {
    			j = failure[j - 1];
    		}
    		if (original.charAt(i) == pattern.charAt(j)) {
    			if (j == pattern.length() - 1) {
    				answer++;
    				sb.append(i - j + 1 + " ");
    				j = failure[j];
    			} else {
    				j++;
    			}
    		}
    	}
    }
}
