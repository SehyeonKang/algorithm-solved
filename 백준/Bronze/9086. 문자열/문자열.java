
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            String a = String.valueOf(s.charAt(0));
            String b = String.valueOf(s.charAt(s.length() - 1));
            sb.append(a + b + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}