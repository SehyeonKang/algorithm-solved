import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int maxPreFix = 0;
        String S = "";
        String T = "";
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                String s1 = words[i];
                String s2 = words[j];
                int count = 0;
                for (int k = 0; k < Math.min(s1.length(), s2.length()); k++) {
                    if (s1.charAt(k) != s2.charAt(k)) {
                        break;
                    }
                    count++;
                }

                if (count > maxPreFix) {
                    maxPreFix = count;
                    S = s1;
                    T = s2;
                }
            }
        }

        System.out.println(S + "\n" + T);
    }
}
